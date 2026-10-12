package com.pragma.payments.infrastructure.exception;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentProcessingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID paymentId;
    private final String errorCode;
    private final Instant timestamp;
    private final BigDecimal paymentAmount;
    private final String paymentSource;

    public PaymentProcessingException() {
        super("Error desconocido en el procesamiento del pago");
        this.paymentId = null;
        this.errorCode = "PAYMENT_UNKNOWN_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.errorCode = "PAYMENT_GENERIC_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.errorCode = "PAYMENT_INTERNAL_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(UUID paymentId, String errorCode, String message) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(UUID paymentId, BigDecimal amount, String errorCode, String message, String source) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = amount;
        this.paymentSource = source;
    }

    public PaymentProcessingException(UUID paymentId, BigDecimal amount, String errorCode, String message, String source, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = amount;
        this.paymentSource = source;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentSource() {
        return paymentSource;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentProcessingException{");
        sb.append("errorCode=").append(errorCode);
        if (paymentId != null) {
            sb.append(", paymentId=").append(paymentId);
        }
        if (paymentAmount != null) {
            sb.append(", amount=").append(paymentAmount);
        }
        if (paymentSource != null) {
            sb.append(", source=").append(paymentSource);
        }
        sb.append(", timestamp=").append(timestamp);
        sb.append(", message=").append(getMessage());
        sb.append("}");
        return sb.toString();
    }

    public boolean isRetryable() {
        return "PAYMENT_TIMEOUT".equals(errorCode) || 
               "PAYMENT_NETWORK_ERROR".equals(errorCode) ||
               "PAYMENT_BANKING_UNAVAILABLE".equals(errorCode);
    }

    public boolean isIdempotent() {
        return "PAYMENT_DUPLICATE".equals(errorCode) ||
               "PAYMENT_ALREADY_PROCESSED".equals(errorCode);
    }
}