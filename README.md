# Spring Boot MVC Common (`mvc-common`)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![JitPack](https://jitpack.io/v/ahuanay/spring-boot-mvc-common.svg)](https://jitpack.io/#ahuanay/spring-boot-mvc-common)
[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-release-notes.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen.svg)](https://spring.io/projects/spring-boot)

Librería compartida agnóstica desarrollada bajo arquitectura limpia, diseñada para estandarizar contratos REST (patrón `RestResponse`), metadatos de paginación desacoplados, manejo global de excepciones técnicas (`ParametroInvalidoException`) y utilidades de auditoría JPA para microservicios basados en Spring Boot 3 y Java 21.

---

## 🚀 Características Principales

* **Estructura REST Unificada (`RestResponse<T>`):** Contrato maestro genérico para estandarizar todas las respuestas del API (éxito con datos, éxito sin contenido, respuestas paginadas y errores con soporte para validación por campos o IDs no encontrados).
* **Paginación Desacoplada (`PagedReadModel` & `PageMetadata`):** Modelos limpios para la gestión y transporte de paginaciones sin acoplamientos a librerías de persistencia específicas de Spring Data.
* **Parámetros y Validaciones Robustas (`QueryPagination`):** Utilidades integradas para ordenar, filtrar y parametrizar consultas de forma segura con valores por defecto.
* **Auditoría JPA Automática (`AuditableEntity`):** Entidad base para el control automático de marcas temporales de creación y actualización (`created_at`, `updated_at`).
* **Manejo Global de Excepciones REST (`GlobalExceptionHandler`):** Captura centralizada de errores comunes y parámetros inválidos adaptados al contrato unificado.
* **Documentación OpenAPI / Swagger Integrada:** Anotaciones listas para generar documentación clara y automática en los microservicios consumidores.

---

## 📦 Instalación (JitPack)

Esta librería utiliza **JitPack** para su distribución pública en el portafolio.

### 1. Agregar el repositorio de JitPack
En el archivo `pom.xml` de tu microservicio consumidor, añade el repositorio en la sección `<repositories>`:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

### Agregar la dependencia de la librería
En el archivo `pom.xml` de tu microservicio consumidor, añade el repositorio en la sección `<repositories>`:

```xml
<dependencies>
  <dependency>
    <groupId>com.github.ahuanay</groupId>
    <artifactId>spring-boot-mvc-common</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```