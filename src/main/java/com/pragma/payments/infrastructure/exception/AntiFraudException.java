package com.pragma.payments.infrastructure.exception;

import java.io.Serial;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class AntiFraudException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public enum FraudCheckResult {
        REJECTED,
        REVIEW_REQUIRED,
        TIMEOUT,
        SERVICE_UNAVAILABLE,
        INTERNAL_ERROR
    }

    private final UUID paymentId;
    private final FraudCheckResult result;
    private final Instant checkTimestamp;
    private final List<String> rejectionReasons;
    private final Double riskScore;
    private final String fraudCheckId;
    private final boolean isRetryable;

    public AntiFraudException(String message) {
        super(message);
        this.paymentId = null;
        this.result = FraudCheckResult.INTERNAL_ERROR;
        this.checkTimestamp = Instant.now();
        this.rejectionReasons = List.of();
        this.riskScore = null;
        this.fraudCheckId = null;
        this.isRetryable = true;
    }

    public AntiFraudException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.result = FraudCheckResult.INTERNAL_ERROR;
        this.checkTimestamp = Instant.now();
        this.rejectionReasons = List.of();
        this.riskScore = null;
        this.fraudCheckId = null;
        this.isRetryable = true;
    }

    public AntiFraudException(UUID paymentId, FraudCheckResult result, String message) {
        super(message);
        this.paymentId = paymentId;
        this.result = result;
        this.checkTimestamp = Instant.now();
        this.rejectionReasons = List.of();
        this.riskScore = null;
        this.fraudCheckId = null;
        this.isRetryable = result == FraudCheckResult.TIMEOUT || result == FraudCheckResult.SERVICE_UNAVAILABLE;
    }

    public AntiFraudException(UUID paymentId, FraudCheckResult result, List<String> reasons, Double riskScore, String fraudCheckId) {
        super("Verificación antifraude fallida: " + (reasons != null && !reasons.isEmpty() ? reasons.get(0) : "razón desconocida"));
        this.paymentId = paymentId;
        this.result = result;
        this.checkTimestamp = Instant.now();
        this.rejectionReasons = reasons != null ? reasons : List.of();
        this.riskScore = riskScore;
        this.fraudCheckId = fraudCheckId;
        this.isRetryable = result == FraudCheckResult.TIMEOUT || result == FraudCheckResult.SERVICE_UNAVAILABLE;
    }

    public AntiFraudException(UUID paymentId, FraudCheckResult result, List<String> reasons, Double riskScore, 
                               String fraudCheckId, Throwable cause) {
        super("Error en verificación antifraude: " + cause.getMessage(), cause);
        this.paymentId = paymentId;
        this.result = result;
        this.checkTimestamp = Instant.now();
        this.rejectionReasons = reasons != null ? reasons : List.of();
        this.riskScore = riskScore;
        this.fraudCheckId = fraudCheckId;
        this.isRetryable = result == FraudCheckResult.TIMEOUT || result == FraudCheckResult.SERVICE_UNAVAILABLE;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public FraudCheckResult getResult() {
        return result;
    }

    public Instant getCheckTimestamp() {
        return checkTimestamp;
    }

    public List<String> getRejectionReasons() {
        return rejectionReasons;
    }

    public Optional<Double> getRiskScore() {
        return Optional.ofNullable(riskScore);
    }

    public Optional<String> getFraudCheckId() {
        return Optional.ofNullable(fraudCheckId);
    }

    public boolean isRetryable() {
        return isRetryable;
    }

    public boolean isRejected() {
        return result == FraudCheckResult.REJECTED;
    }

    public boolean requiresReview() {
        return result == FraudCheckResult.REVIEW_REQUIRED;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("AntiFraudException{");
        sb.append("result=").append(result);
        if (paymentId != null) {
            sb.append(", paymentId=").append(paymentId);
        }
        if (fraudCheckId != null) {
            sb.append(", fraudCheckId=").append(fraudCheckId);
        }
        if (riskScore != null) {
            sb.append(", riskScore=").append(riskScore);
        }
        if (!rejectionReasons.isEmpty()) {
            sb.append(", reasons=").append(rejectionReasons);
        }
        sb.append(", timestamp=").append(checkTimestamp);
        sb.append(", retryable=").append(isRetryable);
        sb.append("}");
        return sb.toString();
    }
}