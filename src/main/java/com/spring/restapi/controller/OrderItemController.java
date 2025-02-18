package com.spring.restapi.controller;

import com.spring.restapi.model.OrderItem;
import com.spring.restapi.dto.OrderItemRequest;
import com.spring.restapi.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/v1/order-items")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        OrderItem orderItem = orderItemService.createOrderItem(orderItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }


}
