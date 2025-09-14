package com.github.leopc17.inventorymanager.domain.input;

import com.github.leopc17.inventorymanager.domain.model.Product;

import java.util.List;

public interface ProductServicePort {
    Product create(Product product);
    List<Product> getAll();
    Product getById(Integer id);
    Product update(Product newProduct, Integer id);
    void deleteById(Integer id);
    Product updateInventory(Integer id, Integer quantity);
}
