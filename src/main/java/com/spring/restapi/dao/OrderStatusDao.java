package com.spring.restapi.dao;

import com.spring.restapi.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderStatusDao extends JpaRepository<OrderStatus, String> {

}
