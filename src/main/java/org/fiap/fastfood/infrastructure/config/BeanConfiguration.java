package org.fiap.fastfood.infrastructure.config;

import org.fiap.fastfood.application.port.driven.ManterCategoria;
import org.fiap.fastfood.application.port.driven.ManterProduto;
import org.fiap.fastfood.application.port.driver.*;
import org.fiap.fastfood.application.usecase.*;
import org.fiap.fastfood.infrastructure.adapter.persistence.ManterCategoriaAdapter;
import org.fiap.fastfood.infrastructure.adapter.persistence.ManterProdutoAdapter;
import org.fiap.fastfood.infrastructure.adapter.persistence.mapper.CategoriaPersistenceMapper;
import org.fiap.fastfood.infrastructure.adapter.persistence.mapper.ProdutoPersistenceMapper;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {



    @Bean
    public ManterCategoria criarCategoriaRepository(CategoriaRepositoryJpa categoriaRepositoryJpa, CategoriaPersistenceMapper categoriaPersistenceMapper) {
        return new ManterCategoriaAdapter(categoriaRepositoryJpa, categoriaPersistenceMapper);
    }

    @Bean
    public CriarCategoriaUseCase criarGerenciarCategoria(ManterCategoria manterCategoria) {
        return new CriarCategoriaUseCaseImpl(manterCategoria);
    }

    @Bean
    public ListarCategoriasUseCase criarListarCategorias(ManterCategoria manterCategoria) {
        return new ListarCategoriasUseCaseImpl(manterCategoria);
    }

    @Bean
    public BuscarCategoriaPeloIdUseCase criarBuscarCategoriaPeloIdUseCase(ManterCategoria manterCategoria) {
        return new BuscarCategoriaPeloIdUseCaseImpl(manterCategoria);
    }

    @Bean
    public AtualizarCategoriaUseCase criarAtualizarCategoriaUseCase(ManterCategoria manterCategoria) {
        return new AtualizarCategoriaUseCaseImpl(manterCategoria);
    }

    @Bean
    public RemoverCategoriaUseCase criarRemoverCategoriaUseCase(ManterCategoria manterCategoria) {
        return new RemoverCategoriaUseCaseImpl(manterCategoria);
    }

    @Bean
    public ManterProduto criarProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa, ProdutoPersistenceMapper produtoPersistenceMapper) {
        return new ManterProdutoAdapter(produtoRepositoryJpa, produtoPersistenceMapper);
    }

    @Bean
    public CriarProdutoUseCase criarCriarProdutoUseCase(ManterProduto manterProduto) {
        return new CriarProdutoUseCaseImpl(manterProduto);
    }

    @Bean
    public ListarProdutosUseCase criarListarProdutosUseCase(ManterProduto manterProduto) {
        return new ListarProdutosUseCaseImpl(manterProduto);
    }

    @Bean
    public BuscarProdutoPeloIdUseCase criarBuscarProdutoPeloIdUseCase(ManterProduto manterProduto) {
        return new BuscarProdutoPeloIdUseCaseImpl(manterProduto);
    }

    @Bean
    public BuscarProdutosPelaCategoriaUseCase criarBuscarProdutoPelaCategoriaUseCase(ManterProduto manterProduto) {
        return new BuscarProdutosPelaCategoriaUseCaseImpl(manterProduto);
    }

    @Bean
    public AtualizarProdutoUseCase criarAtualizarProdutoUseCase(ManterProduto manterProduto) {
        return new AtualizarProdutoUseCaseImpl(manterProduto);
    }

    @Bean
    public RemoverProdutoUseCase criarRemoverProdutoUseCase(ManterProduto manterProduto) {
        return new RemoverProdutoUseCaseImpl(manterProduto);
    }



}
