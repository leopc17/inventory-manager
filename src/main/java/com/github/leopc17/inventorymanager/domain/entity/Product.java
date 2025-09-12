package com.github.leopc17.inventorymanager.domain.entity;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import java.math.BigDecimal;
import com.github.leopc17.inventorymanager.domain.exceptions.*;

public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String longDescription;
    private String shortDescription;
    private ProductCategory category;

    public Product() {}

    public Product(Integer id, String name, BigDecimal price, Integer quantity, String longDescription, String shortDescription, ProductCategory category) {
        if (id == null || id <= 0) {
            throw new ProductIdInvalidException("O ID do produto não pode ser nulo ou zero.");
        }

        if (price == null) {
            throw new ProductPriceInvalidException("O preço não pode ser nulo");
        }

        if (name == null || name.isBlank() || name.length() < 3) {
            throw new ProductNameInvalidException("O nome não pode ser vazio, nulo, ou ter menos de 3 caracteres.");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductPriceInvalidException("O preço não pode ser zero ou negativo.");
        }
        if (quantity < 0) {
            throw new ProductQuantityInvalidException("A quantidade não pode ser negativa.");
        }
        if (longDescription.length() < 30) {
            throw new ProductDescriptionInvalidException("A descrição completa não pode ter menos de 30 caracteres.");
        }
        if (shortDescription.length() < 10) {
            throw new ProductDescriptionInvalidException("A descrição breve não pode ter menos de 10 caracteres.");
        }
        if (category == null) {
            throw new ProductCategoryInvalidException("A categoria não pode ser nula.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new ProductIdInvalidException("O ID do produto não pode ser nulo ou zero.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new ProductNameInvalidException("O nome do produto não pode ser nulo, vazio ou ter menos de 3 caracteres.");
        }
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new ProductPriceInvalidException("O preço não pode ser nulo");
        }

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductPriceInvalidException("O preço deve ser maior do que zero.");
        }
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new ProductQuantityInvalidException("A quantidade não pode ser menor que zero.");
        }
        this.quantity = quantity;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        if (longDescription.length() < 30) {
            throw new ProductDescriptionInvalidException("A descrição completa não pode ter menos de 30 caracteres.");
        }
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        if (shortDescription.length() < 10) {
            throw new ProductDescriptionInvalidException("A descrição breve não pode ter menos de 10 caracteres.");
        }
        this.shortDescription = shortDescription;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        if (category == null) {
            throw new ProductCategoryInvalidException("A categoria não pode ser nula.");
        }
        this.category = category;
    }
}