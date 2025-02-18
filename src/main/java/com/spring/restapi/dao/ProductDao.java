package com.spring.restapi.dao;

import com.spring.restapi.model.Product;
import com.spring.restapi.dto.AnalyticsProductSalesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>
        , JpaSpecificationExecutor<Product> {


    List<Product> findByCategoryCategoryName(String categoryName);

    @Query(value = "SELECT new com.spring.restapi.dto.AnalyticsProductSalesDto(p.productName,COUNT(p.productName)) " +
            " FROM OrderItem ot" +
            " inner join ot.product p" +
            " inner join ot.order o" +
            " where o.orderStatus.orderStatusName in:status " +
            " group by p.productName " +
            " order by COUNT(p.productName) desc "

    )
    List<AnalyticsProductSalesDto> getProductsSeals(@Param("status") Set<String> statues);
}
