package com.springBank.bankApp.exception;

public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException(){super(ExceptionEnum.ACCOUNT_NOT_FOUND);};
    public AccountNotFoundException(String message){super(ExceptionEnum.ACCOUNT_NOT_FOUND, message);};
}
