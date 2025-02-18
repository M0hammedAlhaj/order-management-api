package com.spring.restapi.exception;

public class OrderNotFoundById extends RuntimeException {


    public OrderNotFoundById(int orderId) {
        super("Order with id " + orderId + " not found");
    }
}
