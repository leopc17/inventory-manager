package com.github.leopc17.inventorymanager.domain.entity;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import java.math.BigDecimal;
import com.github.leopc17.inventorymanager.domain.exceptions.ProductException;

public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String longDescription;
    private String shortDescription;
    private ProductCategory category;

    public Product(Integer id, String name, BigDecimal price, String longDescription, String shortDescription, ProductCategory category) {
        if (id == null || id <= 0) {
            throw new ProductException("O ID do produto não pode ser nulo ou zero.");
        }
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new ProductException("O nome não pode ser vazio, nulo, ou ter menos de 3 caracteres.");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductException("O preço do produto não pode ser zero ou negativo.");
        }
        if (longDescription.length() < 30) {
            throw new ProductException("A descrição completa não pode ter menos de 30 caracteres.");
        }
        if (shortDescription.length() < 10) {
            throw new ProductException("A descrição breve não pode ter menos de 10 caracteres.");
        }
        if (category == null) {
            throw new ProductException("A categoria não pode ser nula.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new ProductException("O nome do produto não pode ser vazio.");
        }
        if (name.length() < 3) {
            throw new ProductException("O nome não pode ter menos de 3 caracteres.");
        }
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductException("O preço deve ser maior do que zero.");
        }
        this.price = price;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        if (longDescription.length() < 30) {
            throw new ProductException("A descrição completa não pode ter menos de 30 caracteres.");
        }
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        if (shortDescription.length() < 10) {
            throw new ProductException("A descrição breve não pode ter menos de 10 caracteres.");
        }
        this.shortDescription = shortDescription;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        if (category == null) {
            throw new ProductException("A categoria não pode ser nula.");
        }
        this.category = category;
    }
}