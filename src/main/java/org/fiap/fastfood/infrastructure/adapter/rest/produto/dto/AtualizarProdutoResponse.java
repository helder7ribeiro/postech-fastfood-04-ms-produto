package org.fiap.fastfood.infrastructure.adapter.rest.produto.dto;

import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.ListarCategoriaResponse;

import java.math.BigDecimal;

public record AtualizarProdutoResponse(
        Integer id,
        String nome,
        String descricao,
        BigDecimal preco,
        ListarCategoriaResponse categoria
) {
}
