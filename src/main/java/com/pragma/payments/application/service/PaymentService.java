package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import com.pragma.payments.domain.port.out.AntiFraudPort;
import com.pragma.payments.domain.port.out.BankingCorePort;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.IdempotencyViolationException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del caso de uso de procesamiento de pagos.
 * Orchestrea el flujo completo: validación antifraude, ejecución en core bancario
 * y persistencia del pago, con soporte para idempotencia.
 */
public class PaymentService implements ProcessPaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final long PROCESSING_THRESHOLD_MILLIS = 500L;

    private final PaymentPersistencePort persistencePort;
    private final AntiFraudPort antiFraudPort;
    private final BankingCorePort bankingCorePort;

    public PaymentService(
            PaymentPersistencePort persistencePort,
            AntiFraudPort antiFraudPort,
            BankingCorePort bankingCorePort) {
        this.persistencePort = persistencePort;
        this.antiFraudPort = antiFraudPort;
        this.bankingCorePort = bankingCorePort;
    }

    @Override
    public Payment processPayment(@NotNull @Valid Payment payment) {
        long startTime = Instant.now().toEpochMilli();
        log.info("Iniciando procesamiento de pago con idempotencyKey: {}", payment.idempotencyKey());

        validateIdempotency(payment.idempotencyKey(), payment.id());

        Payment validatedPayment = validateWithAntiFraud(payment);
        Payment completedPayment = executeBankingTransaction(validatedPayment);

        long processingTime = Instant.now().toEpochMilli() - startTime;
        log.info("Pago procesado exitosamente en {} ms. ID: {}", processingTime, completedPayment.id());

        if (!completedPayment.isWithinProcessingThreshold(PROCESSING_THRESHOLD_MILLIS)) {
            log.warn("El procesamiento del pago {} excedió el umbral de {} ms. Tiempo: {} ms",
                    completedPayment.id(), PROCESSING_THRESHOLD_MILLIS, processingTime);
        }

        return completedPayment;
    }

    private void validateIdempotency(String idempotencyKey, UUID paymentId) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            log.debug("No se proporcionó clave de idempotencia, se generará una nueva");
            return;
        }

        Optional<Payment> existingPayment = persistencePort.findByIdempotencyKey(idempotencyKey);
        if (existingPayment.isPresent()) {
            Payment existing = existingPayment.get();
            if (!existing.id().equals(paymentId)) {
                log.error("Idempotency violation: la clave {} ya fue utilizada para el pago {}",
                        idempotencyKey, existing.id());
                throw new IdempotencyViolationException(
                        "La clave de idempotencia ya fue utilizada para otro pago",
                        idempotencyKey);
            }
            log.info("Pago duplicado detectado, retornando resultado existente: {}", existing.id());
            throw new IdempotencyViolationException(
                    "El pago ya fue procesado",
                    idempotencyKey);
        }
    }

    private Payment validateWithAntiFraud(Payment payment) {
        log.debug("Iniciando validación antifraude para el pago: {}", payment.id());

        if (!antiFraudPort.isAvailable()) {
            log.warn("El servicio antifraude no está disponible, continuando con validación manual");
            return payment.withStatus(PaymentStatus.PENDING_FRAUD_CHECK);
        }

        try {
            String fraudCheckId = antiFraudPort.validateTransaction(payment);
            log.info("Validación antifraude exitosa. FraudCheckId: {}", fraudCheckId);
            return payment.withFraudCheckId(fraudCheckId).withStatus(PaymentStatus.FRAUD_APPROVED);
        } catch (Exception e) {
            log.error("Error en validación antifraude para pago {}: {}", payment.id(), e.getMessage());
            throw new PaymentProcessingException(
                    "Error en la validación antifraude: " + e.getMessage(),
                    payment.id(),
                    e);
        }
    }

    private Payment executeBankingTransaction(Payment payment) {
        log.debug("Ejecutando transacción bancaria para el pago: {}", payment.id());

        try {
            BigDecimal availableBalance = bankingCorePort.getAccountBalance(payment.accountNumber());
            if (availableBalance.compareTo(payment.amount()) < 0) {
                log.error("Saldo insuficiente para el pago {}. Saldo: {}, Monto: {}",
                        payment.id(), availableBalance, payment.amount());
                throw new PaymentProcessingException(
                        "Saldo insuficiente en la cuenta",
                        payment.id());
            }

            String transactionId = bankingCorePort.executeDebit(
                    payment.accountNumber(),
                    payment.amount(),
                    payment.id());

            log.info("Transacción bancaria ejecutada exitosamente. TransactionId: {}", transactionId);

            Payment completedPayment = payment
                    .withBankingCoreTransactionId(transactionId)
                    .withStatus(PaymentStatus.COMPLETED);

            return persistencePort.save(completedPayment);

        } catch (PaymentProcessingException e) {
            log.error("Error en transacción bancaria para pago {}: {}", payment.id(), e.getMessage());
            Payment failedPayment = payment.withStatus(PaymentStatus.FAILED);
            persistencePort.save(failedPayment);
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado en transacción bancaria para pago {}: {}", payment.id(), e.getMessage());
            Payment failedPayment = payment.withStatus(PaymentStatus.FAILED);
            persistencePort.save(failedPayment);
            throw new PaymentProcessingException(
                    "Error al procesar la transacción bancaria: " + e.getMessage(),
                    payment.id(),
                    e);
        }
    }
}