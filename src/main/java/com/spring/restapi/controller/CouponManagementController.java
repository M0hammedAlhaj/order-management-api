package com.spring.restapi.controller;

import com.spring.restapi.model.Coupon;
import com.spring.restapi.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CouponManagementController {

    private final CouponService couponService;

    public CouponManagementController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/v1/coupons/admin")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon) {
        Coupon couponSaved = couponService.saveCoupon(coupon);
        return ResponseEntity.ok(couponSaved);
    }

}
