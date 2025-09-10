package com.github.leopc17.inventorymanager.domain.input;

import com.github.leopc17.inventorymanager.domain.entity.Product;

import java.util.List;

public interface ProductServicePort {
    Product create(Product product);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product updateProduct(Product product, Integer id);
    void deleteProductById(Integer id);
}
