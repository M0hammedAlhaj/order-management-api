package com.spring.restapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity
@Table(name = "products")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer stockQuantity;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public @NotNull @NotNull String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull @NotNull String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull @NotNull BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull @NotNull BigDecimal price) {
        this.price = price;
    }

    public @NotNull @NotNull Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@NotNull @NotNull Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
