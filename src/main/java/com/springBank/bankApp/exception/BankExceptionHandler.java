package com.springBank.bankApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankExceptionHandler {
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleException(BaseException ex, String message) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
