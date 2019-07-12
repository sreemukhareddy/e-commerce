package com.ecom.poc.ecompoc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="get_credit_card_by_credit_number", query="SELECT c FROM CreditCard c WHERE customer_name = :cus_name")
public class CreditCard {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private Integer credit_card_number;

	@OneToOne
	@JoinColumn(name = "customer_name")
	private Customer customer;

	public CreditCard() {
		super();
	}

	public Integer getCredit_card_number() {
		return credit_card_number;
	}

	public void setCredit_card_number(Integer credit_card_number) {
		this.credit_card_number = credit_card_number;
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

}
