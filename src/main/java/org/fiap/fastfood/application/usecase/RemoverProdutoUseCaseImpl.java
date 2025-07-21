package org.fiap.fastfood.application.usecase;

import lombok.RequiredArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.RemoverProdutoUseCase;

@RequiredArgsConstructor
public class RemoverProdutoUseCaseImpl implements RemoverProdutoUseCase {

    private final ManterProduto manterProduto;

    @Override
    public void execute(Integer id) {
        manterProduto.delete(id);
    }

}
