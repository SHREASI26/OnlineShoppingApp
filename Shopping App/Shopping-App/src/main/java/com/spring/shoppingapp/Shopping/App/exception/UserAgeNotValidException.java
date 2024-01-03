package com.spring.shoppingapp.Shopping.App.exception;

public class UserAgeNotValidException extends RuntimeException{
    public UserAgeNotValidException(String message) {
        super(message);
    }
}
