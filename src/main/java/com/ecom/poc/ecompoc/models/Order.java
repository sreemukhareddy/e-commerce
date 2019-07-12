package com.ecom.poc.ecompoc.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="order_table")
@NamedQuery(name="get_orders_with_products", query="SELECT o FROM order_table o WHERE customer_name= :name")
public class Order {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="credit_card_id")
	private CreditCard creditCard;
	
	@OneToMany(mappedBy="orders", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<Product> products= new ArrayList<Product>();

	@ManyToOne
	@JoinColumn(name = "customer_name")
	@JsonIgnore
	private Customer customer;

	public Order() {

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addProducts(Product product) {
		this.products.add(product);
	}

	public void addProducts(List<Product> productsWithOrderId) {
		this.products.addAll(productsWithOrderId);
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + "]";
	}

}
