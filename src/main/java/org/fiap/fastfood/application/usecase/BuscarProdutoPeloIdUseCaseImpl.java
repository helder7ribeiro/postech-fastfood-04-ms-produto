package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.BuscarProdutoPeloIdUseCase;
import org.fiap.fastfood.domain.model.Produto;

@AllArgsConstructor
public class BuscarProdutoPeloIdUseCaseImpl implements BuscarProdutoPeloIdUseCase {

    private final ManterProduto manterProduto;

    @Override
    public Produto execute(Integer id) {
        return manterProduto.findById(id);
    }

}
