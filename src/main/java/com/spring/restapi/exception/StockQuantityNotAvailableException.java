package com.spring.restapi.exception;

public class StockQuantityNotAvailableException extends RuntimeException {


    public StockQuantityNotAvailableException(int quantity) {
        super("Stock quantity " + quantity + " is not available");
    }
}
