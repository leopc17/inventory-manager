package com.github.leopc17.inventorymanager.domain.exceptions;

public class ProductException extends RuntimeException {
    public ProductException(String mensagem) {
        super(mensagem);
    }

    public ProductException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}