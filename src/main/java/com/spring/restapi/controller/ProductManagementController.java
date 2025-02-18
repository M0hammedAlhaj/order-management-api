package com.spring.restapi.controller;

import com.spring.restapi.dto.ProductDto;
import com.spring.restapi.model.Product;
import com.spring.restapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductManagementController {

    private final ProductService productService;

    public ProductManagementController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/v1/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String categoryName,
                                                           @RequestParam(required = false) BigDecimal minPrice,
                                                           @RequestParam(required = false) BigDecimal maxPrice,
                                                           @RequestParam(required = false) Integer quantityStock) {
        List<Product> products = productService.findProductsByFilter(categoryName, minPrice, maxPrice, quantityStock);
        List<ProductDto> productsDto = products.stream()
                .map(productService::productToProductDto).toList();
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @PostMapping("/v1/admin/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(productService.productToProductDto(product), HttpStatus.OK);
    }

    @PutMapping("/v1/admin/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId
            , @RequestBody Product product) {
        Product productUpdated = productService.updateProduct(product, productId);
        return new ResponseEntity<>(productService.productToProductDto(productUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/v1/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("delete Product By Id + " + productId, HttpStatus.OK);
    }
}
