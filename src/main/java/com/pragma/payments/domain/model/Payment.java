package com.pragma.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
    @NotNull(message = "Payment ID cannot be null")
    UUID paymentId,

    @NotNull(message = "Transaction ID cannot be null")
    String transactionId,

    @NotNull(message = "Originator ID cannot be null")
    UUID originatorId,

    @NotBlank(message = "Account number cannot be blank")
    String accountNumber,

    @NotBlank(message = "Beneficiary account cannot be blank")
    String beneficiaryAccount,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    String currency,

    @NotNull(message = "Processing timestamp cannot be null")
    LocalDateTime processingTimestamp,

    @NotNull(message = "Status cannot be null")
    PaymentStatus status,

    @NotBlank(message = "Idempotency key cannot be blank")
    String idempotencyKey,

    String fraudCheckId,

    String bankingCoreTransactionId
) {
    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED
    }

    public Payment {
        if (paymentId == null) {
            paymentId = UUID.randomUUID();
        }
        if (processingTimestamp == null) {
            processingTimestamp = LocalDateTime.now();
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            newStatus,
            this.idempotencyKey,
            this.fraudCheckId,
            this.bankingCoreTransactionId
        );
    }

    public Payment withFraudCheckId(String fraudCheckId) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            this.status,
            this.idempotencyKey,
            fraudCheckId,
            this.bankingCoreTransactionId
        );
    }

    public Payment withBankingCoreTransactionId(String bankingCoreTransactionId) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            this.status,
            this.idempotencyKey,
            this.fraudCheckId,
            bankingCoreTransactionId
        );
    }

    public boolean isWithinProcessingThreshold(long thresholdMillis) {
        return LocalDateTime.now().minusNanos(thresholdMillis * 1_000_000)
                .isBefore(this.processingTimestamp);
    }
}