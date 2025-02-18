package com.spring.restapi.mapper;

import com.spring.restapi.dto.OrderResponse;
import com.spring.restapi.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(source = "order.orderId", target = "id")
    @Mapping(source = "order.orderStatus.orderStatusName", target = "status")
    @Mapping(source = "order.orderDate", target = "dateTime")
    @Mapping(expression = "java(order.getUser().fullName())", target = "nameCustomer")
    @Mapping(source = "amount", target = "amount")
    OrderResponse convertOrderToOrderResponse(Order order, BigDecimal amount);


}

