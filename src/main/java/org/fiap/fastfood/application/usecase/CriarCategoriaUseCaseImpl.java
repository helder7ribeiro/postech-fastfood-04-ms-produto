package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driver.CriarCategoriaUseCase;
import org.fiap.fastfood.domain.model.Categoria;

@AllArgsConstructor
public class CriarCategoriaUseCaseImpl implements CriarCategoriaUseCase {

    private final ManterCategoria manterCategoria;

    @Override
    public Categoria execute(Categoria categoria) {
        return manterCategoria.createOrUpdate(categoria);
    }

}
