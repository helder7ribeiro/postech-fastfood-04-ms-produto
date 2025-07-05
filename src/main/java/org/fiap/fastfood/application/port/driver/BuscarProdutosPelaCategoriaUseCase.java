package org.fiap.fastfood.application.port.driver;

import org.fiap.fastfood.domain.model.Produto;

import java.util.List;

public interface BuscarProdutosPelaCategoriaUseCase {

    List<Produto> execute(Integer categoryId);

}
