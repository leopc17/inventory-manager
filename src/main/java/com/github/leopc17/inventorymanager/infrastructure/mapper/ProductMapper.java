package com.github.leopc17.inventorymanager.infrastructure.mapper;

import com.github.leopc17.inventorymanager.application.dto.ProductDto;
import com.github.leopc17.inventorymanager.domain.model.Product;
import com.github.leopc17.inventorymanager.infrastructure.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity productEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getLongDescription(),
                product.getShortDescription(),
                product.getCategory()
        );
    }

    public static Product product(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        product.setQuantity(entity.getQuantity());
        product.setLongDescription(entity.getLongDescription());
        product.setShortDescription(entity.getShortDescription());
        product.setCategory(entity.getCategory());
        return product;
    }

    public static Product product(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());
        product.setCategory(dto.category());
        product.setShortDescription(dto.shortDescription());
        product.setLongDescription(dto.longDescription());
        return product;
    }
}