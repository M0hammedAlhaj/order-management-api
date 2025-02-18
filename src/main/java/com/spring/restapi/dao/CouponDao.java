package com.spring.restapi.dao;

import com.spring.restapi.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CouponDao extends JpaRepository<Coupon, Integer> {


}
