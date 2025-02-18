package com.spring.restapi.controller;

import com.spring.restapi.dto.OrderResponse;
import com.spring.restapi.exception.UserNotFoundException;
import com.spring.restapi.model.Order;
import com.spring.restapi.model.User;
import com.spring.restapi.service.OrderService;
import com.spring.restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderManagementController {

    private final UserService userService;

    private final OrderService orderService;

    public OrderManagementController(UserService userService,
                                     OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<OrderResponse> createOrder(Authentication authentication) {

        User user = userService.findUserByEmail(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException(authentication.getName()));

        Order orderCreated = orderService.createOrderByUser(user);
        OrderResponse orderResponse = orderService.mappingOrderToOrderResponseWithAmountOrder(orderCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }


    @GetMapping("/v1/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/v1/admin/orders/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId) {

        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/v1/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByUser(Authentication authentication) {

        User user = userService.findUserByEmail(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        List<OrderResponse> userOrders = user.getOrders()
                .stream()
                .map(orderService::mappingOrderToOrderResponseWithAmountOrder)
                .toList();

        return ResponseEntity.ok(userOrders);
    }

    @PutMapping("/v1/admin/orders/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Integer orderId
            , @RequestParam String orderStatus) {

        OrderResponse orderUpdatedStatus = orderService.mappingOrderToOrderResponseWithAmountOrder(
                orderService.updateOrderStatus(orderId, orderStatus));

        return ResponseEntity.ok(orderUpdatedStatus);
    }

    @PutMapping("/v1/orders/{orderId}/applyCoupon")
    public ResponseEntity<OrderResponse> applyCoupon(@PathVariable Integer orderId
            , @RequestParam Integer couponId) {

        OrderResponse orderAppliedCoupon = orderService.mappingOrderToOrderResponseWithAmountOrder(orderService
                .applyCouponToOrder(orderId, couponId));
        return ResponseEntity.ok(orderAppliedCoupon);
    }


}
