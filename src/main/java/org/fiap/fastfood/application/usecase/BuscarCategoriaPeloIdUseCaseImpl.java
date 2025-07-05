package org.fiap.fastfood.application.usecase;

import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driver.BuscarCategoriaPeloIdUseCase;
import org.fiap.fastfood.domain.model.Categoria;

@AllArgsConstructor
public class BuscarCategoriaPeloIdUseCaseImpl implements BuscarCategoriaPeloIdUseCase {

    private final ManterCategoria manterCategoria;

    @Override
    public Categoria execute(Integer id) {
        return manterCategoria.findById(id);
    }

}
