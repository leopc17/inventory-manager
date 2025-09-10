package com.github.leopc17.inventorymanager.domain.output;

import com.github.leopc17.inventorymanager.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> create(Product product);
    Optional<List<Product>> getAllProducts();
    Optional<Product> getProductById(Integer id);
    Optional<Product> updateProduct(Product product, Integer id);
    void deleteProductById(Integer id);
}
