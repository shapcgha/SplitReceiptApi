package com.shapca.splitaccountapi.controller.handler;

import com.shapca.splitaccountapi.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> onValidateException(ValidationException validationException) {
        String errorMessage = validationException.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
