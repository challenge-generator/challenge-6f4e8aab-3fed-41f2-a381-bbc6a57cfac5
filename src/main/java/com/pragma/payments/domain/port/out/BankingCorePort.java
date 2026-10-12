package com.pragma.payments.domain.port.out;

import com.pragma.payments.infrastructure.exception.PaymentProcessingException;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de salida para la integración con el core bancario.
 * Define el contrato que la infraestructura debe implementar para ejecutar
 * transacciones en el sistema central del banco.
 */
public interface BankingCorePort {

    /**
     * Ejecuta una transacción de débto en el core bancario.
     *
     * @param accountNumber número de cuenta del beneficiario
     * @param amount monto a debitar
     * @param paymentReference referencia única del pago
     * @return el identificador de la transacción en el core bancario
     * @throws PaymentProcessingException si la transacción falla
     */
    String executeDebit(String accountNumber, BigDecimal amount, UUID paymentReference) throws PaymentProcessingException;

    /**
     * Revierte una transacción previamente ejecutada en el core bancario.
     *
     * @param transactionId identificador de la transacción a revertir
     * @param reason motivo de la reversión
     * @throws PaymentProcessingException si la reversión falla
     */
    void reverseTransaction(String transactionId, String reason) throws PaymentProcessingException;

    /**
     * Consulta el saldo disponible de una cuenta.
     *
     * @param accountNumber número de cuenta a consultar
     * @return saldo disponible
     * @throws PaymentProcessingException si la consulta falla
     */
    BigDecimal getAccountBalance(String accountNumber) throws PaymentProcessingException;
}