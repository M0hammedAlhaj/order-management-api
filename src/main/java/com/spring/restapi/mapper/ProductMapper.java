package com.spring.restapi.mapper;

import com.spring.restapi.dto.ProductDto;
import com.spring.restapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "nameProduct", source = "product.productName")
    @Mapping(target = "priceProduct",source = "product.price")
    @Mapping(target = "descriptionProduct",source = "product.description")
    ProductDto productToProductDto(Product product);

}
