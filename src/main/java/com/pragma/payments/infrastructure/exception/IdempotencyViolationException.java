package com.pragma.payments.infrastructure.exception;

import java.io.Serial;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class IdempotencyViolationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String idempotencyKey;
    private final UUID existingPaymentId;
    private final UUID requestedPaymentId;
    private final Instant existingPaymentTimestamp;
    private final String conflictingField;
    private final boolean allowsReconciliation;

    public IdempotencyViolationException(String idempotencyKey) {
        super("Violación de idempotencia: la clave proporcionado ya existe");
        this.idempotencyKey = idempotencyKey;
        this.existingPaymentId = null;
        this.requestedPaymentId = null;
        this.existingPaymentTimestamp = null;
        this.conflictingField = null;
        this.allowsReconciliation = false;
    }

    public IdempotencyViolationException(String idempotencyKey, UUID existingPaymentId) {
        super("Violación de idempotencia: la clave '" + idempotencyKey + "' ya está asociada al pago " + existingPaymentId);
        this.idempotencyKey = idempotencyKey;
        this.existingPaymentId = existingPaymentId;
        this.requestedPaymentId = null;
        this.existingPaymentTimestamp = null;
        this.conflictingField = null;
        this.allowsReconciliation = true;
    }

    public IdempotencyViolationException(String idempotencyKey, UUID existingPaymentId, UUID requestedPaymentId, String conflictingField) {
        super("Conflicto de idempotencia detectado entre el pago existente " + existingPaymentId + " y el nuevo pago " + requestedPaymentId);
        this.idempotencyKey = idempotencyKey;
        this.existingPaymentId = existingPaymentId;
        this.requestedPaymentId = requestedPaymentId;
        this.existingPaymentTimestamp = Instant.now();
        this.conflictingField = conflictingField;
        this.allowsReconciliation = true;
    }

    public IdempotencyViolationException(String idempotencyKey, UUID existingPaymentId, UUID requestedPaymentId, 
                                          String conflictingField, Instant existingTimestamp) {
        super("Conflicto de idempotencia en el campo '" + conflictingField + "' entre pagos");
        this.idempotencyKey = idempotencyKey;
        this.existingPaymentId = existingPaymentId;
        this.requestedPaymentId = requestedPaymentId;
        this.existingPaymentTimestamp = existingTimestamp;
        this.conflictingField = conflictingField;
        this.allowsReconciliation = true;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public Optional<UUID> getExistingPaymentId() {
        return Optional.ofNullable(existingPaymentId);
    }

    public Optional<UUID> getRequestedPaymentId() {
        return Optional.ofNullable(requestedPaymentId);
    }

    public Optional<Instant> getExistingPaymentTimestamp() {
        return Optional.ofNullable(existingPaymentTimestamp);
    }

    public Optional<String> getConflictingField() {
        return Optional.ofNullable(conflictingField);
    }

    public boolean isAllowsReconciliation() {
        return allowsReconciliation;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("IdempotencyViolationException{");
        sb.append("key=").append(idempotencyKey);
        if (existingPaymentId != null) {
            sb.append(", existingPaymentId=").append(existingPaymentId);
        }
        if (requestedPaymentId != null) {
            sb.append(", requestedPaymentId=").append(requestedPaymentId);
        }
        if (conflictingField != null) {
            sb.append(", conflictingField=").append(conflictingField);
        }
        if (existingPaymentTimestamp != null) {
            sb.append(", existingTimestamp=").append(existingPaymentTimestamp);
        }
        sb.append(", reconciliationAllowed=").append(allowsReconciliation);
        sb.append("}");
        return sb.toString();
    }
}