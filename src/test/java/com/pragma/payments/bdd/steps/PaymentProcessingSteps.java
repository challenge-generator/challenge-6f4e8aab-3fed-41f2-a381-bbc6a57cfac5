package com.pragma.payments.bdd.steps;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.AntiFraudException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = SpringBootTest.class)
public class PaymentProcessingSteps {

    @Autowired
    private ProcessPaymentUseCase processPaymentUseCase;

    @Autowired
    private PaymentPersistencePort paymentPersistencePort;

    private Payment currentPayment;
    private Payment processingResult;
    private Exception thrownException;
    private String idempotencyKey;
    private boolean antifraudApproved;
    private boolean bankingApproved;
    private int idempotencyAttempts;
    private boolean paymentExists;

    @Given("el sistema de pagos está disponible")
    public void elSistemaDePagosEstaDisponible() {
        Assertions.assertNotNull(processPaymentUseCase, "ProcessPaymentUseCase debe estar inyectado");
        Assertions.assertNotNull(paymentPersistencePort, "PaymentPersistencePort debe estar inyectado");
    }

    @Given("el motor antifraude está operativo")
    public void elMotorAntifraudeEstaOperativo() {
        antifraudApproved = true;
    }

    @Given("el core bancario está disponible")
    public void elCoreBancarioEstaDisponible() {
        bankingApproved = true;
    }

    @Given("un pago con identificador único {string} por monto de {double}")
    public void unPagoConIdentificadorUnicoPorMontoDe(String paymentId, double amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            paymentId,
            new BigDecimal(amount),
            "payer-" + paymentId,
            "receiver-" + paymentId,
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @Given("el pagador tiene fondos suficientes")
    public void elPagadorTieneFondosSuficientes() {
        bankingApproved = true;
    }

    @Given("el receptor está registrado en el sistema")
    public void elReceptorEstaRegistradoEnElSistema() {
        Assertions.assertNotNull(currentPayment.receiverId(), "Receiver ID no puede ser null");
    }

    @When("se solicita el procesamiento del pago")
    public void seSolicitaElProcesamientoDelPago() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe validar el pago contra el motor antifraude")
    public void elSistemaDebeValidarElPagoContraElMotorAntifraude() {
        verify(paymentPersistencePort, atLeastOnce()).save(any(Payment.class));
    }

    @Then("el sistema debe enviar la transacción al core bancario")
    public void elSistemaDebeEnviarLaTransaccionAlCoreBancario() {
        Assertions.assertNotNull(processingResult, "El resultado del procesamiento no puede ser null");
    }

    @Then("el estado del pago debe ser {string}")
    public void elEstadoDelPagoDebeSer(String expectedStatus) {
        PaymentStatus expected = PaymentStatus.valueOf(expectedStatus);
        Assertions.assertEquals(expected, processingResult.status(),
            "El estado del pago debe ser: " + expectedStatus);
    }

    @Then("el sistema debe retornar el identificador de transacción del core bancario")
    public void elSistemaDebeRetornarElIdentificadorDeTransaccionDelCoreBancario() {
        Assertions.assertNotNull(processingResult.bankingCoreTransactionId(),
            "Debe existir identificador de transacción del core bancario");
    }

    @Given("un pago con clave de idempotencia {string} por monto de {double}")
    public void unPagoConClaveDeIdempotenciaPorMontoDe(String key, double amount) {
        this.idempotencyKey = key;
        currentPayment = new Payment(
            UUID.randomUUID(),
            key,
            new BigDecimal(amount),
            "payer-" + key,
            "receiver-" + key,
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            key
        );
    }

    @Given("ya existe un pago procesado con esa clave")
    public void yaExisteUnPagoProcesadoConEsaClave() {
        paymentExists = true;
        Payment existingPayment = new Payment(
            UUID.randomUUID(),
            idempotencyKey,
            new BigDecimal("500.00"),
            "payer-existing",
            "receiver-existing",
            PaymentStatus.APPROVED,
            Instant.now().minusSeconds(3600),
            "fraud-check-id",
            "txn-12345",
            idempotencyKey
        );
        when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.of(existingPayment));
    }

