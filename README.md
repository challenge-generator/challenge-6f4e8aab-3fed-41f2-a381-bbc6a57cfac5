# Implementación de Funcionalidad de Pagos con BDD y Automatización

El sistema de pagos de una institución financiera requiere la implementación de una nueva funcionalidad para procesar pagos en tiempo real. Los pagos deben ser idempotentes, con un umbral de procesamiento de 500 ms y una tasa de éxito del 99.9%. Los actores involucrados son el originador de créditos, el motor antifraude y el core bancario. La funcionalidad debe ser documentada y verificada utilizando BDD y herramientas de automatización.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Desarrollador con Sólida Experiencia en BDD y Frameworks de Automatización |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 3 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Especificación del Comportamiento del Pago

**Objetivo:** Definir el comportamiento esperado del pago en términos de negocio utilizando BDD.

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Identificar los actores y sus interacciones en el proceso de pago.
- Especificar las reglas de negocio para el pago idempotente.
- Documentar los criterios de aceptación para la funcionalidad de pago.

**Entregable:** Documentación BDD del comportamiento del pago.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo describir el comportamiento del pago desde la perspectiva del negocio.
- Piensa en cómo validar que un pago es idempotente.

</details>

### Fase 2: Automatización de las Especificaciones BDD

**Objetivo:** Automatizar las especificaciones BDD utilizando herramientas de automatización.

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Implementar los escenarios BDD utilizando una herramienta de automatización.
- Verificar que los escenarios se ejecutan correctamente y cumplen con los criterios de aceptación.
- Documentar cualquier desafío encontrado y cómo lo resolviste.

**Entregable:** Escenarios BDD automatizados y documentados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo estructurar los escenarios BDD para que sean claros y verificables.
- Piensa en cómo manejar los errores y excepciones en los escenarios automatizados.

</details>

### Fase 3: Integración y Verificación de la Funcionalidad de Pago

**Objetivo:** Integrar y verificar la funcionalidad de pago en el sistema existente.

**Tiempo estimado:** 1 semana

**Instrucciones:**

- Integrar la funcionalidad de pago en el sistema existente.
- Verificar que la funcionalidad cumple con los criterios de aceptación y las especificaciones BDD.
- Documentar cualquier cambio o ajuste realizado durante la integración.

**Entregable:** Funcionalidad de pago integrada y verificada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo asegurar que la funcionalidad de pago se integra sin problemas en el sistema existente.
- Piensa en cómo verificar que la funcionalidad cumple con los criterios de aceptación y las especificaciones BDD.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es BDD y cómo se aplica en el desarrollo de software?
- **paraQueSirve**: ¿Para qué sirve la automatización de las especificaciones BDD en el proceso de desarrollo?
- **comoSeUsa**: ¿Cómo se utilizan las herramientas de automatización para implementar y verificar las especificaciones BDD?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar y automatizar las especificaciones BDD?
- **queDecisionesImplica**: ¿Qué decisiones implica la integración de una nueva funcionalidad en un sistema existente utilizando BDD y herramientas de automatización?

## Criterios de Evaluacion

- Implementación correcta de las especificaciones BDD.
- Automatización efectiva de los escenarios BDD.
- Integración y verificación exitosa de la funcionalidad de pago en el sistema existente.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
