package org.fiap.fastfood.application.port.driver;

import org.fiap.fastfood.domain.model.Produto;

public interface AtualizarProdutoUseCase {

    Produto execute(Produto produto);

}
