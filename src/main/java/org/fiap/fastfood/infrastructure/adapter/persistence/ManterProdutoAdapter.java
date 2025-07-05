package org.fiap.fastfood.infrastructure.adapter.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.domain.model.Produto;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.mapper.ProdutoPersistenceMapper;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
public class ManterProdutoAdapter implements ManterProduto {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;
    private final ProdutoPersistenceMapper produtoPersistenceMapper;

    @Override
    @Transactional
    public Produto create(Produto produto) {
        ProdutoEntity entity = produtoPersistenceMapper.toEntity(produto);
        entity = produtoRepositoryJpa.save(entity);
        return produtoPersistenceMapper.toDomain(entity);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepositoryJpa.findAll().stream().map(produtoPersistenceMapper::toDomain).toList();
    }

    @Override
    public Produto findById(Integer id) {
        ProdutoEntity produtoEntity = produtoRepositoryJpa.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("O produto %s não foi encontrado.", id)));
        return produtoPersistenceMapper.toDomain(produtoEntity);
    }

    @Override
    public List<Produto> findByCategoryId(Integer categoryId) {
        List<ProdutoEntity> produtoEntityList = produtoRepositoryJpa.findByCategoriaId(categoryId);
        return produtoEntityList.stream().map(produtoPersistenceMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Produto update(Produto produto) {
        ProdutoEntity entity = produtoPersistenceMapper.toEntity(produto);
        entity = produtoRepositoryJpa.save(entity);
        return produtoPersistenceMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        produtoRepositoryJpa.deleteById(id);
    }
}
