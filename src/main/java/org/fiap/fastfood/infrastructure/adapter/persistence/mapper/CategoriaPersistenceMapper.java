package org.fiap.fastfood.infrastructure.adapter.persistence.mapper;

import org.fiap.fastfood.domain.model.Categoria;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaPersistenceMapper {

    Categoria toDomain(CategoriaEntity entity);

    CategoriaEntity toEntity(Categoria domain);

}