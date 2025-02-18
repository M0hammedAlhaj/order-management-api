package com.spring.restapi.service;

import com.spring.restapi.model.StatusOrder;
import com.spring.restapi.dto.AnalyticsProductSalesDto;
import com.spring.restapi.dto.AnalyticsRevenueSalesCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnalyticsReportingService {

    private final CategoryService categoryService;

    private final ProductService productService;

    public AnalyticsReportingService(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }



    public List<AnalyticsRevenueSalesCategoryDto> getRevenueSalesCategory() {
        return categoryService.getRevenueSalesCategoryByStatusOrder(trackingStatesOrderSealing());
    }


    public List<AnalyticsProductSalesDto> getProductSales() {
        return productService.getProductSalesByStatusOrder(trackingStatesOrderSealing());
    }

    private Set<String> trackingStatesOrderSealing() {
        return Set.of(StatusOrder.PROCESSING.getStatus(),
                StatusOrder.PENDING.getStatus());
    }

}
