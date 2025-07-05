package org.fiap.fastfood.application.port.driver;

import org.fiap.fastfood.domain.model.Categoria;

public interface AtualizarCategoriaUseCase {

    Categoria execute(Categoria categoria);

}
