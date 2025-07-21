package org.fiap.fastfood.infrastructure.adapter.persistence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.domain.model.Categoria;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.mapper.CategoriaPersistenceMapper;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;

import java.util.List;

@AllArgsConstructor
public class ManterCategoriaAdapter implements ManterCategoria {

    private final CategoriaRepositoryJpa categoriaRepositoryJpa;
    private final CategoriaPersistenceMapper categoriaPersistenceMapper;

    @Override
    @Transactional
    public Categoria createOrUpdate(Categoria categoria) {
        CategoriaEntity entity = categoriaPersistenceMapper.toEntity(categoria);
        entity = categoriaRepositoryJpa.save(entity);
        return categoriaPersistenceMapper.toDomain(entity);
    }

    @Override
    public List<Categoria> findAll() {
        List<CategoriaEntity> categorias = categoriaRepositoryJpa.findAll();
        return categorias.stream().map(categoriaPersistenceMapper::toDomain).toList();
    }

    @Override
    public Categoria findById(Integer id) {
        CategoriaEntity entity = categoriaRepositoryJpa.findById(id).orElseThrow();
        return categoriaPersistenceMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoriaRepositoryJpa.deleteById(id);
    }

}
