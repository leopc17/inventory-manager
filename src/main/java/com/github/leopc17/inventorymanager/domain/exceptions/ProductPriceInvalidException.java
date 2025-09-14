package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductPriceInvalidException extends DomainException {
    public ProductPriceInvalidException() {
    }

    public ProductPriceInvalidException(String message) {
        super(message);
    }

    public ProductPriceInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}