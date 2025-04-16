package com.erfanlibrary.api.exception.user;

public class InvalidUserEmailException extends RuntimeException{
    public InvalidUserEmailException(String message) {
        super(message);
    }

}
