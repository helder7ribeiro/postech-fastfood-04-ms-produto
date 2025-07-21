package org.fiap.fastfood.application.usecase;

import lombok.RequiredArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.AtualizarProdutoUseCase;
import org.fiap.fastfood.domain.model.Produto;

@RequiredArgsConstructor
public class AtualizarProdutoUseCaseImpl implements AtualizarProdutoUseCase {

    private final ManterProduto manterProduto;

    @Override
    public Produto execute(Produto produto) {
        return manterProduto.createOrUpdate(produto);
    }
}
