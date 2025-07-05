package org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto;

public record AtualizarCategoriaResponse(
        Integer id,
        String nome,
        String descricao
) {
}
