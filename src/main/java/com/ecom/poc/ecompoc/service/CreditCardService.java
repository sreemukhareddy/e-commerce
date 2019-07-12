package com.ecom.poc.ecompoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.poc.ecompoc.models.CreditCard;
import com.ecom.poc.ecompoc.repository.CreditCardReposiotry;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardReposiotry creditCardReposiotry;
	
	@Autowired
	private CustomerService customerService;
	
	public boolean addCreditCard(CreditCard creditCard) {
		try {
			if(!customerService.isCustomerExists(creditCard.getCustomer())) {
				System.out.println("-----------------------------------------------THE CUSTOMER DOESNT EXISTS----------------------------");
				return false;
			}
			
			creditCardReposiotry.addCreditCard(creditCard);
			return true;
		} catch(Exception e) {
			
		}
		System.out.println("-----------------------------------------------ERROR----------------------------");
		return false;
	}
}
