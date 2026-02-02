package com.github.leopc17.inventorymanager.domain.input;

import java.math.BigDecimal;
import java.util.List;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.domain.model.Product;

public interface ProductServicePort {
    Product create(Product product);
    List<Product> getAll();
    Product getById(Integer id);
    Product update(Product newProduct, Integer id);
    void deleteById(Integer id);
    Product updateInventory(Integer id, Integer quantity);
    List<Product> getByCategory(ProductCategory category);
    List<Product> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> getProductsBelow(Integer quantity);
}
