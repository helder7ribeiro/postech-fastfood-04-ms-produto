package org.fiap.fastfood.application.usecase;

import lombok.RequiredArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.ListarProdutosUseCase;
import org.fiap.fastfood.domain.model.Produto;

import java.util.List;

@RequiredArgsConstructor
public class ListarProdutosUseCaseImpl implements ListarProdutosUseCase {

    private final ManterProduto manterProduto;

    @Override
    public List<Produto> execute() {
        return manterProduto.findAll();
    }

}
