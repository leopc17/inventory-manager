package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductIdInvalidException extends DomainException {

    public ProductIdInvalidException() {
    }

    public ProductIdInvalidException(String message) {
        super(message);
    }

    public ProductIdInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
