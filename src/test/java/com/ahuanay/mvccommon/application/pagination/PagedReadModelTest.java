package com.ahuanay.mvccommon.application.pagination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PagedReadModelTest {

    @Test
    @DisplayName("Debería crear PagedReadModel con valores válidos correctamente")
    void deberiaCrearPagedReadModelConValoresValidos() {
        // Arrange
        List<String> content = List.of("Item 1", "Item 2");
        int pageNumber = 0;
        int pageSize = 10;
        long totalElements = 2L;
        int totalPages = 1;
        boolean isLast = true;

        // Act
        PagedReadModel<String> pagedReadModel = new PagedReadModel<>(
            content, pageNumber, pageSize, totalElements, totalPages, isLast
        );

        // Assert
        assertThat(pagedReadModel).isNotNull();
        assertThat(pagedReadModel.content()).hasSize(2).containsExactly("Item 1", "Item 2");
        assertThat(pagedReadModel.pageNumber()).isEqualTo(0);
        assertThat(pagedReadModel.pageSize()).isEqualTo(10);
        assertThat(pagedReadModel.totalElements()).isEqualTo(2L);
        assertThat(pagedReadModel.totalPages()).isEqualTo(1);
        assertThat(pagedReadModel.isLast()).isTrue();
    }

    @Test
    @DisplayName("Debería permitir contenido vacío y metadatos en cero correctamente")
    void deberiaPermitirContenidoVacioYMetadatosEnCero() {
        // Arrange
        List<String> emptyContent = List.of();

        // Act
        PagedReadModel<String> pagedReadModel = new PagedReadModel<>(
            emptyContent, 0, 10, 0L, 0, true
        );

        // Assert
        assertThat(pagedReadModel.content()).isEmpty();
        assertThat(pagedReadModel.totalElements()).isZero();
        assertThat(pagedReadModel.totalPages()).isZero();
        assertThat(pagedReadModel.isLast()).isTrue();
    }
}