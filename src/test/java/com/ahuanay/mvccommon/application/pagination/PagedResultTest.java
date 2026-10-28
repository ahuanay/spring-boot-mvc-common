package com.ahuanay.mvccommon.application.pagination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PagedResultTest {

    @Test
    @DisplayName("Debería crear PagedResult con valores válidos correctamente")
    void deberiaCrearPagedReadModelConValoresValidos() {
        // Arrange
        List<String> content = List.of("Item 1", "Item 2");
        int pageNumber = 0;
        int pageSize = 10;
        long totalElements = 2L;
        int totalPages = 1;
        boolean isLast = true;

        // Act
        PagedResult<String> pagedResult = new PagedResult<>(
            content, pageNumber, pageSize, totalElements, totalPages, isLast
        );

        // Assert
        assertThat(pagedResult).isNotNull();
        assertThat(pagedResult.content()).hasSize(2).containsExactly("Item 1", "Item 2");
        assertThat(pagedResult.pageNumber()).isEqualTo(0);
        assertThat(pagedResult.pageSize()).isEqualTo(10);
        assertThat(pagedResult.totalElements()).isEqualTo(2L);
        assertThat(pagedResult.totalPages()).isEqualTo(1);
        assertThat(pagedResult.isLast()).isTrue();
    }

    @Test
    @DisplayName("Debería permitir contenido vacío y metadatos en cero correctamente")
    void deberiaPermitirContenidoVacioYMetadatosEnCero() {
        // Arrange
        List<String> emptyContent = List.of();

        // Act
        PagedResult<String> pagedResult = new PagedResult<>(
            emptyContent, 0, 10, 0L, 0, true
        );

        // Assert
        assertThat(pagedResult.content()).isEmpty();
        assertThat(pagedResult.totalElements()).isZero();
        assertThat(pagedResult.totalPages()).isZero();
        assertThat(pagedResult.isLast()).isTrue();
    }
}