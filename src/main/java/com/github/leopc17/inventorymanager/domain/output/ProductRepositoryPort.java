package com.github.leopc17.inventorymanager.domain.output;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> save(Product product);
    Optional<List<Product>> getALl();
    Optional<Product> getById(Integer id);
    Optional<Product> updateById(Product newProduct, Integer id);
    void deleteById(Integer id);
    Optional<List<Product>> getByCategory(ProductCategory category);
    Optional<List<Product>> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<List<Product>> getProductsBelow(Integer quantity);
}