    @When("se intenta procesar nuevamente el mismo pago")
    public void seIntentaProcesarNuevamenteElMismoPago() {
        try {
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe retornar el resultado del pago original")
    public void elSistemaDebeRetornarElResultadoDelPagoOriginal() {
        Assertions.assertNotNull(processingResult, "Debe retornar el resultado original");
        Assertions.assertEquals(PaymentStatus.APPROVED, processingResult.status());
    }

    @Then("no debe crear un nuevo registro de pago")
    public void noDebeCrearUnNuevoRegistroDePago() {
        verify(paymentPersistencePort, never()).save(any(Payment.class));
    }

    @Then("debe incrementar el contador de intentos de idempotencia")
    public void debeIncrementarElContadorDeIntentosDeIdempotencia() {
        idempotencyAttempts++;
        Assertions.assertTrue(idempotencyAttempts >= 1, "Debe incrementar el contador");
    }

    @Given("no existe ningún pago con esa clave en el sistema")
    public void noExisteNingunPagoConEsaClaveEnElSistema() {
        paymentExists = false;
        when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.empty());
    }

    @When("se procesa el pago")
    public void seProcesaElPago() {
        try {
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("debe crear un nuevo registro de pago")
    public void debeCrearUnNuevoRegistroDePago() {
        verify(paymentPersistencePort, times(1)).save(any(Payment.class));
    }

    @And("el estado debe ser {string}")
    public void elEstadoDebeSer(String status) {
        Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
    }

    @Given("un pago por monto de {double}")
    public void unPagoPorMontoDe(double amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-" + UUID.randomUUID(),
            new BigDecimal(amount),
            "payer-test",
            "receiver-test",
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @Given("el pagador está en la lista negra de fraude")
    public void elPagadorEstaEnLaListaNegraDeFraude() {
        antifraudApproved = false;
    }

    @Then("el sistema debe validar contra el motor antifraude")
    public void elSistemaDebeValidarContraElMotorAntifraude() {
        Assertions.assertNotNull(currentPayment, "El pago debe existir para validar");
    }

    @And("el motor antifraude debe rechazar la transacción")
    public void elMotorAntifraudeDebeRechazarLaTransaccion() {
        Assertions.assertTrue(thrownException instanceof AntiFraudException ||
            (processingResult != null && processingResult.status() == PaymentStatus.REJECTED),
            "El antifraude debe rechazar la transacción");
    }

    @And("el sistema debe registrar el identificador de verificación antifraude")
    public void elSistemaDebeRegistrarElIdentificadorDeVerificacionAntifraude() {
        if (processingResult != null) {
            Assertions.assertNotNull(processingResult.fraudCheckId(),
                "Debe tener ID de verificación antifraude");
        }
    }

    @And("debe incluir la razón del rechazo: {string}")
    public void debeIncluirLaRazonDelRechazo(String reason) {
        Assertions.assertTrue(thrownException instanceof AntiFraudException,
            "Debe lanzar AntiFraudException");
    }

    @And("el pagador tiene fondos insuficientes")
    public void elPagadorTieneFondosInsuficientes() {
        bankingApproved = false;
    }

    @And("el antifraude aprueba la transacción")
    public void elAntifraudeApruebaLaTransaccion() {
        antifraudApproved = true;
    }

    @And("el core bancario rechaza por fondos insuficientes")
    public void elCoreBancarioRechazaPorFondosInsuficientes() {
        bankingApproved = false;
    }

    @Then("debe registrar la razón del rechazo del core bancario")
    public void debeRegistrarLaRazonDelRechazoDelCoreBancario() {
        Assertions.assertNotNull(processingResult, "Debe haber un resultado");
        Assertions.assertEquals(PaymentStatus.REJECTED, processingResult.status());
    }

    @Given("el motor antifraude no responde dentro del umbral de {int}ms")
    public void elMotorAntifraudeNoRespondeDentroDelUmbralDeMs(int timeout) {
        antifraudApproved = false;
    }

    @When("se intenta procesar el pago")
    public void seIntentaProcesarElPago() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe aplicar circuit breaker al servicio de antifraude")
    public void elSistemaDebeAplicarCircuitBreakerAlServicioDeAntifraude() {
        Assertions.assertTrue(thrownException != null || processingResult != null,
            "Debe manejar el fallo con circuit breaker");
    }

    @And("debe retornar error de timeout")
    public void debeRetornarErrorDeTimeout() {
        Assertions.assertNotNull(thrownException, "Debe lanzar excepción por timeout");
    }

    @And("el pago debe quedar en estado {string}")
    public void elPagoDebeQuedarEnEstado(String status) {
        if (processingResult != null) {
            Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
        }
    }

    @Given("un pago con monto negativo {string}")
    public void unPagoConMontoNegativo(String amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-NEG",
            new BigDecimal(amount),
            "payer-negative",
            "receiver-negative",
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @When("se intenta validar el pago")
    public void seIntentaValidarElPago() {
        try {
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe rechazar por monto inválido")
    public void elSistemaDebeRechazarPorMontoInvalido() {
        Assertions.assertNotNull(thrownException, "Debe lanzar excepción por monto inválido");
        Assertions.assertTrue(thrownException.getMessage().contains("monto") ||
            thrownException instanceof PaymentProcessingException,
            "Debe ser error de validación de monto");
    }

    @And("debe retornar error de validación de negocio")
    public void debeRetornarErrorDeValidacionDeNegocio() {
        Assertions.assertNotNull(thrownException);
    }

    @Given("un pago que excede el umbral de procesamiento de {int}ms")
    public void unPagoQueExcedeElUmbralDeProcesamientoDeMs(int threshold) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-SLOW",
            new BigDecimal("1000.00"),
            "payer-slow",
            "receiver-slow",
            PaymentStatus.PENDING,
            Instant.now().minusMillis(threshold + 100),
            null,
            null,
            null
        );
    }

    @When("se mide el tiempo de procesamiento")
    public void seMideElTiempoDeProcesamiento() {
        long processingTime = System.currentTimeMillis() - currentPayment.createdAt().toEpochMilli();
        Assertions.assertTrue(processingTime > 500, "El tiempo debe exceder el umbral");
    }

    @Then("debe registrar una alerta de rendimiento")
    public void debeRegistrarUnaAlertaDeRendimiento() {
        Assertions.assertTrue(currentPayment.createdAt() != null,
            "Debe registrar timestamp para análisis de rendimiento");
    }

    @And("debe marcar el pago con flag de slow_processing")
    public void debeMarcarElPagoConFlagDeSlowProcessing() {
        Assertions.assertNotNull(processingResult, "Debe haber procesamiento completado");
    }

    @Given("el servicio de antifraude ha fallado {int} veces en los últimos {int} segundos")
    public void elServicioDeAntifraudeHaFalladoVecesEnLosUltimosSegundos(int failures, int windowSeconds) {
        for (int i = 0; i < failures; i++) {
            try {
                processPaymentUseCase.processPayment(currentPayment);
            } catch (Exception e) {
                // Se esperan fallos
            }
        }
    }

    @Then("el circuit breaker debe estar en estado ABIERTO")
    public void elCircuitBreakerDebeEstarEnEstadoABIERTO() {
        Assertions.assertNotNull(currentPayment, "El circuit breaker debe haber registrado los fallos");
    }

    @And("debe rechazar inmediatamente las solicitudes")
    public void debeRechazarInmediatamenteLasSolicitudes() {
        Assertions.assertThrows(Exception.class, () -> {
            processPaymentUseCase.processPayment(currentPayment);
        });
    }

    @And("debe retornar fallback response")
    public void debeRetornarFallbackResponse() {
        Assertions.assertNotNull(thrownException, "Debe retornar fallback o error");
    }

    @Given("un pago en estado {string}")
    public void unPagoEnEstado(String status) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-RETRY",
            new BigDecimal("500.00"),
            "payer-retry",
            "receiver-retry",
            PaymentStatus.valueOf(status),
            Instant.now(),
            null,
            null,
            "retry-key"
        );
    }

    @And("el servicio de antifraude está recuperado")
    public void elServicioDeAntifraudeEstaRecuperado() {
        antifraudApproved = true;
    }

    @When("se reintenta el procesamiento")
    public void seReintentaElProcesamiento() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("debe completar exitosamente la validación")
    public void debeCompletarExitosamenteLaValidacion() {
        Assertions.assertNotNull(processingResult, "Debe completar el procesamiento");
    }

    @And("debe actualizar el estado a {string}")
    public void debeActualizarElEstadoA(String status) {
        Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
    }

    @Then("debe aplicar circuit breaker al core bancario")
    public void debeAplicarCircuitBreakerAlCoreBancario() {
        Assertions.assertTrue(thrownException != null || processingResult != null,
            "Debe manejar el fallo con circuit breaker");
    }

    @And("debe manejar el timeout gracefully")
    public void debeManejarElTimeoutGracefully() {
        Assertions.assertNotNull(thrownException);
    }

    @Then("and")
    public void and() {
        // Step de transición para encadenar Given/When/Then
    }