package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.BuscarProdutosPelaCategoriaUseCase;
import org.fiap.fastfood.domain.model.Produto;

import java.util.List;

@AllArgsConstructor
public class BuscarProdutosPelaCategoriaUseCaseImpl implements BuscarProdutosPelaCategoriaUseCase {

    private final ManterProduto manterProduto;

    @Override
    public List<Produto> execute(Integer categoryId) {
        return manterProduto.findByCategoryId(categoryId);
    }

}
