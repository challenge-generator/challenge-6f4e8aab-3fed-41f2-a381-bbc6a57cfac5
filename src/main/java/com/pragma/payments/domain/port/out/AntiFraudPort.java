package com.pragma.payments.domain.port.out;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.infrastructure.exception.AntiFraudException;

/**
 * Puerto de salida para la integración con el motor antifraude.
 * Define el contrato que la infraestructura debe implementar para validar
 * transacciones contra el sistema de detección de fraude.
 */
public interface AntiFraudPort {

    /**
     * Valida una transacción contra el motor antifraude.
     *
     * @param payment el pago a validar
     * @return el identificador de la verificación antifraude
     * @throws AntiFraudException si la validación falla o el servicio no está disponible
     */
    String validateTransaction(Payment payment) throws AntiFraudException;

    /**
     * Verifica si el motor antifraude está disponible para procesar solicitudes.
     *
     * @return true si el servicio está disponible, false en caso contrario
     */
    boolean isAvailable();
}