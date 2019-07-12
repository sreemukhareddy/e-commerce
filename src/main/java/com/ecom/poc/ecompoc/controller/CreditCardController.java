package com.ecom.poc.ecompoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.poc.ecompoc.models.CreditCard;
import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.service.CreditCardService;

@RestController
@RequestMapping("/credit")
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditCardService;

	@PostMapping(path="/{cus_name}")
	public ResponseEntity<String> addCreditCard(@RequestBody CreditCard creditCard, @PathVariable("cus_name") String name){
		Customer customer= new Customer(name);
		creditCard.setCustomer(customer);
		if(creditCardService.addCreditCard(creditCard)) {
			return new ResponseEntity<String>("The Credit_Card with the Customer_name has been added ", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Some problem has occured...Please troubleshoot the application ", HttpStatus.OK); 
	}
	
}
