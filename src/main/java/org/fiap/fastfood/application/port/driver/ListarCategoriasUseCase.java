package org.fiap.fastfood.application.port.driver;

import org.fiap.fastfood.domain.model.Categoria;

import java.util.List;

public interface ListarCategoriasUseCase {

    List<Categoria> execute();

}
