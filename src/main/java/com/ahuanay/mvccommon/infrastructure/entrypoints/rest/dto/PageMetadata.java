package com.ahuanay.mvccommon.infrastructure.entrypoints.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Schema(name = "PageMetadata", description = "Metadatos de paginación para respuestas estructuradas")
public record PageMetadata(

    @Schema(description = "Número de la página actual (Base cero)", example = "0")
    int pageNumber,

    @Schema(description = "Cantidad de elementos solicitados por página", example = "10")
    int pageSize,

    @Schema(description = "Cantidad total de elementos existentes en la base de datos", example = "150")
    long totalElements,

    @Schema(description = "Número total de páginas calculadas", example = "15")
    int totalPages,

    @Schema(description = "Indica si es la última página del resultado", example = "false")
    boolean isLast
) {
}