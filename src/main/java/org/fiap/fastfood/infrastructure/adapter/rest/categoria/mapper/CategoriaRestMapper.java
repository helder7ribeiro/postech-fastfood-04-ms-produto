package org.fiap.fastfood.infrastructure.adapter.rest.categoria.mapper;

import org.fiap.fastfood.domain.model.Categoria;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaRestMapper {

    Categoria toDomain(RegistrarCategoriaRequest request);

    Categoria toDomain(AtualizarCategoriaRequest request);

    RegistrarCategoriaResponse toRegistrarCategoriaResponse(Categoria domain);

    ListarCategoriaResponse toListarCategoriaResponse(Categoria domain);

    AtualizarCategoriaResponse toAtualizarCategoriaResponse(Categoria domain);
}