package org.fiap.fastfood.application.usecase;

import lombok.RequiredArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driver.RemoverCategoriaUseCase;

@RequiredArgsConstructor
public class RemoverCategoriaUseCaseImpl implements RemoverCategoriaUseCase {

    private final ManterCategoria manterCategoria;

    @Override
    public void execute(Integer id) {
        manterCategoria.delete(id);
    }

}
