package com.pragma.payments.domain.port.in;

import com.pragma.payments.domain.model.Payment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ProcessPaymentUseCase {
    /**
     * Processes a payment with idempotency and fraud check.
     * @param payment The payment to process, must not be null and must pass validation.
     * @return The processed payment with updated status and transaction IDs.
     * @throws com.pragma.payments.infrastructure.exception.IdempotencyViolationException if the idempotency key is duplicated.
     * @throws com.pragma.payments.infrastructure.exception.PaymentProcessingException if the payment processing fails.
     */
    Payment processPayment(@NotNull @Valid Payment payment);
}