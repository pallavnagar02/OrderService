package com.ibm.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.orders.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
	
}