package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductNameInvalidException extends DomainException {
    public ProductNameInvalidException() {
    }

    public ProductNameInvalidException(String message) {
        super(message);
    }

    public ProductNameInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}