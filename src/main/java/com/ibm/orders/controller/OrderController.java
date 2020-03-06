package com.ibm.orders.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.orders.dto.ProductDto;
import com.ibm.orders.exception.ResourceNotFoundException;
import com.ibm.orders.model.Order;
import com.ibm.orders.model.OrderStatus;
import com.ibm.orders.service.OrderService;
import com.ibm.orders.service.ProductService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	public OrderController(OrderService orderService, ProductService productService) {
		this.orderService = orderService;
		this.productService = productService;
	}
	
	@GetMapping(value = { "","/" })
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody OrderCart cart){
		List<ProductDto> products = cart.getProductsOrdered();
		validateProductExistence(products);
		Order order = new Order();
		order.setStatus(OrderStatus.ISSUED);
		orderService.createOrder(order);
		
		String uri = ServletUriComponentsBuilder
		          .fromCurrentServletMapping()
		          .path("/orders/{id}")
		          .buildAndExpand(order.getId())
		          .toString();
		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Location", uri);

		return new ResponseEntity<> (order, headers, HttpStatus.CREATED);
	}
	
	
	private void validateProductExistence(List<ProductDto> productsOrdered) {
		
		List<ProductDto> list = productsOrdered.stream()
								.filter(op -> Objects.isNull(productService
										.getProductById(op
										.getProduct()
										.getId()))).collect(Collectors.toList());
		
		if(!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not available !!!");
		}
	}


	public static class OrderCart {
		
		private List<ProductDto> productsOrdered;

		public List<ProductDto> getProductsOrdered() {
			return productsOrdered;
		}

		public void setProductsOrdered(List<ProductDto> productsOrdered) {
			this.productsOrdered = productsOrdered;
		}
		
	}
 }
