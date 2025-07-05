package org.fiap.fastfood.infrastructure.adapter.rest.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegistrarProdutoRequest(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal preco,
        @NotNull
        Integer categoriaId
) {
}
