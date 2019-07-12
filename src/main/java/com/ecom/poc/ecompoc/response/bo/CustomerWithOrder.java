package com.ecom.poc.ecompoc.response.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ecom.poc.ecompoc.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CustomerWithOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String message;
	
	private Map<Integer, List<Product>> orders;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, List<Product>> getOrders() {
		return orders;
	}

	public void setOrders(Map<Integer, List<Product>> orders) {
		this.orders = orders;
	}
	
}
