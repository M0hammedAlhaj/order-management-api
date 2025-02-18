package com.spring.restapi.service;

import com.spring.restapi.dao.OrderStatusDao;
import com.spring.restapi.exception.OrderStatusNotFound;
import com.spring.restapi.model.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderStatusService {

    private final OrderStatusDao orderStatusDao;

    public OrderStatusService(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Transactional(readOnly = true)
    public OrderStatus findOrderStatus(String orderStatus) {
        return orderStatusDao.findById(orderStatus).orElseThrow(OrderStatusNotFound::new);
    }

}
