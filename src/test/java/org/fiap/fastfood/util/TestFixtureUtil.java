package org.fiap.fastfood.util;

import jakarta.transaction.Transactional;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class TestFixtureUtil {

    @Autowired
    private CategoriaRepositoryJpa categoriaRepositoryJpa;

    @Autowired
    private ProdutoRepositoryJpa produtoRepositoryJpa;

    @Autowired


    @Transactional
    public CategoriaEntity criarCategoriaEntity() {
        int random = new Random().nextInt(10000000);
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNome("Nome " + random);
        categoriaEntity.setDescricao("Descricao " + random);
        return categoriaRepositoryJpa.save(categoriaEntity);
    }



    @Transactional
    public ProdutoEntity criarProdutoEntity() {
        return criarProdutoEntity(criarCategoriaEntity());
    }

    @Transactional
    public ProdutoEntity criarProdutoEntity(CategoriaEntity categoriaEntity) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setCategoria(categoriaEntity);

        int random = new Random().nextInt(10000000);
        produtoEntity.setNome("Nome " + random);
        produtoEntity.setDescricao("Descricao " + random);
        produtoEntity.setPreco(BigDecimal.ONE);
        return produtoRepositoryJpa.save(produtoEntity);
    }



}
