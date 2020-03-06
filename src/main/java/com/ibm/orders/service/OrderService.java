package com.ibm.orders.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.ibm.orders.model.Order;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();
    
    Order getOrderById(Long id);

    Order createOrder(@NotNull(message = "The order cannot be null.") @Valid Order order);

}
