package com.springBank.bankApp.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    
    public BaseException(ExceptionEnum exception) {
        super(exception.getMessage());

    }

    public BaseException(ExceptionEnum exception, String message) {
        super(message);
    }
}
