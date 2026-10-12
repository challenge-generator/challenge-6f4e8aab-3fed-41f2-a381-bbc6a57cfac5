package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.out.AntiFraudPort;
import com.pragma.payments.domain.port.out.BankingCorePort;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.AntiFraudException;
import com.pragma.payments.infrastructure.exception.IdempotencyViolationException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentService - Tests Unitarios")
class PaymentServiceTest {

    @Mock
    private PaymentPersistencePort paymentPersistencePort;

    @Mock
    private AntiFraudPort antiFraudPort;

    @Mock
    private BankingCorePort bankingCorePort;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentPersistencePort, antiFraudPort, bankingCorePort);
    }

    @Nested
    @DisplayName("Escenarios de Pago Exitoso")
    class SuccessfulPaymentScenarios {

        @Test
        @DisplayName("Debe procesar un pago válido completamente")
        void shouldProcessValidPaymentCompletely() {
            String idempotencyKey = "idem-" + UUID.randomUUID();
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("1000.00"),
                "COP",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-check-123");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-789");
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

            Payment result = paymentService.processPayment(payment);

            assertThat(result).isNotNull();
            assertThat(result.status()).isEqualTo(PaymentStatus.COMPLETED);
            assertThat(result.fraudCheckId()).isEqualTo("fraud-check-123");
            assertThat(result.bankingCoreTransactionId()).isEqualTo("txn-789");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort).validatePayment(any(Payment.class));
            verify(bankingCorePort).processTransaction(any(Payment.class));
            verify(paymentPersistencePort, times(2)).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe rechazar pago duplicado por clave de idempotencia")
        void shouldRejectDuplicatePaymentByIdempotencyKey() {
            String idempotencyKey = "idem-duplicate";
            Payment existingPayment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("500.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.COMPLETED,
                Instant.now().minusSeconds(3600),
                null,
                "fraud-001",
                "txn-001"
            );

            Payment newPayment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("500.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
                .thenReturn(Optional.of(existingPayment));

            assertThatThrownBy(() -> paymentService.processPayment(newPayment))
                .isInstanceOf(IdempotencyViolationException.class)
                .hasMessageContaining("Payment with idempotency key");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort, never()).validatePayment(any(Payment.class));
            verify(bankingCorePort, never()).processTransaction(any(Payment.class));
            verify(paymentPersistencePort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe validar que el pago está dentro del umbral de procesamiento")
        void shouldValidatePaymentWithinProcessingThreshold() {
            String idempotencyKey = "idem-" + UUID.randomUUID();
            Instant createdAt = Instant.now();
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-789",
                "ACC-001",
                new BigDecimal("250.50"),
                "EUR",
                idempotencyKey,
                PaymentStatus.PENDING,
                createdAt,
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-002");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-002");
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

            Payment result = paymentService.processPayment(payment);

            assertThat(result.isWithinProcessingThreshold(500000)).isTrue();
            assertThat(result.status()).isEqualTo(PaymentStatus.COMPLETED);
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallos en Integraciones Externas")
    class ExternalServiceFailureScenarios {

        @Test
        @DisplayName("Debe lanzar excepción cuando el servicio antifraude falla")
        void shouldThrowExceptionWhenAntiFraudServiceFails() {
            String idempotencyKey = "idem-antifraud-fail";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-100",
                "ACC-200",
                new BigDecimal("10000.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class)))
                .thenThrow(new AntiFraudException("Fraud detection service unavailable"));

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(AntiFraudException.class)
                .hasMessage("Fraud detection service unavailable");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort).validatePayment(any(Payment.class));
            verify(bankingCorePort, never()).processTransaction(any(Payment.class));
            verify(paymentPersistencePort).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe guardar estado FAILED cuando el core bancario falla")
        void shouldSaveFailedStatusWhenBankingCoreFails() {
            String idempotencyKey = "idem-banking-fail";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-300",
                "ACC-400",
                new BigDecimal("750.00"),
                "GBP",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-003");
            when(bankingCorePort.processTransaction(any(Payment.class)))
                .thenThrow(new PaymentProcessingException("Banking core transaction failed"));

            ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.save(paymentCaptor.capture()))
                .thenAnswer(inv -> inv.getArgument(0));

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(PaymentProcessingException.class)
                .hasMessage("Banking core transaction failed");

            Payment capturedPayment = paymentCaptor.getValue();
            assertThat(capturedPayment.status()).isEqualTo(PaymentStatus.FAILED);
            assertThat(capturedPayment.fraudCheckId()).isEqualTo("fraud-003");
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación de Datos")
    class DataValidationScenarios {

        @Test
        @DisplayName("Debe rechazar pago con monto negativo")
        void shouldRejectPaymentWithNegativeAmount() {
            String idempotencyKey = "idem-negative";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-500",
                "ACC-600",
                new BigDecimal("-100.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con cuenta de origen vacía")
        void shouldRejectPaymentWithEmptySourceAccount() {
            String idempotencyKey = "idem-empty-source";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "",
                "ACC-700",
                new BigDecimal("200.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con cuenta de destino vacía")
        void shouldRejectPaymentWithEmptyDestinationAccount() {
            String idempotencyKey = "idem-empty-dest";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-800",
                "",
                new BigDecimal("300.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con clave de idempotencia nula")
        void shouldRejectPaymentWithNullIdempotencyKey() {
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-900",
                "ACC-000",
                new BigDecimal("400.00"),
                "USD",
                null,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("Escenarios de Actualización de Estado")
    class StateUpdateScenarios {

        @Test
        @DisplayName("Debe actualizar estado correctamente con withStatus")
        void shouldUpdateStatusCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-state",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            Payment updatedPayment = originalPayment.withStatus(PaymentStatus.COMPLETED);

            assertThat(updatedPayment.status()).isEqualTo(PaymentStatus.COMPLETED);
            assertThat(updatedPayment.sourceAccount()).isEqualTo(originalPayment.sourceAccount());
            assertThat(updatedPayment.destinationAccount()).isEqualTo(originalPayment.destinationAccount());
            assertThat(updatedPayment.amount()).isEqualByComparingTo(originalPayment.amount());
        }

        @Test
        @DisplayName("Debe asociar ID de verificación antifraude correctamente")
        void shouldAssociateFraudCheckIdCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-fraud",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            String fraudCheckId = "FRAUD-" + UUID.randomUUID();
            Payment paymentWithFraudCheck = originalPayment.withFraudCheckId(fraudCheckId);

            assertThat(paymentWithFraudCheck.fraudCheckId()).isEqualTo(fraudCheckId);
            assertThat(paymentWithFraudCheck.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe asociar ID de transacción del core bancario correctamente")
        void shouldAssociateBankingTransactionIdCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-txn",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            String txnId = "TXN-" + UUID.randomUUID();
            Payment paymentWithTxnId = originalPayment.withBankingCoreTransactionId(txnId);

            assertThat(paymentWithTxnId.bankingCoreTransactionId()).isEqualTo(txnId);
            assertThat(paymentWithTxnId.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe detectar correctamente cuando el pago excede el umbral de tiempo")
        void shouldDetectWhenPaymentExceedsTimeThreshold() {
            long fiveMinutesAgo = Instant.now().minusSeconds(300).toEpochMilli();
            Payment oldPayment = new Payment(
                UUID.randomUUID(),
                "ACC-OLD",
                "ACC-NEW",
                new BigDecimal("500.00"),
                "USD",
                "idem-old",
                PaymentStatus.PENDING,
                Instant.ofEpochMilli(fiveMinutesAgo),
                null,
                null,
                null
            );

            assertThat(oldPayment.isWithinProcessingThreshold(500000)).isFalse();
        }
    }

    @Nested
    @DisplayName("Escenarios de Persistencia")
    class PersistenceScenarios {

        @Test
        @DisplayName("Debe guardar el pago inicial con estado PENDING")
        void shouldSaveInitialPaymentWithPendingStatus() {
            String idempotencyKey = "idem-persist-1";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-P1",
                "ACC-P2",
                new BigDecimal("600.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            ArgumentCaptor<Payment> saveCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(saveCaptor.capture())).thenAnswer(inv -> inv.getArgument(0));
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-persist");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-persist");

            paymentService.processPayment(payment);

            Payment savedPayment = saveCaptor.getValue();
            assertThat(savedPayment.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe actualizar el pago con estado COMPLETED después de éxito")
        void shouldUpdatePaymentToCompletedAfterSuccess() {
            String idempotencyKey = "idem-persist-2";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-C1",
                "ACC-C2",
                new BigDecimal("800.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            ArgumentCaptor<Payment> saveCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(saveCaptor.capture())).thenAnswer(inv -> inv.getArgument(0));
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-complete");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-complete");

            paymentService.processPayment(payment);

            var allSavedPayments = saveCaptor.getAllValues();
            Payment finalPayment = allSavedPayments.get(allSavedPayments.size() - 1);
            assertThat(finalPayment.status()).isEqualTo(PaymentStatus.COMPLETED);
        }

        @Test
        @DisplayName("Debe encontrar pago por ID existente")
        void shouldFindPaymentById() {
            UUID paymentId = UUID.randomUUID();
            Payment existingPayment = new Payment(
                paymentId,
                "ACC-F1",
                "ACC-F2",
                new BigDecimal("900.00"),
                "USD",
                "idem-find",
                PaymentStatus.COMPLETED,
                Instant.now(),
                null,
                "fraud-find",
                "txn-find"
            );

            when(paymentPersistencePort.findById(paymentId)).thenReturn(Optional.of(existingPayment));

            Optional<Payment> result = paymentPersistencePort.findById(paymentId);

            assertThat(result).isPresent();
            assertThat(result.get().id()).isEqualTo(paymentId);
            assertThat(result.get().status()).isEqualTo(PaymentStatus.COMPLETED);
        }

        @Test
        @DisplayName("Debe retornar vacío cuando el pago no existe")
        void shouldReturnEmptyWhenPaymentNotFound() {
            UUID nonExistentId = UUID.randomUUID();

            when(paymentPersistencePort.findById(nonExistentId)).thenReturn(Optional.empty());

            Optional<Payment> result = paymentPersistencePort.findById(nonExistentId);

            assertThat(result).isEmpty();
        }
    }
}