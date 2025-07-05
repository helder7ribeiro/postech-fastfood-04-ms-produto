package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driver.ListarCategoriasUseCase;
import org.fiap.fastfood.domain.model.Categoria;

import java.util.List;

@AllArgsConstructor
public class ListarCategoriasUseCaseImpl implements ListarCategoriasUseCase {

    private final ManterCategoria manterCategoria;

    @Override
    public List<Categoria> execute() {
        return manterCategoria.findAll();
    }

}
