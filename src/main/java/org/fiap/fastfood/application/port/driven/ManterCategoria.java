package org.fiap.fastfood.application.port.driven;

import org.fiap.fastfood.domain.model.Categoria;

import java.util.List;

public interface ManterCategoria {

    Categoria createOrUpdate(Categoria categoria);

    List<Categoria> findAll();

    Categoria findById(Integer id);

    void delete(Integer id);

}
