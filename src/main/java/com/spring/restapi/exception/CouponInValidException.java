package com.spring.restapi.exception;

import java.time.LocalDate;

public class CouponInValidException extends RuntimeException {


    public CouponInValidException(LocalDate expireDate) {
        super("Coupon has Expired date : " + expireDate);
    }
}
