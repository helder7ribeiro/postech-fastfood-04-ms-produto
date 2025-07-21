package org.fiap.fastfood.application.port.driven;

import org.fiap.fastfood.domain.model.Produto;

import java.util.List;

public interface ManterProduto {

    Produto createOrUpdate(Produto produto);

    List<Produto> findAll();

    Produto findById(Integer id);

    List<Produto> findByCategoryId(Integer categoryId);

    void delete(Integer id);

}
