package org.fiap.fastfood.infrastructure.adapter.rest.produto.mapper;

import org.fiap.fastfood.domain.model.Categoria;
import org.fiap.fastfood.domain.model.Produto;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.mapper.CategoriaRestMapper;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.ListarProdutoResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoriaRestMapper.class)
public interface ProdutoRestMapper {

    @Mapping(source = "categoriaId", target = "categoria")
    Produto toDomain(RegistrarProdutoRequest request);

    @Mapping(source = "categoriaId", target = "categoria")
    Produto toDomain(AtualizarProdutoRequest request);

    RegistrarProdutoResponse toRegistrarProdutoResponse(Produto domain);

    ListarProdutoResponse toListarProdutoResponse(Produto domain);

    AtualizarProdutoResponse toAtualizarProdutoResponse(Produto domain);

    default Categoria map(Integer categoriaId) {
        return categoriaId != null ? Categoria.builder().id(categoriaId).build() : null;
    }

}