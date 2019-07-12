package com.ecom.poc.ecompoc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name="get_products_with_order_id", query="SELECT p FROM Product p where order_id = :id")
public class Product {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String productName;

	@Column
	private Integer productQuantity;

	@Column
	private Integer price;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order orders;

	public Product() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

}
