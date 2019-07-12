package com.ecom.poc.ecompoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.poc.ecompoc.exception.CustomerCreationException;
import com.ecom.poc.ecompoc.models.CreditCard;
import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.models.Order;
import com.ecom.poc.ecompoc.models.Product;
import com.ecom.poc.ecompoc.repository.CreditCardReposiotry;
import com.ecom.poc.ecompoc.repository.CustomerRepository;
import com.ecom.poc.ecompoc.repository.OrderReposiotry;
import com.ecom.poc.ecompoc.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderReposiotry orderReposiotry;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CreditCardReposiotry creditCardReposiotry;

	public void placeOrder(Customer customer, Order order, List<Product> products, Integer credit_card_number) {
		if(!customerRepository.isCustomerExist(customer)) {
			try {
				customerRepository.createCustomer(customer);
			} catch (CustomerCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		CreditCard findCreditCard = creditCardReposiotry.findCreditCard(credit_card_number, customer.getName());
		System.out.println("-------------------------credit_card_number-----------------" + findCreditCard);
		
		if(findCreditCard == null) {
			CreditCard creditCard = new CreditCard();
			creditCard.setCustomer(customer);
			creditCard.setCredit_card_number(credit_card_number);
			creditCardReposiotry.addCreditCard(creditCard);
		}
		findCreditCard = creditCardReposiotry.findCreditCard(credit_card_number, customer.getName());
		order.setCustomer(customer);
		order.setCreditCard(findCreditCard);
		orderReposiotry.placeOrder(order);
		
		for(Product p : products) {
			productRepository.addProducts(p);
		}
	}
	
	public List<Order> getOrdersWithCustomerName(String name) {
		List<Order> customerWithOrders = null;
		try {
			customerWithOrders = orderReposiotry.getCustomerWithOrders(name);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		return customerWithOrders;
	}
	
	public boolean deleteOrder(Order order) {
		System.out.println("-------------------------------------ORDER " + order + " ------------------------------------");
		try {
			orderReposiotry.deleteOrderWithOrderID(order);
			return true;
		} catch( Exception e) {
			
		}
		return false;
	}

}
