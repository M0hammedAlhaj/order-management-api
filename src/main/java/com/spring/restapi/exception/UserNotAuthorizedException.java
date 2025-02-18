package com.spring.restapi.exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(String message) {
        super(message);
    }

    public UserNotAuthorizedException() {
    }
}
