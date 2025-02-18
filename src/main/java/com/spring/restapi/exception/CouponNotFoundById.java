package com.spring.restapi.exception;

public class CouponNotFoundById extends RuntimeException {


    public CouponNotFoundById(int orderId) {
        super("Coupon with id " + orderId + " not found");
    }
}
