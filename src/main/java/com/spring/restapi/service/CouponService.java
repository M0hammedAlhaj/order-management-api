package com.spring.restapi.service;

import com.spring.restapi.dao.CouponDao;
import com.spring.restapi.exception.CouponNotFoundById;
import com.spring.restapi.model.Coupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CouponService {

    private final CouponDao couponDao;


    public CouponService(CouponDao couponDao) {
        this.couponDao = couponDao;
    }


    @Transactional(readOnly = true)
    public Coupon findCouponById(Integer couponId) {
        return couponDao.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundById(couponId));
    }

    @Transactional(readOnly = true)
    public boolean couponIsValid(Coupon coupon, LocalDate dateAppliedCoupon) {
        LocalDate expiryDate = coupon.getExpiryDate();
        return expiryDate.isAfter(dateAppliedCoupon);
    }
    @Transactional
    public Coupon saveCoupon(Coupon coupon) {
        coupon.setCouponId(null);
        return couponDao.save(coupon);
    }

}
