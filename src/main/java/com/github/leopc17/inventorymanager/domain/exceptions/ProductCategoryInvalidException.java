package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductCategoryInvalidException extends DomainException {
    public ProductCategoryInvalidException() {
    }

    public ProductCategoryInvalidException(String message) {
        super(message);
    }

    public ProductCategoryInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}