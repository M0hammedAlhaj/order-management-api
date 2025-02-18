package com.spring.restapi.service;

import com.spring.restapi.dao.OrderItemDao;
import com.spring.restapi.exception.StockQuantityNotAvailableException;
import com.spring.restapi.model.Order;
import com.spring.restapi.model.OrderItem;
import com.spring.restapi.dto.OrderItemRequest;
import com.spring.restapi.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderItemService {

    private final OrderItemDao orderItemDao;

    private final ProductService productService;

    private final OrderService orderService;

    public OrderItemService(OrderItemDao orderItemDao,
                            ProductService productService,
                            OrderService orderService) {
        this.orderItemDao = orderItemDao;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    public OrderItem createOrderItem(OrderItemRequest orderItemRequest) {
        int productId = orderItemRequest.getProductId();
        Product product = productService.findProductById(productId);
        if (!productService.stockQuantityIsAvailable(product, orderItemRequest.getQuantity())) {
            throw new StockQuantityNotAvailableException(product.getProductId());
        }
        Order order = orderService.findOrderById(orderItemRequest.getOrderId());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemRequest.getQuantity());
        orderItem.setPrice(calculatePriceOfProductItem(product.getPrice(), orderItemRequest.getQuantity()));
        return orderItemDao.save(orderItem);
    }

    private BigDecimal calculatePriceOfProductItem(BigDecimal price, int quantity) {
        return BigDecimal.
                valueOf(quantity)
                .multiply(price);
    }
}
