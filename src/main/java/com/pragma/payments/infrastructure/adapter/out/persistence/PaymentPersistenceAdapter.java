package com.pragma.payments.infrastructure.adapter.out.persistence;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentPersistenceAdapter implements PaymentPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(PaymentPersistenceAdapter.class);
    private final PaymentRepository paymentRepository;

    public PaymentPersistenceAdapter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        log.debug("Saving payment with id: {} and idempotency key: {}", 
                payment.id(), payment.idempotencyKey());
        
        PaymentEntity entity = toEntity(payment);
        PaymentEntity savedEntity = paymentRepository.save(entity);
        
        log.info("Payment saved successfully with id: {}", savedEntity.getId());
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Payment> findById(UUID paymentId) {
        log.debug("Finding payment by id: {}", paymentId);
        return paymentRepository.findById(paymentId).map(this::toDomain);
    }

    @Override
    public Optional<Payment> findByIdempotencyKey(String idempotencyKey) {
        log.debug("Finding payment by idempotency key: {}", idempotencyKey);
        return paymentRepository.findByIdempotencyKey(idempotencyKey).map(this::toDomain);
    }

    private PaymentEntity toEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.id());
        entity.setAmount(payment.amount());
        entity.setCurrency(payment.currency());
        entity.setPayerDocument(payment.payerDocument());
        entity.setPayerName(payment.payerName());
        entity.setPayeeDocument(payment.payeeDocument());
        entity.setPayeeName(payment.payeeName());
        entity.setIdempotencyKey(payment.idempotencyKey());
        entity.setStatus(payment.status().name());
        entity.setCreatedAt(payment.createdAt());
        entity.setUpdatedAt(payment.updatedAt());
        entity.setFraudCheckId(payment.fraudCheckId());
        entity.setBankingCoreTransactionId(payment.bankingCoreTransactionId());
        return entity;
    }

    private Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getPayerDocument(),
                entity.getPayerName(),
                entity.getPayeeDocument(),
                entity.getPayeeName(),
                entity.getIdempotencyKey(),
                PaymentStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getFraudCheckId(),
                entity.getBankingCoreTransactionId()
        );
    }

    @Entity
    @Table(name = "payments")
    public static class PaymentEntity {

        @Id
        private UUID id;

        @Column(nullable = false, precision = 19, scale = 4)
        private java.math.BigDecimal amount;

        @Column(nullable = false, length = 3)
        private String currency;

        @Column(name = "payer_document", nullable = false, length = 50)
        private String payerDocument;

        @Column(name = "payer_name", nullable = false, length = 255)
        private String payerName;

        @Column(name = "payee_document", nullable = false, length = 50)
        private String payeeDocument;

        @Column(name = "payee_name", nullable = false, length = 255)
        private String payeeName;

        @Column(name = "idempotency_key", nullable = false, unique = true, length = 255)
        private String idempotencyKey;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20)
        private String status;

        @Column(name = "created_at", nullable = false)
        private Instant createdAt;

        @Column(name = "updated_at")
        private Instant updatedAt;

        @Column(name = "fraud_check_id", length = 255)
        private String fraudCheckId;

        @Column(name = "banking_core_transaction_id", length = 255)
        private String bankingCoreTransactionId;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public java.math.BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(java.math.BigDecimal amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPayerDocument() {
            return payerDocument;
        }

        public void setPayerDocument(String payerDocument) {
            this.payerDocument = payerDocument;
        }

        public String getPayerName() {
            return payerName;
        }

        public void setPayerName(String payerName) {
            this.payerName = payerName;
        }

        public String getPayeeDocument() {
            return payeeDocument;
        }

        public void setPayeeDocument(String payeeDocument) {
            this.payeeDocument = payeeDocument;
        }

        public String getPayeeName() {
            return payeeName;
        }

        public void setPayeeName(String payeeName) {
            this.payeeName = payeeName;
        }

        public String getIdempotencyKey() {
            return idempotencyKey;
        }

        public void setIdempotencyKey(String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }

        public Instant getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getFraudCheckId() {
            return fraudCheckId;
        }

        public void setFraudCheckId(String fraudCheckId) {
            this.fraudCheckId = fraudCheckId;
        }

        public String getBankingCoreTransactionId() {
            return bankingCoreTransactionId;
        }

        public void setBankingCoreTransactionId(String bankingCoreTransactionId) {
            this.bankingCoreTransactionId = bankingCoreTransactionId;
        }

        @PrePersist
        public void prePersist() {
            if (createdAt == null) {
                createdAt = Instant.now();
            }
        }
    }

    public interface PaymentRepository extends org.springframework.data.jpa.repository.JpaRepository<PaymentEntity, UUID> {
        Optional<PaymentEntity> findByIdempotencyKey(String idempotencyKey);
    }
}