package com.spring.restapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String name) {
        super(name);
    }

    public UserNotFoundException(Integer userId) {
        this(String.format("User Not Found by Id %d", userId));
    }
}
