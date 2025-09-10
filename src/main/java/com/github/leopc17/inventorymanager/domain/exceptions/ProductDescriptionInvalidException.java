package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductDescriptionInvalidException extends DomainException {
    public ProductDescriptionInvalidException() {
    }

    public ProductDescriptionInvalidException(String message) {
        super(message);
    }

    public ProductDescriptionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}