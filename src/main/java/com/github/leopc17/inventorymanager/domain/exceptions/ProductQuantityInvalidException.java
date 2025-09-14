package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductQuantityInvalidException extends DomainException {
    public ProductQuantityInvalidException() {
    }

    public ProductQuantityInvalidException(String message) {
        super(message);
    }

    public ProductQuantityInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}