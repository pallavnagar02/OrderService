package com.ibm.orders.service;

import org.springframework.stereotype.Service;

import com.ibm.orders.exception.ResourceNotFoundException;
import com.ibm.orders.model.Product;
import com.ibm.orders.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProductById(Long id) {
		
		productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}
	
}
