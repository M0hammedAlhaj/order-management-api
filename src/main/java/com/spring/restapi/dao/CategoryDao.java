package com.spring.restapi.dao;

import com.spring.restapi.model.Category;
import com.spring.restapi.dto.AnalyticsRevenueSalesCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    @Query("select c.categoryName, sum(p.price) as revenueSales from OrderItem i " +
            "inner join i.order o " +
            "inner join i.product p " +
            "inner join p.category c " +
            "where o.orderStatus.orderStatusName in:status " +
            "group by c.categoryName" +
            " order by  revenueSales desc ")

    List<AnalyticsRevenueSalesCategoryDto> revenueSalesByCategory(@Param(value = "status") Set<String> status);

}
