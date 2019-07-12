package com.ecom.poc.ecompoc.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@Column(nullable = false, unique = true)
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Order> orders = new ArrayList<Order>();

	@OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private CreditCard creditCard;

	public Customer() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrders(Order order) {
		getOrders().add(order);
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + "]";
	}

}
