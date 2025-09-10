package com.github.leopc17.inventorymanager.infrastructure.entity;

import com.github.leopc17.inventorymanager.domain.entity.Product;
import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
public class ProductEntity extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Column(nullable = false, name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(nullable = false, name = "price")
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Column(nullable = false, name = "quantity")
    @Override
    public Integer getQuantity() {
        return super.getQuantity();
    }

    @Column(nullable = false, name = "long_description")
    @Override
    public String getLongDescription() {
        return super.getLongDescription();
    }

    @Column(nullable = false, name = "short_description", columnDefinition = "TEXT")
    @Override
    public String getShortDescription() {
        return super.getShortDescription();
    }

    @Column(nullable = false, name = "category", columnDefinition = "TEXT")
    @Override
    public ProductCategory getCategory() {
        return super.getCategory();
    }

    public ProductEntity() {
    }

    public ProductEntity(Integer id, String name, BigDecimal price, Integer quantity, String longDescription, String shortDescription, ProductCategory category) {
        super(id, name, price, quantity, longDescription, shortDescription, category);
    }
}
