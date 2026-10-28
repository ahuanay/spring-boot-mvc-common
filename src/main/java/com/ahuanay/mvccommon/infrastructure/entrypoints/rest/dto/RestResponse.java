package com.ahuanay.mvccommon.infrastructure.entrypoints.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Schema(name = "RestResponse", description = "Estructura estándar unificada para todas las respuestas de la API (Éxito, Paginación y Errores)")
public record RestResponse<T>(

    @Schema(description = "Fecha y hora en la que se generó la respuesta", example = "2026-10-28T18:45:30.123")
    LocalDateTime timestamp,

    @Schema(description = "Código de estado HTTP de la respuesta", example = "200")
    int status,

    @Schema(description = "Indica si la operación ejecutada fue exitosa o fallida", example = "true")
    boolean success,

    @Schema(description = "Mensaje descriptivo del resultado de la operación", example = "Operación exitosa")
    String message,

    @Schema(description = "Carga útil de datos (objeto único, lista de elementos o nulo según corresponda)")
    T data,

    @Schema(description = "Metadatos de paginación (presente únicamente en consultas paginadas)")
    PageMetadata pagination,

    @Schema(description = "Mapa de errores específicos por campo (presente únicamente en fallos de validación)", example = "{\"nombre\": \"El nombre es obligatorio\"}")
    Map<String, String> errors,

    @Schema(description = "Lista de identificadores que no fueron encontrados en el sistema (presente en errores de recursos no hallados)", example = "[99, 100]")
    List<Long> notFoundIds
) {
    public RestResponse {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public static <T> RestResponse<T> success(int status, String message, T data) {
        return RestResponse.<T>builder()
            .status(status)
            .success(true)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> RestResponse<T> success(int status, String message) {
        return RestResponse.<T>builder()
            .status(status)
            .success(true)
            .message(message)
            .build();
    }

    public static <T> RestResponse<T> successPage(int status, String message, T data, PageMetadata pagination) {
        return RestResponse.<T>builder()
            .status(status)
            .success(true)
            .message(message)
            .data(data)
            .pagination(pagination)
            .build();
    }

    public static <T> RestResponse<T> error(int status, String message) {
        return RestResponse.<T>builder()
            .status(status)
            .success(false)
            .message(message)
            .build();
    }

    public static <T> RestResponse<T> error(int status, String message, Map<String, String> errors) {
        return RestResponse.<T>builder()
            .status(status)
            .success(false)
            .message(message)
            .errors(errors)
            .build();
    }

    public static <T> RestResponse<T> error(int status, String message, List<Long> notFoundIds) {
        return RestResponse.<T>builder()
            .status(status)
            .success(false)
            .message(message)
            .notFoundIds(notFoundIds)
            .build();
    }
}