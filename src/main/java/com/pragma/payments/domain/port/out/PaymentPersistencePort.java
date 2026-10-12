package com.pragma.payments.domain.port.out;

import com.pragma.payments.domain.model.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentPersistencePort {
    /**
     * Saves a payment to the persistence store.
     * @param payment The payment to save, must not be null.
     * @return The saved payment.
     */
    Payment save(Payment payment);

    /**
     * Finds a payment by its unique identifier.
     * @param paymentId The payment ID, must not be null.
     * @return An Optional containing the payment if found, empty otherwise.
     */
    Optional<Payment> findById(UUID paymentId);

    /**
     * Finds a payment by its idempotency key.
     * @param idempotencyKey The idempotency key, must not be null.
     * @return An Optional containing the payment if found, empty otherwise.
     */
    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}