package org.fiap.fastfood.application.port.driven;

import org.fiap.fastfood.domain.model.Categoria;

import java.util.List;

public interface ManterCategoria {

    Categoria create(Categoria categoria);

    List<Categoria> findAll();

    Categoria findById(Integer id);

    Categoria update(Categoria categoria);

    void delete(Integer id);

}
