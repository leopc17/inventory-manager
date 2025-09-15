package com.github.leopc17.inventorymanager.infrastructure.adapter.output;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.infrastructure.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Integer> {
     List<ProductEntity> findByCategory(ProductCategory category);
     List<ProductEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
