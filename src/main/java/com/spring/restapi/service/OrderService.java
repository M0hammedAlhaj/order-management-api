package com.spring.restapi.service;

import com.spring.restapi.dao.OrderDao;
import com.spring.restapi.exception.CouponInValidException;
import com.spring.restapi.exception.OrderNotFoundById;
import com.spring.restapi.dto.OrderResponse;
import com.spring.restapi.mapper.OrderMapper;
import com.spring.restapi.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderDao orderDao;

    private final OrderStatusService orderStatusService;

    private final CouponService couponService;

    private final OrderMapper orderMapper;

    private final EntityManager entityManager;

    public OrderService(OrderDao orderDao,
                        OrderStatusService orderStatusService,
                        CouponService couponService,
                        OrderMapper orderMapper,
                        EntityManager entityManager) {
        this.orderDao = orderDao;
        this.orderStatusService = orderStatusService;
        this.couponService = couponService;
        this.orderMapper = orderMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public Order createOrderByUser(User user) {
        Order order = new Order();
        OrderStatus orderStatus = orderStatusService.findOrderStatus("PENDING");
        order.setOrderStatus(orderStatus);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        return orderDao.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }

    @Transactional(readOnly = true)
    public Order findOrderById(Integer orderId) {
        return orderDao.findById(orderId).orElseThrow(() -> new OrderNotFoundById(orderId));
    }

    @Transactional
    public Order updateOrderStatus(Integer orderId, String orderStatusName) {

        var order = orderDao.findById(orderId);
        var orderStatus = orderStatusService.findOrderStatus(orderStatusName);
        if (order.isPresent()) {
            order.get().setOrderStatus(orderStatus);
            return orderDao.save(order.get());
        }
        throw new OrderNotFoundById(orderId);
    }

    @Transactional
    public Order applyCouponToOrder(Integer orderId, Integer couponId) {
        Order order = orderDao.findById(orderId).orElseThrow(() -> new OrderNotFoundById(orderId));
        Coupon coupon = couponService.findCouponById(couponId);
        LocalDate now = LocalDate.now();
        if (couponService.couponIsValid(coupon, now)) {
            order.setCoupon(coupon);
            return orderDao.save(order);
        }
        throw new CouponInValidException(now);
    }

    public OrderResponse mappingOrderToOrderResponseWithAmountOrder(Order order) {
        BigDecimal amountOrder = calculateAmount(order.getOrderId());
        return orderMapper.convertOrderToOrderResponse(order, amountOrder);


    }

    @Transactional(readOnly = true)
    protected BigDecimal calculateAmount(Integer orderId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> query = criteriaBuilder.createQuery(BigDecimal.class);
        Root<OrderItem> orderItemRoot = query.from(OrderItem.class);

        query.select(criteriaBuilder.sum(orderItemRoot.get("price")))
                .where(criteriaBuilder.equal(orderItemRoot.get("order").get("orderId"), orderId));
        return entityManager.createQuery(query).getSingleResult();
    }

}
