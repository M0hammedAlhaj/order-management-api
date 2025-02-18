package com.spring.restapi.exception;

public class ProductNotFoundById extends RuntimeException {


    public ProductNotFoundById(int productId) {
        super("Product with id " + productId + " not found");
    }
}
