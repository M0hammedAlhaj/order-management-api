package com.spring.restapi.dto;

import java.math.BigDecimal;

public class AnalyticsRevenueSalesCategoryDto {

    private String categoryName;

    private BigDecimal totalRevenue;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
