package com.spring.restapi.model;

public enum StatusOrder {

    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    SHIPPED("SHIPPED"),;

    private final String status;

    StatusOrder(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }


}
