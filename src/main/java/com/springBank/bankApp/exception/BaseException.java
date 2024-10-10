package com.springBank.bankApp.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    
    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());

    }

    public BaseException(ExceptionEnum exceptionEnum, String message) {
        super(message);
    }
}
