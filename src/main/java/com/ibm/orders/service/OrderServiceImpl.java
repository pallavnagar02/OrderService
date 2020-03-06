package com.ibm.orders.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ibm.orders.model.Order;
import com.ibm.orders.repository.OrderRepository;
import com.ibm.orders.exception.ResourceNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Override
	public Iterable<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

    @Override
	public Order getOrderById(Long id) {
		return this.orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}
    
    @Override
	public Order createOrder(Order order) {
    	order.setDateOfIssue(LocalDate.now());
		return this.orderRepository.save(order);
	}
    
}
