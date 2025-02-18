package com.spring.restapi.controller;

import com.spring.restapi.exception.ProductNotFoundById;
import com.spring.restapi.exception.UserNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundById.class)
    public ResponseEntity<Object> productNotFoundById(ProductNotFoundById p) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", p.getMessage());
        response.put("code", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("code", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> signatureException(SignatureException e) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("code", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
