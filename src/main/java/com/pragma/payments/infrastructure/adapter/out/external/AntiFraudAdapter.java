package com.pragma.payments.infrastructure.adapter.out.external;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.out.AntiFraudPort;
import com.pragma.payments.infrastructure.exception.AntiFraudException;
import java.time.Duration;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AntiFraudAdapter implements AntiFraudPort {

    private static final Logger log = LoggerFactory.getLogger(AntiFraudAdapter.class);
    private static final String FRAUD_APPROVED_STATUS = "APPROVED";
    private static final String FRAUD_REJECTED_STATUS = "REJECTED";
    private static final Duration TIMEOUT = Duration.ofMillis(500);

    @Override
    public String validatePayment(Payment payment) {
        log.info("Initiating fraud validation for payment: {} amount: {} {}", 
                payment.id(), payment.amount(), payment.currency());

        long startTime = System.currentTimeMillis();
        String fraudCheckId = UUID.randomUUID().toString();

        try {
            validateInputParameters(payment);
            
            boolean isHighValueTransaction = payment.amount().compareTo(
                    new java.math.BigDecimal("10000")) > 0;
            boolean isSuspiciousPattern = validateSuspiciousPattern(payment);
            boolean isRestrictedPayer = validateRestrictedPayer(payment.payerDocument());

            String fraudStatus = determineFraudStatus(
                    isHighValueTransaction, 
                    isSuspiciousPattern, 
                    isRestrictedPayer
            );

            long processingTime = System.currentTimeMillis() - startTime;
            log.info("Fraud validation completed for payment: {} in {}ms. Status: {}", 
                    payment.id(), processingTime, fraudStatus);

            if (FRAUD_REJECTED_STATUS.equals(fraudStatus)) {
                throw new AntiFraudException("Transaction flagged as fraudulent by anti-fraud system");
            }

            return fraudCheckId;

        } catch (AntiFraudException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during fraud validation for payment: {}", payment.id(), e);
            throw new AntiFraudException("Fraud validation service temporarily unavailable", e);
        }
    }

    private void validateInputParameters(Payment payment) {
        if (payment == null) {
            throw new AntiFraudException("Payment cannot be null for fraud validation");
        }
        if (payment.payerDocument() == null || payment.payerDocument().isBlank()) {
            throw new AntiFraudException("Payer document is required for fraud validation");
        }
        if (payment.amount() == null || payment.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new AntiFraudException("Payment amount must be positive for fraud validation");
        }
    }

    private boolean validateSuspiciousPattern(Payment payment) {
        String payerDoc = payment.payerDocument();
        String payeeDoc = payment.payeeDocument();
        
        if (payerDoc != null && payeeDoc != null && payerDoc.equals(payeeDoc)) {
            log.warn("Suspicious pattern detected: payer and payee documents are identical");
            return true;
        }
        
        if (payment.amount().compareTo(new java.math.BigDecimal("50000")) > 0) {
            log.warn("Suspicious pattern detected: very high value transaction");
            return true;
        }
        
        return false;
    }

    private boolean validateRestrictedPayer(String payerDocument) {
        if (payerDocument == null) {
            return false;
        }
        
        String[] restrictedDocuments = {"00000000", "11111111", "22222222", 
                                        "33333333", "44444444", "55555555"};
        
        for (String restricted : restrictedDocuments) {
            if (payerDocument.startsWith(restricted)) {
                log.warn("Restricted payer document detected: {}", payerDocument);
                return true;
            }
        }
        
        return false;
    }

    private String determineFraudStatus(boolean isHighValue, boolean isSuspicious, boolean isRestricted) {
        if (isRestricted) {
            return FRAUD_REJECTED_STATUS;
        }
        
        if (isSuspicious && isHighValue) {
            return FRAUD_REJECTED_STATUS;
        }
        
        if (isHighValue) {
            log.info("High value transaction requires additional verification");
        }
        
        return FRAUD_APPROVED_STATUS;
    }
}