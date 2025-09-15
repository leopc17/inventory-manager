package com.github.leopc17.inventorymanager.application.dto;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;

import java.math.BigDecimal;

public record ProductDto(
        String name,
        BigDecimal price,
        Integer quantity,
        String longDescription,
        String shortDescription,
        ProductCategory category
) {
}
