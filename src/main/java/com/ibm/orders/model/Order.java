package com.ibm.orders.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="orders")
public class Order {
	
	@Id
	private Long id;
	
	@JsonFormat (pattern = "dd/MM/yyyy")
	private LocalDate dateOfIssue;
	
	private Integer numberOfProducts;
	
	private OrderStatus status;

	public Order() {
		
	}
	
	public Order(Long id, LocalDate dateOfIssue, Integer numberOfProducts, OrderStatus status) {
		super();
		this.id = id;
		this.dateOfIssue = dateOfIssue;
		this.numberOfProducts = numberOfProducts;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", dateOfIssue=" + dateOfIssue + ", numberOfProducts=" + numberOfProducts
				+ ", status=" + status + "]";
	}
	
}
