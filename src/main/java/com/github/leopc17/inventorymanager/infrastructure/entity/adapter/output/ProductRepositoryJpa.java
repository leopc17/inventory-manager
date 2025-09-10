package com.github.leopc17.inventorymanager.infrastructure.entity.adapter.output;

import com.github.leopc17.inventorymanager.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Integer> {
}
