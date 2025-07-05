package org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistrarCategoriaRequest(
        @NotBlank
        String nome,
        @NotBlank
        String descricao
) {
}
