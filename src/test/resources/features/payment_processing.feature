Feature: Procesamiento de Pagos en Tiempo Real

  Background:
    Given el sistema de pagos está disponible
    And el motor antifraude está operativo
    And el core bancario está disponible

  @payment @happy_path
  Scenario: Procesamiento de pago exitoso
    Given un pago con identificador único "PAY-2024-001" por monto de 1000.00
    And el pagador tiene fondos suficientes
    And el receptor está registrado en el sistema
    When se solicita el procesamiento del pago
    Then el sistema debe validar el pago contra el motor antifraude
    And el sistema debe enviar la transacción al core bancario
    And el estado del pago debe ser "APPROVED"
    And el sistema debe retornar el identificador de transacción del core bancario

  @payment @idempotency
  Scenario: Pago idempotente - retry con misma clave
    Given un pago con clave de idempotencia "IDEMP-KEY-123" por monto de 500.00
    And ya existe un pago procesado con esa clave
    When se intenta procesar nuevamente el mismo pago
    Then el sistema debe retornar el resultado del pago original
    And no debe crear un nuevo registro de pago
    And debe incrementar el contador de intentos de idempotencia

  @payment @idempotency
  Scenario: Pago nuevo con clave de idempotencia única
    Given un pago con clave de idempotencia "IDEMP-KEY-NEW-456" por monto de 750.00
    And no existe ningún pago con esa clave en el sistema
    When se procesa el pago
    Then debe crear un nuevo registro de pago
    And el estado debe ser "PROCESSING"

  @payment @antifraud_rejection
  Scenario: Pago rechazado por motor antifraude
    Given un pago por monto de 50000.00
    And el pagador está en la lista negra de fraude
    When se solicita el procesamiento del pago
    Then el sistema debe validar contra el motor antifraude
    And el motor antifraude debe rechazar la transacción
    And el estado del pago debe ser "REJECTED"
    And el sistema debe registrar el identificador de verificación antifraude
    And debe incluir la razón del rechazo: "HIGH_RISK_ACCOUNT"

  @payment @banking_rejection
  Scenario: Pago rechazado por el core bancario
    Given un pago por monto de 2000.00
    And el pagador tiene fondos insuficientes
    When se procesa el pago
    And el motor antifraude aprueba la transacción
    And el core bancario rechaza por fondos insuficientes
    Then el estado del pago debe ser "REJECTED"
    And debe registrar la razón del rechazo del core bancario

  @payment @timeout @resilience
  Scenario: Timeout en comunicación con antifraude
    Given un pago por monto de 1500.00
    And el motor antifraude no responde dentro del umbral de 500ms
    When se intenta procesar el pago
    Then el sistema debe aplicar circuit breaker al servicio de antifraude
    And debe retornar error de timeout
    And el pago debe quedar en estado "PENDING_RETRY"

  @payment @timeout @resilience
  Scenario: Timeout en comunicación con core bancario
    Given un pago por monto de 3000.00
    And el antifraude aprueba la transacción
    And el core bancario no responde dentro del umbral esperado
    When se procesa el pago
    Then debe aplicar circuit breaker al core bancario
    And debe manejar el timeout gracefully
    And el pago debe quedar en estado "PENDING_RETRY"

  @payment @validation
  Scenario: Validación de monto de pago
    Given un pago con monto negativo "-100.00"
    When se intenta validar el pago
    Then el sistema debe rechazar por monto inválido
    And debe retornar error de validación de negocio

  @payment @validation
  Scenario: Validación de umbral de procesamiento
    Given un pago que excede el umbral de procesamiento de 500ms
    When se mide el tiempo de procesamiento
    Then debe registrar una alerta de rendimiento
    And debe marcar el pago con flag de slow_processing

  @payment @resilience
  Scenario: Circuit breaker abierto después de fallos consecutivos
    Given el servicio de antifraude ha fallado 5 veces en los últimos 10 segundos
    When se intenta procesar un nuevo pago
    Then el circuit breaker debe estar en estado ABIERTO
    And debe rechazar inmediatamente las solicitudes
    And debe retornar fallback response

  @payment @recovery
  Scenario: Recuperación de pago después de fallo temporal
    Given un pago en estado "PENDING_RETRY"
    And el servicio de antifraude está recuperado
    When se reintenta el procesamiento
    Then debe completar exitosamente la validación
    And debe actualizar el estado a "APPROVED"