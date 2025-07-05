package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driver.AtualizarCategoriaUseCase;
import org.fiap.fastfood.domain.model.Categoria;

@AllArgsConstructor
public class AtualizarCategoriaUseCaseImpl implements AtualizarCategoriaUseCase {

    private final ManterCategoria manterCategoria;

    @Override
    public Categoria execute(Categoria categoria) {
        return manterCategoria.update(categoria);
    }

}
