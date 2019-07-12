package com.ecom.poc.ecompoc.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.poc.ecompoc.exception.CustomerCreationException;
import com.ecom.poc.ecompoc.exception.CustomerDeletionException;
import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.models.Order;
import com.ecom.poc.ecompoc.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	private final org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

	public boolean createCustomer(Customer customer) {
		Logger.info("STARTED CustomerService :: createCustomer :: WITH_CUSTOMER --> {}", customer);
		try {
			if (customerRepository.createCustomer(customer)) {
				Logger.info("ENDED CustomerService :: createCustomer ");
				return true;
			}
		} catch (CustomerCreationException e) {
			Logger.error("CustomerService :: createCustomer :: ERROR :: {}", e.toString());
			Logger.info("ENDED CustomerService :: createCustomer ");
			return false;
		} catch (Exception e) {
			Logger.error("CustomerService :: createCustomer :: ERROR :: {}", e.toString());
			Logger.info("ENDED CustomerService :: createCustomer ");
			throw e;
		}
		Logger.info("ENDED CustomerService :: createCustomer ");
		return false;
	}

	public boolean isCustomerExists(Customer customer) {
		boolean customerExist = customerRepository.isCustomerExist(customer);
		if (!customerExist)
			return false;
		return true;
	}

	public boolean deleteCustomer(Customer customer) throws CustomerDeletionException, Exception {
		Logger.info("STARTED :: CustomerService :: deleteCustomer :: WITH_CUSTOMER --> {}", customer);
		boolean isDeleted = false;
		try {
			isDeleted = customerRepository.deleteCustomer(customer);
		} catch (CustomerDeletionException cde) {
			Logger.error("ERROR :: CustomerService :: deleteCustomer :: {}", cde);
			Logger.info("ENDED :: CustomerService :: deleteCustomer :: WITH_CUSTOMER --> {}", customer);
			throw cde;
		} catch (Exception e) {
			Logger.error("ERROR :: CustomerService :: deleteCustomer :: {}", e);
			Logger.info("ENDED :: CustomerService :: deleteCustomer :: WITH_CUSTOMER --> {}", customer);
			throw e;
		}
		Logger.info("ENDED :: CustomerService :: deleteCustomer :: WITH_CUSTOMER --> {}", customer);
		return isDeleted;
	}

	public List<Order> getOrdersWithCustomer(String name) {
		List<Order> orders = null;
		try {
			orders = customerRepository.getOrdersWithCustomer(name);
		} catch (Exception e) {

		}
		return orders;
	}
}
