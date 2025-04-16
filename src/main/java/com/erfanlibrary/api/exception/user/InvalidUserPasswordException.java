package com.erfanlibrary.api.exception.user;

public class InvalidUserPasswordException extends RuntimeException{
    public InvalidUserPasswordException(String message) {
        super(message);
    }

}
