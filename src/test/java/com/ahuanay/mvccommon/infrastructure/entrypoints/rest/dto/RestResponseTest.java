package com.ahuanay.mvccommon.infrastructure.entrypoints.rest.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RestResponseTest {

    @Test
    @DisplayName("Debería crear una respuesta de éxito con datos y asignar timestamp automáticamente")
    void deberiaCrearRespuestaExitoConDatos() {
        // Arrange
        int status = 200;
        String message = "Operación exitosa";
        String data = "Datos de prueba";

        // Act
        RestResponse<String> response = RestResponse.success(status, message, data);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(200, response.status());
        assertTrue(response.success());
        assertEquals("Operación exitosa", response.message());
        assertEquals("Datos de prueba", response.data());
        assertNull(response.pagination());
        assertNull(response.errors());
        assertNull(response.notFoundIds());
    }

    @Test
    @DisplayName("Debería crear una respuesta de éxito simple sin datos cuando se invoca success")
    void deberiaCrearRespuestaExitoSimple() {
        // Arrange
        int status = 201;
        String message = "Recurso creado con éxito";

        // Act
        RestResponse<Void> response = RestResponse.success(status, message);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(201, response.status());
        assertTrue(response.success());
        assertEquals("Recurso creado con éxito", response.message());
        assertNull(response.data());
    }

    @Test
    @DisplayName("Debería crear una respuesta con paginación cuando se invoca successPage")
    void deberiaCrearRespuestaExitoPaginada() {
        // Arrange
        int status = 200;
        String message = "Listado obtenido";
        List<String> listData = List.of("Item 1", "Item 2");
        PageMetadata metadata = new PageMetadata(0, 10, 2L, 1, true);

        // Act
        RestResponse<List<String>> response = RestResponse.successPage(status, message, listData, metadata);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(200, response.status());
        assertTrue(response.success());
        assertEquals("Listado obtenido", response.message());
        assertEquals(2, response.data().size());
        assertEquals(metadata, response.pagination());
    }

    @Test
    @DisplayName("Debería crear una respuesta de error simple cuando se invoca error")
    void deberiaCrearRespuestaErrorSimple() {
        // Arrange
        int status = 500;
        String message = "Error interno del servidor";

        // Act
        RestResponse<Object> response = RestResponse.error(status, message);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(500, response.status());
        assertFalse(response.success());
        assertEquals("Error interno del servidor", response.message());
        assertNull(response.data());
        assertNull(response.errors());
    }

    @Test
    @DisplayName("Debería crear una respuesta de error con mapa de validaciones cuando se invoca error con errores")
    void deberiaCrearRespuestaErrorConValidaciones() {
        // Arrange
        int status = 400;
        String message = "Error de validación";
        Map<String, String> errors = Map.of("nombre", "no puede estar vacío");

        // Act
        RestResponse<Object> response = RestResponse.error(status, message, errors);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(400, response.status());
        assertFalse(response.success());
        assertEquals("Error de validación", response.message());
        assertEquals("no puede estar vacío", response.errors().get("nombre"));
    }

    @Test
    @DisplayName("Debería crear una respuesta de error con IDs no encontrados cuando se invoca error con notFoundIds")
    void deberiaCrearRespuestaErrorConNotFoundIds() {
        // Arrange
        int status = 404;
        String message = "No se encontró el recurso solicitado.";
        List<Long> notFoundIds = List.of(1L, 2L);

        // Act
        RestResponse<Object> response = RestResponse.error(status, message, notFoundIds);

        // Assert
        assertNotNull(response.timestamp());
        assertEquals(404, response.status());
        assertFalse(response.success());
        assertEquals("No se encontró el recurso solicitado.", response.message());
        assertEquals(notFoundIds, response.notFoundIds());
    }

    @Test
    @DisplayName("Debería asignar el timestamp actual mediante el constructor compacto si se proporciona uno nulo")
    void deberiaAsignarTimestampPorDefectoEnConstructorCompacto() {
        // Arrange & Act
        RestResponse<Object> response = new RestResponse<>(
            null, 200, true, "OK", null, null, null, null
        );

        // Assert
        assertNotNull(response.timestamp());
    }
}