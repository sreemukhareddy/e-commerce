package com.ecom.poc.ecompoc.requet.bo;

import java.util.List;

import com.ecom.poc.ecompoc.models.Product;

public class OrderBO {

	private String name;

	private Integer credit_card;

	private List<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public OrderBO() {
		super();
	}

	public OrderBO(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public Integer getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(Integer credit_card) {
		this.credit_card = credit_card;
	}

}
