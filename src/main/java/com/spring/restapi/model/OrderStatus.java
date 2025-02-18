package com.spring.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "order_status")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})

public class OrderStatus {

    @Id
    @Column(name = "order_status_name")
    @Size(max = 10)
    private String orderStatusName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(orderStatusName, that.orderStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderStatusName);
    }

    public @Size(max = 10) String getOrderStatusName() {
        return orderStatusName;
    }


    public void setOrderStatusName(@Size(max = 10) String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusName='" + orderStatusName + '\'' +
                '}';
    }
}
