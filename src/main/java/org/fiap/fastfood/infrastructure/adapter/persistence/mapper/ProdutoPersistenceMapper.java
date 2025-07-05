package org.fiap.fastfood.infrastructure.adapter.persistence.mapper;

import org.fiap.fastfood.domain.model.Produto;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoPersistenceMapper {

    Produto toDomain(ProdutoEntity entity);

    ProdutoEntity toEntity(Produto domain);

}