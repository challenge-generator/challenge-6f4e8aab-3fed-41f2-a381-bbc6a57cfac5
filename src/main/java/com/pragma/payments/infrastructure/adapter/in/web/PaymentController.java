package com.pragma.payments.infrastructure.adapter.in.web;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request) {
        log.info("Received payment request for amount: {} currency: {}", 
                request.amount(), request.currency());

        Payment payment = new Payment(
                UUID.randomUUID(),
                request.amount(),
                request.currency(),
                request.payerDocument(),
                request.payerName(),
                request.payeeDocument(),
                request.payeeName(),
                request.idempotencyKey(),
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null,
                null
        );

        Payment processedPayment = processPaymentUseCase.processPayment(payment);

        PaymentResponse response = new PaymentResponse(
                processedPayment.id(),
                processedPayment.status().name(),
                processedPayment.amount(),
                processedPayment.currency(),
                processedPayment.payerDocument(),
                processedPayment.payeeDocument(),
                processedPayment.idempotencyKey(),
                processedPayment.createdAt().toString(),
                processedPayment.fraudCheckId(),
                processedPayment.bankingCoreTransactionId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public record PaymentRequest(
            @NotNull @Positive BigDecimal amount,
            @NotBlank String currency,
            @NotBlank String payerDocument,
            @NotBlank String payerName,
            @NotBlank String payeeDocument,
            @NotBlank String payeeName,
            @NotBlank String idempotencyKey
    ) {}

    public record PaymentResponse(
            UUID id,
            String status,
            BigDecimal amount,
            String currency,
            String payerDocument,
            String payeeDocument,
            String idempotencyKey,
            String createdAt,
            String fraudCheckId,
            String bankingCoreTransactionId
    ) {}
}