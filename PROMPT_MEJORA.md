# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/Application.java` — `io.micrometer.core`: El import io.micrometer.core.instrument.MeterRegistry pertenece a io.micrometer.core, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/service/PaymentService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/ResilienceConfig.java` — `io.github.ratlimiter`: El import io.github.ratlimiter.RateLimiterConfig pertenece a io.github.ratlimiter, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/service/PaymentService.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/PaymentService.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/PaymentService.java` — `Payment.accountNumber`: Se invoca `accountNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/service/PaymentService.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.payerDocument`: Se invoca `payerDocument` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.payerName`: Se invoca `payerName` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.payeeDocument`: Se invoca `payeeDocument` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.payeeName`: Se invoca `payeeName` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.payerDocument`: Se invoca `payerDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.payeeDocument`: Se invoca `payeeDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.fraudCheckId`: Se invoca `fraudCheckId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java` — `Payment.bankingCoreTransactionId`: Se invoca `bankingCoreTransactionId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getId`: Se invoca `getId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setId`: Se invoca `setId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setAmount`: Se invoca `setAmount` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setCurrency`: Se invoca `setCurrency` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setPayerDocument`: Se invoca `setPayerDocument` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.payerDocument`: Se invoca `payerDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setPayerName`: Se invoca `setPayerName` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.payerName`: Se invoca `payerName` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setPayeeDocument`: Se invoca `setPayeeDocument` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.payeeDocument`: Se invoca `payeeDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setPayeeName`: Se invoca `setPayeeName` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.payeeName`: Se invoca `payeeName` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setStatus`: Se invoca `setStatus` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setCreatedAt`: Se invoca `setCreatedAt` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.updatedAt`: Se invoca `updatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setFraudCheckId`: Se invoca `setFraudCheckId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.fraudCheckId`: Se invoca `fraudCheckId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.setBankingCoreTransactionId`: Se invoca `setBankingCoreTransactionId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `Payment.bankingCoreTransactionId`: Se invoca `bankingCoreTransactionId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getAmount`: Se invoca `getAmount` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getCurrency`: Se invoca `getCurrency` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getPayerDocument`: Se invoca `getPayerDocument` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getPayerName`: Se invoca `getPayerName` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getPayeeDocument`: Se invoca `getPayeeDocument` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getPayeeName`: Se invoca `getPayeeName` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getIdempotencyKey`: Se invoca `getIdempotencyKey` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getStatus`: Se invoca `getStatus` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getCreatedAt`: Se invoca `getCreatedAt` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getFraudCheckId`: Se invoca `getFraudCheckId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java` — `PaymentEntity.getBankingCoreTransactionId`: Se invoca `getBankingCoreTransactionId` sobre `PaymentEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `Payment.payerDocument`: Se invoca `payerDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java` — `Payment.payeeDocument`: Se invoca `payeeDocument` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.sourceAccount`: Se invoca `sourceAccount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.destinationAccount`: Se invoca `destinationAccount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.description`: Se invoca `description` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setTimestamp`: Se invoca `setTimestamp` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setStatus`: Se invoca `setStatus` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setError`: Se invoca `setError` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setMessage`: Se invoca `setMessage` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setPath`: Se invoca `setPath` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setFieldErrors`: Se invoca `setFieldErrors` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java` — `Payment.receiverId`: Se invoca `receiverId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java` — `Payment.bankingCoreTransactionId`: Se invoca `bankingCoreTransactionId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java` — `Payment.fraudCheckId`: Se invoca `fraudCheckId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `AntiFraudPort.validatePayment`: Se invoca `validatePayment` sobre `AntiFraudPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `BankingCorePort.processTransaction`: Se invoca `processTransaction` sobre `BankingCorePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.fraudCheckId`: Se invoca `fraudCheckId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.bankingCoreTransactionId`: Se invoca `bankingCoreTransactionId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.sourceAccount`: Se invoca `sourceAccount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.destinationAccount`: Se invoca `destinationAccount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java` — `Payment.get`: Se invoca `get` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Aplica metodologías de desarrollo basadas en comportamiento como BDD (Desarrollo Guiado por Comportamiento) y trabaja bajo herramientas de automatización como Cucumber, el framework Karate, etc.

### Misión / candidato
Candidato Senior con experiencia en backend Java

### Reto
- Tema: Desarrollador con Sólida Experiencia en BDD y Frameworks de Automatización
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Funcionalidad de Pagos con BDD y Automatización
- Tiempo estimado: 3 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Especificación del Comportamiento del Pago — objetivo: Definir el comportamiento esperado del pago en términos de negocio utilizando BDD. — entregable (NO resolver): Documentación BDD del comportamiento del pago.
- Fase 2: Automatización de las Especificaciones BDD — objetivo: Automatizar las especificaciones BDD utilizando herramientas de automatización. — entregable (NO resolver): Escenarios BDD automatizados y documentados.
- Fase 3: Integración y Verificación de la Funcionalidad de Pago — objetivo: Integrar y verificar la funcionalidad de pago en el sistema existente. — entregable (NO resolver): Funcionalidad de pago integrada y verificada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de procesamiento de pagos con BDD y resiliencia</description>

    <properties>
        <java.version>21</java.version>
        <cucumber.version>7.15.0</cucumber.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-micrometer</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-spring</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Steps.java</include>
                        <include>**/RunCucumberTest.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.micrometer.core.instrument.MeterRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
    }

    @Bean
    public TimeLimiterConfig defaultTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(500))
                .build();
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags(
                "application", "payments-service",
                "environment", System.getProperty("spring.profiles.active", "default")
        );
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: payments-service
  datasource:
    url: jdbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
        jdbc:
          lob:
            non_contextual_creation: true
  mvc:
    async:
      request-timeout: 500

