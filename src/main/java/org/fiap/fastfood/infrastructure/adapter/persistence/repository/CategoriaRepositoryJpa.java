package org.fiap.fastfood.infrastructure.adapter.persistence.repository;

import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositoryJpa extends JpaRepository<CategoriaEntity, Integer> {
}
