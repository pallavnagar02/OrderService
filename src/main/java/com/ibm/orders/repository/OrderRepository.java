package com.ibm.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.orders.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

}
