package com.github.leopc17.inventorymanager.infrastructure.adapter.input.exception.handler;

import com.github.leopc17.inventorymanager.application.exception.ProductNotFoundException;
import com.github.leopc17.inventorymanager.domain.exceptions.DomainException;
import com.github.leopc17.inventorymanager.infrastructure.adapter.input.exception.apierror.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), INTERNAL_SERVER_ERROR, "Unexpected error.");
        return buildResponse(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), NOT_FOUND, ex.getLocalizedMessage());
        return buildResponse(apiError);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiError> handleDomainException(DomainException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), BAD_REQUEST, ex.getLocalizedMessage());
        return buildResponse(apiError);
    }

    private ResponseEntity<ApiError> buildResponse(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.status());
    }
}
