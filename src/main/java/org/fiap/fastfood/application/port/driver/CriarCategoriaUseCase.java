package org.fiap.fastfood.application.port.driver;

import org.fiap.fastfood.domain.model.Categoria;

public interface CriarCategoriaUseCase {

    Categoria execute(Categoria categoria);

}
