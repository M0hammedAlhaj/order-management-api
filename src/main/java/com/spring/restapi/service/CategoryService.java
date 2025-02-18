package com.spring.restapi.service;

import com.spring.restapi.dao.CategoryDao;
import com.spring.restapi.dto.AnalyticsRevenueSalesCategoryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional(readOnly = true)
    public List<AnalyticsRevenueSalesCategoryDto> getRevenueSalesCategoryByStatusOrder(Set<String> statusOrder) {

        return categoryDao.revenueSalesByCategory(statusOrder);
    }

}
