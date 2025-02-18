package com.spring.restapi.dto;

public class AnalyticsProductSalesDto {

    private String productName;

    private Long salesCount;

    public AnalyticsProductSalesDto(String productName, Long salesCount) {
        this.productName = productName;
        this.salesCount = salesCount;
    }

    public Long getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Long salesCount) {
        this.salesCount = salesCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
