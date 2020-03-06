package com.ibm.orders.service;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.ibm.orders.model.Product;

@Validated
public interface ProductService {
	
	@NotNull Iterable<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProductById(Long id);
}
