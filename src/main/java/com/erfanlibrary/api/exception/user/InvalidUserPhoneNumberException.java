package com.erfanlibrary.api.exception.user;

public class InvalidUserPhoneNumberException extends RuntimeException{
    public InvalidUserPhoneNumberException(String message) {
        super(message);
    }
}
