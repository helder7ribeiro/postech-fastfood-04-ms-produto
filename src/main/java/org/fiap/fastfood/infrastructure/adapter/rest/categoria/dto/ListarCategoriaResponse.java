package org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto;

public record ListarCategoriaResponse(
        Integer id,
        String nome,
        String descricao
) {
}
