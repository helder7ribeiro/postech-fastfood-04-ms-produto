package org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto;

public record RegistrarCategoriaResponse(
        Integer id,
        String nome,
        String descricao
) {
}
