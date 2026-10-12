package com.pragma.payments.infrastructure.adapter.out.external;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.port.out.BankingCorePort;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BankingCoreAdapter implements BankingCorePort {

    private static final Logger log = LoggerFactory.getLogger(BankingCoreAdapter.class);
    private static final String SERVICE_NAME = "banking-core";

    private final RestTemplate restTemplate;
    private final String bankingCoreUrl;
    private final int connectionTimeout;
    private final int readTimeout;

    public BankingCoreAdapter(
            RestTemplate restTemplate,
            @Value("${external.banking-core.url:http://localhost:8081}") String bankingCoreUrl,
            @Value("${external.banking-core.timeout.connection:3000}") int connectionTimeout,
            @Value("${external.banking-core.timeout.read:5000}") int readTimeout) {
        this.restTemplate = restTemplate;
        this.bankingCoreUrl = bankingCoreUrl;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "processTransactionFallback")
    @Retry(name = SERVICE_NAME)
    public String processTransaction(Payment payment) {
        log.info("Enviando transacción al core bancario para pago: {}", payment.id());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", payment.idempotencyKey());
        headers.set("X-Request-Id", UUID.randomUUID().toString());

        Map<String, Object> requestBody = buildTransactionRequest(payment);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            String transactionUrl = bankingCoreUrl + "/api/v1/transactions";
            Map<String, Object> response = restTemplate.postForObject(
                    transactionUrl,
                    request,
                    Map.class
            );

            if (response != null && response.get("transactionId") != null) {
                String transactionId = response.get("transactionId").toString();
                log.info("Transacción procesada exitosamente. TransactionId: {}", transactionId);
                return transactionId;
            }

            throw new PaymentProcessingException("Respuesta inválida del core bancario");
        } catch (Exception e) {
            log.error("Error al procesar transacción en el core bancario: {}", e.getMessage());
            throw new PaymentProcessingException("Error de comunicación con el core bancario: " + e.getMessage(), e);
        }
    }

    private Map<String, Object> buildTransactionRequest(Payment payment) {
        Map<String, Object> request = new HashMap<>();
        request.put("amount", payment.amount().toString());
        request.put("currency", payment.currency());
        request.put("sourceAccount", payment.sourceAccount());
        request.put("destinationAccount", payment.destinationAccount());
        request.put("description", payment.description());
        request.put("idempotencyKey", payment.idempotencyKey());
        request.put("referenceId", payment.id().toString());
        request.put("timestamp", payment.createdAt().toString());
        return request;
    }

    private String processTransactionFallback(Payment payment, Throwable t) {
        log.warn("Fallback activado para el core bancario. Pago: {}, Error: {}", payment.id(), t.getMessage());
        throw new PaymentProcessingException(
                "El servicio de core bancario no está disponible. Por favor intente más tarde.",
                t
        );
    }

    public boolean validateAccount(String accountNumber) {
        log.debug("Validando cuenta: {}", accountNumber);
        try {
            String validateUrl = bankingCoreUrl + "/api/v1/accounts/" + accountNumber + "/validate";
            Map<String, Object> response = restTemplate.getForObject(validateUrl, Map.class);
            return response != null && Boolean.TRUE.equals(response.get("valid"));
        } catch (Exception e) {
            log.error("Error al validar cuenta {}: {}", accountNumber, e.getMessage());
            return false;
        }
    }

    public Map<String, Object> getAccountBalance(String accountNumber) {
        log.debug("Obteniendo saldo de cuenta: {}", accountNumber);
        try {
            String balanceUrl = bankingCoreUrl + "/api/v1/accounts/" + accountNumber + "/balance";
            return restTemplate.getForObject(balanceUrl, Map.class);
        } catch (Exception e) {
            log.error("Error al obtener saldo de cuenta {}: {}", accountNumber, e.getMessage());
            throw new PaymentProcessingException("Error al consultar saldo: " + e.getMessage(), e);
        }
    }
}