package com.springBank.bankApp.exception;

public class IncorrectPinException extends BaseException{
     public IncorrectPinException(){super(ExceptionEnum.INCORRECT_PINCODE);};
     public IncorrectPinException(String message){super(ExceptionEnum.INCORRECT_PINCODE, message);};
}