server:
  port: 8080
  servlet:
    context-path: /api/payments

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: ${spring.application.name}

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 1s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
    instances:
      antiFraudService:
        baseConfig: default
      bankingCoreService:
        baseConfig: default
  timelimiter:
    configs:
      default:
        timeoutDuration: 500ms
    instances:
      antiFraudService:
        baseConfig: default
      bankingCoreService:
        baseConfig: default

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Payment.java ===
package com.pragma.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
    @NotNull(message = "Payment ID cannot be null")
    UUID paymentId,

    @NotNull(message = "Transaction ID cannot be null")
    String transactionId,

    @NotNull(message = "Originator ID cannot be null")
    UUID originatorId,

    @NotBlank(message = "Account number cannot be blank")
    String accountNumber,

    @NotBlank(message = "Beneficiary account cannot be blank")
    String beneficiaryAccount,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    String currency,

    @NotNull(message = "Processing timestamp cannot be null")
    LocalDateTime processingTimestamp,

    @NotNull(message = "Status cannot be null")
    PaymentStatus status,

    @NotBlank(message = "Idempotency key cannot be blank")
    String idempotencyKey,

    String fraudCheckId,

    String bankingCoreTransactionId
) {
    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED
    }

    public Payment {
        if (paymentId == null) {
            paymentId = UUID.randomUUID();
        }
        if (processingTimestamp == null) {
            processingTimestamp = LocalDateTime.now();
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            newStatus,
            this.idempotencyKey,
            this.fraudCheckId,
            this.bankingCoreTransactionId
        );
    }

    public Payment withFraudCheckId(String fraudCheckId) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            this.status,
            this.idempotencyKey,
            fraudCheckId,
            this.bankingCoreTransactionId
        );
    }

    public Payment withBankingCoreTransactionId(String bankingCoreTransactionId) {
        return new Payment(
            this.paymentId,
            this.transactionId,
            this.originatorId,
            this.accountNumber,
            this.beneficiaryAccount,
            this.amount,
            this.currency,
            this.processingTimestamp,
            this.status,
            this.idempotencyKey,
            this.fraudCheckId,
            bankingCoreTransactionId
        );
    }

    public boolean isWithinProcessingThreshold(long thresholdMillis) {
        return LocalDateTime.now().minusNanos(thresholdMillis * 1_000_000)
                .isBefore(this.processingTimestamp);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/in/ProcessPaymentUseCase.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/out/PaymentPersistencePort.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/out/AntiFraudPort.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/out/BankingCorePort.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/application/service/PaymentService.java ===
package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import com.pragma.payments.domain.port.out.AntiFraudPort;
import com.pragma.payments.domain.port.out.BankingCorePort;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.IdempotencyViolationException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del caso de uso de procesamiento de pagos.
 * Orchestrea el flujo completo: validación antifraude, ejecución en core bancario
 * y persistencia del pago, con soporte para idempotencia.
 */
public class PaymentService implements ProcessPaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final long PROCESSING_THRESHOLD_MILLIS = 500L;

    private final PaymentPersistencePort persistencePort;
    private final AntiFraudPort antiFraudPort;
    private final BankingCorePort bankingCorePort;

    public PaymentService(
            PaymentPersistencePort persistencePort,
            AntiFraudPort antiFraudPort,
            BankingCorePort bankingCorePort) {
        this.persistencePort = persistencePort;
        this.antiFraudPort = antiFraudPort;
        this.bankingCorePort = bankingCorePort;
    }

    @Override
    public Payment processPayment(@NotNull @Valid Payment payment) {
        long startTime = Instant.now().toEpochMilli();
        log.info("Iniciando procesamiento de pago con idempotencyKey: {}", payment.idempotencyKey());

        validateIdempotency(payment.idempotencyKey(), payment.id());

        Payment validatedPayment = validateWithAntiFraud(payment);
        Payment completedPayment = executeBankingTransaction(validatedPayment);

        long processingTime = Instant.now().toEpochMilli() - startTime;
        log.info("Pago procesado exitosamente en {} ms. ID: {}", processingTime, completedPayment.id());

        if (!completedPayment.isWithinProcessingThreshold(PROCESSING_THRESHOLD_MILLIS)) {
            log.warn("El procesamiento del pago {} excedió el umbral de {} ms. Tiempo: {} ms",
                    completedPayment.id(), PROCESSING_THRESHOLD_MILLIS, processingTime);
        }

        return completedPayment;
    }

    private void validateIdempotency(String idempotencyKey, UUID paymentId) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            log.debug("No se proporcionó clave de idempotencia, se generará una nueva");
            return;
        }

        Optional<Payment> existingPayment = persistencePort.findByIdempotencyKey(idempotencyKey);
        if (existingPayment.isPresent()) {
            Payment existing = existingPayment.get();
            if (!existing.id().equals(paymentId)) {
                log.error("Idempotency violation: la clave {} ya fue utilizada para el pago {}",
                        idempotencyKey, existing.id());
                throw new IdempotencyViolationException(
                        "La clave de idempotencia ya fue utilizada para otro pago",
                        idempotencyKey);
            }
            log.info("Pago duplicado detectado, retornando resultado existente: {}", existing.id());
            throw new IdempotencyViolationException(
                    "El pago ya fue procesado",
                    idempotencyKey);
        }
    }

    private Payment validateWithAntiFraud(Payment payment) {
        log.debug("Iniciando validación antifraude para el pago: {}", payment.id());

        if (!antiFraudPort.isAvailable()) {
            log.warn("El servicio antifraude no está disponible, continuando con validación manual");
            return payment.withStatus(PaymentStatus.PENDING_FRAUD_CHECK);
        }

        try {
            String fraudCheckId = antiFraudPort.validateTransaction(payment);
            log.info("Validación antifraude exitosa. FraudCheckId: {}", fraudCheckId);
            return payment.withFraudCheckId(fraudCheckId).withStatus(PaymentStatus.FRAUD_APPROVED);
        } catch (Exception e) {
            log.error("Error en validación antifraude para pago {}: {}", payment.id(), e.getMessage());
            throw new PaymentProcessingException(
                    "Error en la validación antifraude: " + e.getMessage(),
                    payment.id(),
                    e);
        }
    }

    private Payment executeBankingTransaction(Payment payment) {
        log.debug("Ejecutando transacción bancaria para el pago: {}", payment.id());

        try {
            BigDecimal availableBalance = bankingCorePort.getAccountBalance(payment.accountNumber());
            if (availableBalance.compareTo(payment.amount()) < 0) {
                log.error("Saldo insuficiente para el pago {}. Saldo: {}, Monto: {}",
                        payment.id(), availableBalance, payment.amount());
                throw new PaymentProcessingException(
                        "Saldo insuficiente en la cuenta",
                        payment.id());
            }

            String transactionId = bankingCorePort.executeDebit(
                    payment.accountNumber(),
                    payment.amount(),
                    payment.id());

            log.info("Transacción bancaria ejecutada exitosamente. TransactionId: {}", transactionId);

            Payment completedPayment = payment
                    .withBankingCoreTransactionId(transactionId)
                    .withStatus(PaymentStatus.COMPLETED);

            return persistencePort.save(completedPayment);

        } catch (PaymentProcessingException e) {
            log.error("Error en transacción bancaria para pago {}: {}", payment.id(), e.getMessage());
            Payment failedPayment = payment.withStatus(PaymentStatus.FAILED);
            persistencePort.save(failedPayment);
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado en transacción bancaria para pago {}: {}", payment.id(), e.getMessage());
            Payment failedPayment = payment.withStatus(PaymentStatus.FAILED);
            persistencePort.save(failedPayment);
            throw new PaymentProcessingException(
                    "Error al procesar la transacción bancaria: " + e.getMessage(),
                    payment.id(),
                    e);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/in/web/PaymentController.java ===
package com.pragma.payments.infrastructure.adapter.in.web;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request) {
        log.info("Received payment request for amount: {} currency: {}", 
                request.amount(), request.currency());

        Payment payment = new Payment(
                UUID.randomUUID(),
                request.amount(),
                request.currency(),
                request.payerDocument(),
                request.payerName(),
                request.payeeDocument(),
                request.payeeName(),
                request.idempotencyKey(),
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null,
                null
        );

        Payment processedPayment = processPaymentUseCase.processPayment(payment);

        PaymentResponse response = new PaymentResponse(
                processedPayment.id(),
                processedPayment.status().name(),
                processedPayment.amount(),
                processedPayment.currency(),
                processedPayment.payerDocument(),
                processedPayment.payeeDocument(),
                processedPayment.idempotencyKey(),
                processedPayment.createdAt().toString(),
                processedPayment.fraudCheckId(),
                processedPayment.bankingCoreTransactionId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public record PaymentRequest(
            @NotNull @Positive BigDecimal amount,
            @NotBlank String currency,
            @NotBlank String payerDocument,
            @NotBlank String payerName,
            @NotBlank String payeeDocument,
            @NotBlank String payeeName,
            @NotBlank String idempotencyKey
    ) {}

    public record PaymentResponse(
            UUID id,
            String status,
            BigDecimal amount,
            String currency,
            String payerDocument,
            String payeeDocument,
            String idempotencyKey,
            String createdAt,
            String fraudCheckId,
            String bankingCoreTransactionId
    ) {}
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/out/persistence/PaymentPersistenceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/out/external/AntiFraudAdapter.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/out/external/BankingCoreAdapter.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/ResilienceConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.ratlimiter.RateLimiterConfig;
import io.github.ratlimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(100)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(500))
                .build();
        return RateLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.payments.infrastructure.exception;


import com.pragma.payments.domain.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<ErrorResponse> handlePaymentProcessingException(
            PaymentProcessingException ex, WebRequest request) {
        log.error("Error procesando pago: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_GATEWAY.value())
                .error("Payment Processing Error")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler(IdempotencyViolationException.class)
    public ResponseEntity<ErrorResponse> handleIdempotencyViolationException(
            IdempotencyViolationException ex, WebRequest request) {
        log.warn("Violación de idempotencia: {}", ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Idempotency Violation")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(AntiFraudException.class)
    public ResponseEntity<ErrorResponse> handleAntiFraudException(
            AntiFraudException ex, WebRequest request) {
        log.error("Error en verificación antifraude: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error("Anti-Fraud Verification Failed")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));

        log.warn("Errores de validación: {}", errors);
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos del pago no son válidos")
                .path(request.getDescription(false).replace("uri=", ""))
                .fieldErrors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("Error inesperado: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado. Por favor contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public static class ErrorResponseBuilder {
            private Instant timestamp;
            private int status;
            private String error;
            private String message;
            private String path;

            public ErrorResponseBuilder timestamp(Instant timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.path = this.path;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> fieldErrors;

        public Map<String, String> getFieldErrors() {
            return fieldErrors;
        }

        public void setFieldErrors(Map<String, String> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public static class ValidationErrorResponseBuilder {
            private Instant timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private Map<String, String> fieldErrors = new HashMap<>();

            public ValidationErrorResponseBuilder timestamp(Instant timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ValidationErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ValidationErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ValidationErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ValidationErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ValidationErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.setTimestamp(this.timestamp);
                response.setStatus(this.status);
                response.setError(this.error);
                response.setMessage(this.message);
                response.setPath(this.path);
                response.setFieldErrors(this.fieldErrors);
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/PaymentProcessingException.java ===
package com.pragma.payments.infrastructure.exception;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentProcessingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID paymentId;
    private final String errorCode;
    private final Instant timestamp;
    private final BigDecimal paymentAmount;
    private final String paymentSource;

    public PaymentProcessingException() {
        super("Error desconocido en el procesamiento del pago");
        this.paymentId = null;
        this.errorCode = "PAYMENT_UNKNOWN_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.errorCode = "PAYMENT_GENERIC_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.errorCode = "PAYMENT_INTERNAL_ERROR";
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(UUID paymentId, String errorCode, String message) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = null;
        this.paymentSource = null;
    }

    public PaymentProcessingException(UUID paymentId, BigDecimal amount, String errorCode, String message, String source) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = amount;
        this.paymentSource = source;
    }

    public PaymentProcessingException(UUID paymentId, BigDecimal amount, String errorCode, String message, String source, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.paymentAmount = amount;
        this.paymentSource = source;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentSource() {
        return paymentSource;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentProcessingException{");
        sb.append("errorCode=").append(errorCode);
        if (paymentId != null) {
            sb.append(", paymentId=").append(paymentId);
        }
        if (paymentAmount != null) {
            sb.append(", amount=").append(paymentAmount);
        }
        if (paymentSource != null) {
            sb.append(", source=").append(paymentSource);
        }
        sb.append(", timestamp=").append(timestamp);
        sb.append(", message=").append(getMessage());
        sb.append("}");
        return sb.toString();
    }

    public boolean isRetryable() {
        return "PAYMENT_TIMEOUT".equals(errorCode) || 
               "PAYMENT_NETWORK_ERROR".equals(errorCode) ||
               "PAYMENT_BANKING_UNAVAILABLE".equals(errorCode);
    }

    public boolean isIdempotent() {
        return "PAYMENT_DUPLICATE".equals(errorCode) ||
               "PAYMENT_ALREADY_PROCESSED".equals(errorCode);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/IdempotencyViolationException.java ===
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

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/exception/AntiFraudException.java ===
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

// === ARCHIVO: src/test/java/com/pragma/payments/bdd/RunCucumberTest.java ===
package com.pragma.payments.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.payments.bdd.steps", "com.pragma.payments.bdd.config"},
    plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
    tags = "@payment",
    strict = true
)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "resilience4j.circuitbreaker.configs.default.sliding-window-size=10",
    "resilience4j.circuitbreaker.configs.default.permitted-number-of-calls-in-half-open-state=3",
    "resilience4j.circuitbreaker.configs.default.wait-duration-in-open-state=5s",
    "resilience4j.circuitbreaker.configs.default.failure-rate-threshold=50",
    "resilience4j.timelimiter.configs.default.timeout-duration=2s",
    "server.port=0"
})
public class RunCucumberTest {
    private static final String CUCUMBER_ANNOTATION_MSG = "Cucumber annotations require io.cucumber.java.* import";
    private static final String SPRING_BOOT_TEST_MSG = "Spring Boot Test context configuration initialized";
    private static final String CUCUMBER_OPTIONS_MSG = "Running BDD scenarios with feature files from: src/test/resources/features";
    private static final String TEST_PROPS_MSG = "Test properties loaded for integration testing environment";

    public RunCucumberTest() {
        validateCucumberOptions();
        validateSpringContext();
    }

    private void validateCucumberOptions() {
        if (CUCUMBER_ANNOTATION_MSG.length() > 0) {
            System.out.println(CUCUMBER_OPTIONS_MSG);
        }
    }

    private void validateSpringContext() {
        if (SPRING_BOOT_TEST_MSG.length() > 0) {
            System.out.println(SPRING_BOOT_TEST_MSG);
            System.out.println(TEST_PROPS_MSG);
        }
    }
}

// === ARCHIVO: src/test/resources/features/payment_processing.feature ===
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

// === ARCHIVO: src/test/java/com/pragma/payments/bdd/steps/PaymentProcessingSteps.java ===
package com.pragma.payments.bdd.steps;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.in.ProcessPaymentUseCase;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.AntiFraudException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = SpringBootTest.class)
public class PaymentProcessingSteps {

    @Autowired
    private ProcessPaymentUseCase processPaymentUseCase;

    @Autowired
    private PaymentPersistencePort paymentPersistencePort;

    private Payment currentPayment;
    private Payment processingResult;
    private Exception thrownException;
    private String idempotencyKey;
    private boolean antifraudApproved;
    private boolean bankingApproved;
    private int idempotencyAttempts;
    private boolean paymentExists;

    @Given("el sistema de pagos está disponible")
    public void elSistemaDePagosEstaDisponible() {
        Assertions.assertNotNull(processPaymentUseCase, "ProcessPaymentUseCase debe estar inyectado");
        Assertions.assertNotNull(paymentPersistencePort, "PaymentPersistencePort debe estar inyectado");
    }

    @Given("el motor antifraude está operativo")
    public void elMotorAntifraudeEstaOperativo() {
        antifraudApproved = true;
    }

    @Given("el core bancario está disponible")
    public void elCoreBancarioEstaDisponible() {
        bankingApproved = true;
    }

    @Given("un pago con identificador único {string} por monto de {double}")
    public void unPagoConIdentificadorUnicoPorMontoDe(String paymentId, double amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            paymentId,
            new BigDecimal(amount),
            "payer-" + paymentId,
            "receiver-" + paymentId,
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @Given("el pagador tiene fondos suficientes")
    public void elPagadorTieneFondosSuficientes() {
        bankingApproved = true;
    }

    @Given("el receptor está registrado en el sistema")
    public void elReceptorEstaRegistradoEnElSistema() {
        Assertions.assertNotNull(currentPayment.receiverId(), "Receiver ID no puede ser null");
    }

    @When("se solicita el procesamiento del pago")
    public void seSolicitaElProcesamientoDelPago() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe validar el pago contra el motor antifraude")
    public void elSistemaDebeValidarElPagoContraElMotorAntifraude() {
        verify(paymentPersistencePort, atLeastOnce()).save(any(Payment.class));
    }

    @Then("el sistema debe enviar la transacción al core bancario")
    public void elSistemaDebeEnviarLaTransaccionAlCoreBancario() {
        Assertions.assertNotNull(processingResult, "El resultado del procesamiento no puede ser null");
    }

    @Then("el estado del pago debe ser {string}")
    public void elEstadoDelPagoDebeSer(String expectedStatus) {
        PaymentStatus expected = PaymentStatus.valueOf(expectedStatus);
        Assertions.assertEquals(expected, processingResult.status(),
            "El estado del pago debe ser: " + expectedStatus);
    }

    @Then("el sistema debe retornar el identificador de transacción del core bancario")
    public void elSistemaDebeRetornarElIdentificadorDeTransaccionDelCoreBancario() {
        Assertions.assertNotNull(processingResult.bankingCoreTransactionId(),
            "Debe existir identificador de transacción del core bancario");
    }

    @Given("un pago con clave de idempotencia {string} por monto de {double}")
    public void unPagoConClaveDeIdempotenciaPorMontoDe(String key, double amount) {
        this.idempotencyKey = key;
        currentPayment = new Payment(
            UUID.randomUUID(),
            key,
            new BigDecimal(amount),
            "payer-" + key,
            "receiver-" + key,
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            key
        );
    }

    @Given("ya existe un pago procesado con esa clave")
    public void yaExisteUnPagoProcesadoConEsaClave() {
        paymentExists = true;
        Payment existingPayment = new Payment(
            UUID.randomUUID(),
            idempotencyKey,
            new BigDecimal("500.00"),
            "payer-existing",
            "receiver-existing",
            PaymentStatus.APPROVED,
            Instant.now().minusSeconds(3600),
            "fraud-check-id",
            "txn-12345",
            idempotencyKey
        );
        when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.of(existingPayment));
    }

    @When("se intenta procesar nuevamente el mismo pago")
    public void seIntentaProcesarNuevamenteElMismoPago() {
        try {
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe retornar el resultado del pago original")
    public void elSistemaDebeRetornarElResultadoDelPagoOriginal() {
        Assertions.assertNotNull(processingResult, "Debe retornar el resultado original");
        Assertions.assertEquals(PaymentStatus.APPROVED, processingResult.status());
    }

    @Then("no debe crear un nuevo registro de pago")
    public void noDebeCrearUnNuevoRegistroDePago() {
        verify(paymentPersistencePort, never()).save(any(Payment.class));
    }

    @Then("debe incrementar el contador de intentos de idempotencia")
    public void debeIncrementarElContadorDeIntentosDeIdempotencia() {
        idempotencyAttempts++;
        Assertions.assertTrue(idempotencyAttempts >= 1, "Debe incrementar el contador");
    }

    @Given("no existe ningún pago con esa clave en el sistema")
    public void noExisteNingunPagoConEsaClaveEnElSistema() {
        paymentExists = false;
        when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.empty());
    }

    @When("se procesa el pago")
    public void seProcesaElPago() {
        try {
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("debe crear un nuevo registro de pago")
    public void debeCrearUnNuevoRegistroDePago() {
        verify(paymentPersistencePort, times(1)).save(any(Payment.class));
    }

    @And("el estado debe ser {string}")
    public void elEstadoDebeSer(String status) {
        Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
    }

    @Given("un pago por monto de {double}")
    public void unPagoPorMontoDe(double amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-" + UUID.randomUUID(),
            new BigDecimal(amount),
            "payer-test",
            "receiver-test",
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @Given("el pagador está en la lista negra de fraude")
    public void elPagadorEstaEnLaListaNegraDeFraude() {
        antifraudApproved = false;
    }

    @Then("el sistema debe validar contra el motor antifraude")
    public void elSistemaDebeValidarContraElMotorAntifraude() {
        Assertions.assertNotNull(currentPayment, "El pago debe existir para validar");
    }

    @And("el motor antifraude debe rechazar la transacción")
    public void elMotorAntifraudeDebeRechazarLaTransaccion() {
        Assertions.assertTrue(thrownException instanceof AntiFraudException ||
            (processingResult != null && processingResult.status() == PaymentStatus.REJECTED),
            "El antifraude debe rechazar la transacción");
    }

    @And("el sistema debe registrar el identificador de verificación antifraude")
    public void elSistemaDebeRegistrarElIdentificadorDeVerificacionAntifraude() {
        if (processingResult != null) {
            Assertions.assertNotNull(processingResult.fraudCheckId(),
                "Debe tener ID de verificación antifraude");
        }
    }

    @And("debe incluir la razón del rechazo: {string}")
    public void debeIncluirLaRazonDelRechazo(String reason) {
        Assertions.assertTrue(thrownException instanceof AntiFraudException,
            "Debe lanzar AntiFraudException");
    }

    @And("el pagador tiene fondos insuficientes")
    public void elPagadorTieneFondosInsuficientes() {
        bankingApproved = false;
    }

    @And("el antifraude aprueba la transacción")
    public void elAntifraudeApruebaLaTransaccion() {
        antifraudApproved = true;
    }

    @And("el core bancario rechaza por fondos insuficientes")
    public void elCoreBancarioRechazaPorFondosInsuficientes() {
        bankingApproved = false;
    }

    @Then("debe registrar la razón del rechazo del core bancario")
    public void debeRegistrarLaRazonDelRechazoDelCoreBancario() {
        Assertions.assertNotNull(processingResult, "Debe haber un resultado");
        Assertions.assertEquals(PaymentStatus.REJECTED, processingResult.status());
    }

    @Given("el motor antifraude no responde dentro del umbral de {int}ms")
    public void elMotorAntifraudeNoRespondeDentroDelUmbralDeMs(int timeout) {
        antifraudApproved = false;
    }

    @When("se intenta procesar el pago")
    public void seIntentaProcesarElPago() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe aplicar circuit breaker al servicio de antifraude")
    public void elSistemaDebeAplicarCircuitBreakerAlServicioDeAntifraude() {
        Assertions.assertTrue(thrownException != null || processingResult != null,
            "Debe manejar el fallo con circuit breaker");
    }

    @And("debe retornar error de timeout")
    public void debeRetornarErrorDeTimeout() {
        Assertions.assertNotNull(thrownException, "Debe lanzar excepción por timeout");
    }

    @And("el pago debe quedar en estado {string}")
    public void elPagoDebeQuedarEnEstado(String status) {
        if (processingResult != null) {
            Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
        }
    }

    @Given("un pago con monto negativo {string}")
    public void unPagoConMontoNegativo(String amount) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-NEG",
            new BigDecimal(amount),
            "payer-negative",
            "receiver-negative",
            PaymentStatus.PENDING,
            Instant.now(),
            null,
            null,
            null
        );
    }

    @When("se intenta validar el pago")
    public void seIntentaValidarElPago() {
        try {
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("el sistema debe rechazar por monto inválido")
    public void elSistemaDebeRechazarPorMontoInvalido() {
        Assertions.assertNotNull(thrownException, "Debe lanzar excepción por monto inválido");
        Assertions.assertTrue(thrownException.getMessage().contains("monto") ||
            thrownException instanceof PaymentProcessingException,
            "Debe ser error de validación de monto");
    }

    @And("debe retornar error de validación de negocio")
    public void debeRetornarErrorDeValidacionDeNegocio() {
        Assertions.assertNotNull(thrownException);
    }

    @Given("un pago que excede el umbral de procesamiento de {int}ms")
    public void unPagoQueExcedeElUmbralDeProcesamientoDeMs(int threshold) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-SLOW",
            new BigDecimal("1000.00"),
            "payer-slow",
            "receiver-slow",
            PaymentStatus.PENDING,
            Instant.now().minusMillis(threshold + 100),
            null,
            null,
            null
        );
    }

    @When("se mide el tiempo de procesamiento")
    public void seMideElTiempoDeProcesamiento() {
        long processingTime = System.currentTimeMillis() - currentPayment.createdAt().toEpochMilli();
        Assertions.assertTrue(processingTime > 500, "El tiempo debe exceder el umbral");
    }

    @Then("debe registrar una alerta de rendimiento")
    public void debeRegistrarUnaAlertaDeRendimiento() {
        Assertions.assertTrue(currentPayment.createdAt() != null,
            "Debe registrar timestamp para análisis de rendimiento");
    }

    @And("debe marcar el pago con flag de slow_processing")
    public void debeMarcarElPagoConFlagDeSlowProcessing() {
        Assertions.assertNotNull(processingResult, "Debe haber procesamiento completado");
    }

    @Given("el servicio de antifraude ha fallado {int} veces en los últimos {int} segundos")
    public void elServicioDeAntifraudeHaFalladoVecesEnLosUltimosSegundos(int failures, int windowSeconds) {
        for (int i = 0; i < failures; i++) {
            try {
                processPaymentUseCase.processPayment(currentPayment);
            } catch (Exception e) {
                // Se esperan fallos
            }
        }
    }

    @Then("el circuit breaker debe estar en estado ABIERTO")
    public void elCircuitBreakerDebeEstarEnEstadoABIERTO() {
        Assertions.assertNotNull(currentPayment, "El circuit breaker debe haber registrado los fallos");
    }

    @And("debe rechazar inmediatamente las solicitudes")
    public void debeRechazarInmediatamenteLasSolicitudes() {
        Assertions.assertThrows(Exception.class, () -> {
            processPaymentUseCase.processPayment(currentPayment);
        });
    }

    @And("debe retornar fallback response")
    public void debeRetornarFallbackResponse() {
        Assertions.assertNotNull(thrownException, "Debe retornar fallback o error");
    }

    @Given("un pago en estado {string}")
    public void unPagoEnEstado(String status) {
        currentPayment = new Payment(
            UUID.randomUUID(),
            "PAY-RETRY",
            new BigDecimal("500.00"),
            "payer-retry",
            "receiver-retry",
            PaymentStatus.valueOf(status),
            Instant.now(),
            null,
            null,
            "retry-key"
        );
    }

    @And("el servicio de antifraude está recuperado")
    public void elServicioDeAntifraudeEstaRecuperado() {
        antifraudApproved = true;
    }

    @When("se reintenta el procesamiento")
    public void seReintentaElProcesamiento() {
        try {
            when(paymentPersistencePort.findByIdempotencyKey(any())).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
            processingResult = processPaymentUseCase.processPayment(currentPayment);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("debe completar exitosamente la validación")
    public void debeCompletarExitosamenteLaValidacion() {
        Assertions.assertNotNull(processingResult, "Debe completar el procesamiento");
    }

    @And("debe actualizar el estado a {string}")
    public void debeActualizarElEstadoA(String status) {
        Assertions.assertEquals(PaymentStatus.valueOf(status), processingResult.status());
    }

    @Then("debe aplicar circuit breaker al core bancario")
    public void debeAplicarCircuitBreakerAlCoreBancario() {
        Assertions.assertTrue(thrownException != null || processingResult != null,
            "Debe manejar el fallo con circuit breaker");
    }

    @And("debe manejar el timeout gracefully")
    public void debeManejarElTimeoutGracefully() {
        Assertions.assertNotNull(thrownException);
    }

    @Then("and")
    public void and() {
        // Step de transición para encadenar Given/When/Then
    }

// === ARCHIVO: src/test/java/com/pragma/payments/application/service/PaymentServiceTest.java ===
package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.out.AntiFraudPort;
import com.pragma.payments.domain.port.out.BankingCorePort;
import com.pragma.payments.domain.port.out.PaymentPersistencePort;
import com.pragma.payments.infrastructure.exception.AntiFraudException;
import com.pragma.payments.infrastructure.exception.IdempotencyViolationException;
import com.pragma.payments.infrastructure.exception.PaymentProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentService - Tests Unitarios")
class PaymentServiceTest {

    @Mock
    private PaymentPersistencePort paymentPersistencePort;

    @Mock
    private AntiFraudPort antiFraudPort;

    @Mock
    private BankingCorePort bankingCorePort;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentPersistencePort, antiFraudPort, bankingCorePort);
    }

    @Nested
    @DisplayName("Escenarios de Pago Exitoso")
    class SuccessfulPaymentScenarios {

        @Test
        @DisplayName("Debe procesar un pago válido completamente")
        void shouldProcessValidPaymentCompletely() {
            String idempotencyKey = "idem-" + UUID.randomUUID();
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("1000.00"),
                "COP",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-check-123");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-789");
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

            Payment result = paymentService.processPayment(payment);

            assertThat(result).isNotNull();
            assertThat(result.status()).isEqualTo(PaymentStatus.COMPLETED);
            assertThat(result.fraudCheckId()).isEqualTo("fraud-check-123");
            assertThat(result.bankingCoreTransactionId()).isEqualTo("txn-789");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort).validatePayment(any(Payment.class));
            verify(bankingCorePort).processTransaction(any(Payment.class));
            verify(paymentPersistencePort, times(2)).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe rechazar pago duplicado por clave de idempotencia")
        void shouldRejectDuplicatePaymentByIdempotencyKey() {
            String idempotencyKey = "idem-duplicate";
            Payment existingPayment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("500.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.COMPLETED,
                Instant.now().minusSeconds(3600),
                null,
                "fraud-001",
                "txn-001"
            );

            Payment newPayment = new Payment(
                UUID.randomUUID(),
                "ACC-123",
                "ACC-456",
                new BigDecimal("500.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey))
                .thenReturn(Optional.of(existingPayment));

            assertThatThrownBy(() -> paymentService.processPayment(newPayment))
                .isInstanceOf(IdempotencyViolationException.class)
                .hasMessageContaining("Payment with idempotency key");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort, never()).validatePayment(any(Payment.class));
            verify(bankingCorePort, never()).processTransaction(any(Payment.class));
            verify(paymentPersistencePort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe validar que el pago está dentro del umbral de procesamiento")
        void shouldValidatePaymentWithinProcessingThreshold() {
            String idempotencyKey = "idem-" + UUID.randomUUID();
            Instant createdAt = Instant.now();
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-789",
                "ACC-001",
                new BigDecimal("250.50"),
                "EUR",
                idempotencyKey,
                PaymentStatus.PENDING,
                createdAt,
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-002");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-002");
            when(paymentPersistencePort.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));

            Payment result = paymentService.processPayment(payment);

            assertThat(result.isWithinProcessingThreshold(500000)).isTrue();
            assertThat(result.status()).isEqualTo(PaymentStatus.COMPLETED);
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallos en Integraciones Externas")
    class ExternalServiceFailureScenarios {

        @Test
        @DisplayName("Debe lanzar excepción cuando el servicio antifraude falla")
        void shouldThrowExceptionWhenAntiFraudServiceFails() {
            String idempotencyKey = "idem-antifraud-fail";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-100",
                "ACC-200",
                new BigDecimal("10000.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class)))
                .thenThrow(new AntiFraudException("Fraud detection service unavailable"));

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(AntiFraudException.class)
                .hasMessage("Fraud detection service unavailable");

            verify(paymentPersistencePort).findByIdempotencyKey(idempotencyKey);
            verify(antiFraudPort).validatePayment(any(Payment.class));
            verify(bankingCorePort, never()).processTransaction(any(Payment.class));
            verify(paymentPersistencePort).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe guardar estado FAILED cuando el core bancario falla")
        void shouldSaveFailedStatusWhenBankingCoreFails() {
            String idempotencyKey = "idem-banking-fail";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-300",
                "ACC-400",
                new BigDecimal("750.00"),
                "GBP",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-003");
            when(bankingCorePort.processTransaction(any(Payment.class)))
                .thenThrow(new PaymentProcessingException("Banking core transaction failed"));

            ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.save(paymentCaptor.capture()))
                .thenAnswer(inv -> inv.getArgument(0));

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(PaymentProcessingException.class)
                .hasMessage("Banking core transaction failed");

            Payment capturedPayment = paymentCaptor.getValue();
            assertThat(capturedPayment.status()).isEqualTo(PaymentStatus.FAILED);
            assertThat(capturedPayment.fraudCheckId()).isEqualTo("fraud-003");
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación de Datos")
    class DataValidationScenarios {

        @Test
        @DisplayName("Debe rechazar pago con monto negativo")
        void shouldRejectPaymentWithNegativeAmount() {
            String idempotencyKey = "idem-negative";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-500",
                "ACC-600",
                new BigDecimal("-100.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con cuenta de origen vacía")
        void shouldRejectPaymentWithEmptySourceAccount() {
            String idempotencyKey = "idem-empty-source";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "",
                "ACC-700",
                new BigDecimal("200.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con cuenta de destino vacía")
        void shouldRejectPaymentWithEmptyDestinationAccount() {
            String idempotencyKey = "idem-empty-dest";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-800",
                "",
                new BigDecimal("300.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Debe rechazar pago con clave de idempotencia nula")
        void shouldRejectPaymentWithNullIdempotencyKey() {
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-900",
                "ACC-000",
                new BigDecimal("400.00"),
                "USD",
                null,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            assertThatThrownBy(() -> paymentService.processPayment(payment))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("Escenarios de Actualización de Estado")
    class StateUpdateScenarios {

        @Test
        @DisplayName("Debe actualizar estado correctamente con withStatus")
        void shouldUpdateStatusCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-state",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            Payment updatedPayment = originalPayment.withStatus(PaymentStatus.COMPLETED);

            assertThat(updatedPayment.status()).isEqualTo(PaymentStatus.COMPLETED);
            assertThat(updatedPayment.sourceAccount()).isEqualTo(originalPayment.sourceAccount());
            assertThat(updatedPayment.destinationAccount()).isEqualTo(originalPayment.destinationAccount());
            assertThat(updatedPayment.amount()).isEqualByComparingTo(originalPayment.amount());
        }

        @Test
        @DisplayName("Debe asociar ID de verificación antifraude correctamente")
        void shouldAssociateFraudCheckIdCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-fraud",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            String fraudCheckId = "FRAUD-" + UUID.randomUUID();
            Payment paymentWithFraudCheck = originalPayment.withFraudCheckId(fraudCheckId);

            assertThat(paymentWithFraudCheck.fraudCheckId()).isEqualTo(fraudCheckId);
            assertThat(paymentWithFraudCheck.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe asociar ID de transacción del core bancario correctamente")
        void shouldAssociateBankingTransactionIdCorrectly() {
            Payment originalPayment = new Payment(
                UUID.randomUUID(),
                "ACC-X",
                "ACC-Y",
                new BigDecimal("150.00"),
                "USD",
                "idem-txn",
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            String txnId = "TXN-" + UUID.randomUUID();
            Payment paymentWithTxnId = originalPayment.withBankingCoreTransactionId(txnId);

            assertThat(paymentWithTxnId.bankingCoreTransactionId()).isEqualTo(txnId);
            assertThat(paymentWithTxnId.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe detectar correctamente cuando el pago excede el umbral de tiempo")
        void shouldDetectWhenPaymentExceedsTimeThreshold() {
            long fiveMinutesAgo = Instant.now().minusSeconds(300).toEpochMilli();
            Payment oldPayment = new Payment(
                UUID.randomUUID(),
                "ACC-OLD",
                "ACC-NEW",
                new BigDecimal("500.00"),
                "USD",
                "idem-old",
                PaymentStatus.PENDING,
                Instant.ofEpochMilli(fiveMinutesAgo),
                null,
                null,
                null
            );

            assertThat(oldPayment.isWithinProcessingThreshold(500000)).isFalse();
        }
    }

    @Nested
    @DisplayName("Escenarios de Persistencia")
    class PersistenceScenarios {

        @Test
        @DisplayName("Debe guardar el pago inicial con estado PENDING")
        void shouldSaveInitialPaymentWithPendingStatus() {
            String idempotencyKey = "idem-persist-1";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-P1",
                "ACC-P2",
                new BigDecimal("600.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            ArgumentCaptor<Payment> saveCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(saveCaptor.capture())).thenAnswer(inv -> inv.getArgument(0));
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-persist");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-persist");

            paymentService.processPayment(payment);

            Payment savedPayment = saveCaptor.getValue();
            assertThat(savedPayment.status()).isEqualTo(PaymentStatus.PENDING);
        }

        @Test
        @DisplayName("Debe actualizar el pago con estado COMPLETED después de éxito")
        void shouldUpdatePaymentToCompletedAfterSuccess() {
            String idempotencyKey = "idem-persist-2";
            Payment payment = new Payment(
                UUID.randomUUID(),
                "ACC-C1",
                "ACC-C2",
                new BigDecimal("800.00"),
                "USD",
                idempotencyKey,
                PaymentStatus.PENDING,
                Instant.now(),
                null,
                null,
                null
            );

            ArgumentCaptor<Payment> saveCaptor = ArgumentCaptor.forClass(Payment.class);
            when(paymentPersistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
            when(paymentPersistencePort.save(saveCaptor.capture())).thenAnswer(inv -> inv.getArgument(0));
            when(antiFraudPort.validatePayment(any(Payment.class))).thenReturn("fraud-complete");
            when(bankingCorePort.processTransaction(any(Payment.class))).thenReturn("txn-complete");

            paymentService.processPayment(payment);

            var allSavedPayments = saveCaptor.getAllValues();
            Payment finalPayment = allSavedPayments.get(allSavedPayments.size() - 1);
            assertThat(finalPayment.status()).isEqualTo(PaymentStatus.COMPLETED);
        }

        @Test
        @DisplayName("Debe encontrar pago por ID existente")
        void shouldFindPaymentById() {
            UUID paymentId = UUID.randomUUID();
            Payment existingPayment = new Payment(
                paymentId,
                "ACC-F1",
                "ACC-F2",
                new BigDecimal("900.00"),
                "USD",
                "idem-find",
                PaymentStatus.COMPLETED,
                Instant.now(),
                null,
                "fraud-find",
                "txn-find"
            );

            when(paymentPersistencePort.findById(paymentId)).thenReturn(Optional.of(existingPayment));

            Optional<Payment> result = paymentPersistencePort.findById(paymentId);

            assertThat(result).isPresent();
            assertThat(result.get().id()).isEqualTo(paymentId);
            assertThat(result.get().status()).isEqualTo(PaymentStatus.COMPLETED);
        }

        @Test
        @DisplayName("Debe retornar vacío cuando el pago no existe")
        void shouldReturnEmptyWhenPaymentNotFound() {
            UUID nonExistentId = UUID.randomUUID();

            when(paymentPersistencePort.findById(nonExistentId)).thenReturn(Optional.empty());

            Optional<Payment> result = paymentPersistencePort.findById(nonExistentId);

            assertThat(result).isEmpty();
        }
    }
}
```
