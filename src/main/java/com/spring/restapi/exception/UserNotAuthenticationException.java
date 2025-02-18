package com.spring.restapi.exception;

public class UserNotAuthenticationException extends RuntimeException {

    public UserNotAuthenticationException(String message) {
        super(message);
    }

    public UserNotAuthenticationException() {
    }
}
