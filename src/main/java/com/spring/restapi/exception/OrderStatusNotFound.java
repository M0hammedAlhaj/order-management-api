package com.spring.restapi.exception;

public class OrderStatusNotFound extends RuntimeException {

    public OrderStatusNotFound(String message) {
        super(message);
    }
    public OrderStatusNotFound(){
        super("Order Status Not Found");
    }
}
