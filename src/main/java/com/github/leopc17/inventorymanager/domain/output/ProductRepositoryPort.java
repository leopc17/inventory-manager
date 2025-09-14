package com.github.leopc17.inventorymanager.domain.output;

import com.github.leopc17.inventorymanager.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> save(Product product);
    Optional<List<Product>> getALl();
    Optional<Product> getById(Integer id);
    Optional<Product> updateById(Product newProduct, Integer id);
    void deleteById(Integer id);
    Optional<List<Product>> getByCategory(String category);
}
