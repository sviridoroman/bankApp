package com.springBank.bankApp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
"некорректный запрос"),
    INCORRECT_PINCODE(HttpStatus.BAD_REQUEST,"не верный пин-код"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND,"счет не найден");

    private final HttpStatus httpStatus;

    private final String message;

    @Override
    public String toString() {
        return httpStatus.value() + " " + name().replace("_", " ");
    }   
}
