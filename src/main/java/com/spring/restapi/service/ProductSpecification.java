package com.spring.restapi.service;

import com.spring.restapi.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private String categoryName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minStock;

    public ProductSpecification(String categoryName,
                                BigDecimal minPrice,
                                BigDecimal maxPrice,
                                Integer minStock) {

        this.categoryName = categoryName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minStock = minStock;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (categoryName != null) {
            predicates.add(criteriaBuilder.equal(root.get("category").get("categoryName"), categoryName));
        }
        if (minPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (minStock != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stockQuantity"), minStock));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
