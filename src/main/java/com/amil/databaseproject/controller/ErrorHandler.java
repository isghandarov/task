package com.amil.databaseproject.controller;

import com.amil.databaseproject.exception.ApiError;
import com.amil.databaseproject.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiError onValidationException(NotFoundException e) {
        return ApiError.builder()
                .messageCode("requested_resource_not_found")
                .message(e.getMessage())
                .build();
    }
}
