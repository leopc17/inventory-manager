package com.github.leopc17.inventorymanager.infrastructure.adapter.input.exception.apierror;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,
        HttpStatus status,
        String message
) {
}
