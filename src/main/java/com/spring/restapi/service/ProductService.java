package com.spring.restapi.service;

import com.spring.restapi.dao.ProductDao;
import com.spring.restapi.dto.AnalyticsProductSalesDto;
import com.spring.restapi.dto.ProductDto;
import com.spring.restapi.exception.ProductNotFoundById;
import com.spring.restapi.mapper.ProductMapper;
import com.spring.restapi.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductDao productDao;

    private final ProductMapper productMapper;


    public ProductService(ProductDao productDao, ProductMapper productMapper) {
        this.productDao = productDao;
        this.productMapper = productMapper;
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByCategoryName(String categoryName) {
        return productDao.findByCategoryCategoryName(categoryName);
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByFilter(String categoryName
            , BigDecimal minPrice
            , BigDecimal maxPrice
            , Integer quantityStock) {
        ProductSpecification productSpecification = new ProductSpecification(categoryName, minPrice, maxPrice, quantityStock);

        return productDao.findAll(productSpecification);
    }

    @Transactional
    public Product updateProduct(Product product, int productId) {
        if (productDao.findById(productId).isPresent()) {
            product.setProductId(productId);
            return productDao.save(product);
        }
        throw new ProductNotFoundById(productId);
    }

    @Transactional
    public void deleteProduct(int productId) {
        if (!productDao.existsById(productId)) {
            throw new ProductNotFoundById(productId);
        }
        productDao.deleteById(productId);
    }

    @Transactional(readOnly = true)
    public Product findProductById(int productId) {
        return productDao.findById(productId).orElseThrow(() -> new ProductNotFoundById(productId));
    }

    @Transactional(readOnly = true)
    public boolean productExists(int productId) {
        return productDao.existsById(productId);
    }

    public boolean stockQuantityIsAvailable(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            return false;
        }
        return true;
    }


    @Transactional(readOnly = true)
    public List<AnalyticsProductSalesDto> getProductSalesByStatusOrder(Set<String> strings) {
        return productDao.getProductsSeals(strings);
    }

    public ProductDto productToProductDto(Product product) {
        return productMapper.productToProductDto(product);
    }
}
