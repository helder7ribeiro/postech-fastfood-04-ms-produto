package org.fiap.fastfood.infrastructure.adapter.persistence.repository;

import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByCategoriaId(Integer categoryId);

}
