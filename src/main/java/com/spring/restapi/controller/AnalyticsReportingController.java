package com.spring.restapi.controller;


import com.spring.restapi.dto.AnalyticsProductSalesDto;
import com.spring.restapi.dto.AnalyticsRevenueSalesCategoryDto;
import com.spring.restapi.service.AnalyticsReportingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnalyticsReportingController {

    private final AnalyticsReportingService analyticsReportingService;


    public AnalyticsReportingController(AnalyticsReportingService analyticsReportingService) {
        this.analyticsReportingService = analyticsReportingService;
    }

    @GetMapping("/v1/analytics/revenue-sales-by-categories")
    public ResponseEntity<List<AnalyticsRevenueSalesCategoryDto>> revenueSalesCategory() {
        return ResponseEntity.ok(analyticsReportingService.getRevenueSalesCategory());
    }

    @GetMapping("/v1/analytics/products-sales")
    public ResponseEntity<List<AnalyticsProductSalesDto>> productsSalesCategory() {
        return ResponseEntity.ok(analyticsReportingService.getProductSales());
    }

}
