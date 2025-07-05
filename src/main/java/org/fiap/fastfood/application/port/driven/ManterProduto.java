package org.fiap.fastfood.application.port.driven;

import org.fiap.fastfood.domain.model.Produto;

import java.util.List;

public interface ManterProduto {

    Produto create(Produto produto);

    List<Produto> findAll();

    Produto findById(Integer id);

    List<Produto> findByCategoryId(Integer categoryId);

    Produto update(Produto produto);

    void delete(Integer id);

}
