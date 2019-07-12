package com.ecom.poc.ecompoc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecom.poc.ecompoc.exception.CustomerCreationException;
import com.ecom.poc.ecompoc.exception.CustomerDeletionException;
import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.models.Order;

@Repository
@Transactional
public class CustomerRepository {

	@Autowired
	private EntityManager manager;

	private final org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

	@Transactional(rollbackOn = { CustomerCreationException.class })
	public boolean createCustomer(Customer customer) throws CustomerCreationException {
		Logger.info("STARTED :: CustomerRepository :: createCustomer :: WITH username {}", customer.getName());
		try {
			manager.persist(customer);
			Logger.info("CUSTOMER PERSISTED IN THE DB");
			manager.flush();
			Logger.info("CUSTOMER FLUSHED INTO THE DB");
			manager.detach(customer);
			Logger.info("CUSTOMER DETACHED FROM THE ENTITY_MANAGER");
		} catch (PersistenceException e) {
			Logger.error("THE USER MIGHT BE ALREADY PRESENT IN THE DB");
			CustomerCreationException customerCreationException = new CustomerCreationException("The User with the username is already existing :: ", customer.getName());
			Logger.info("ENDED :: CustomerRepository :: createCustomer");
			throw customerCreationException;
		} catch (Exception e) {
			Logger.error("UNKNOWN EXCEPTION {}", e.toString());
			Logger.info("ENDED :: CustomerRepository :: createCustomer");
			return false;
		}
		Logger.info("ENDED :: CustomerRepository :: createCustomer");
		return true;
	}

	public boolean isCustomerExist(Customer customer) {
		Customer find = null;
		try {
			find = manager.find(Customer.class, customer.getName());
		} catch (Exception e) {
			return false;
		}
		if (find == null) {
			return false;
		}
		return true;
	}

	@Transactional(rollbackOn = { CustomerDeletionException.class })
	public boolean deleteCustomer(Customer customer) throws CustomerDeletionException, Exception {
		Logger.info("STARTED :: CustomerRepository :: deleteCustomer :: WITH_CUSTOMER_NAME :: {}", customer.getName());
		try {
			customer = manager.find(Customer.class, customer.getName());
			Logger.info("CustomerRepository :: deleteCustomer :: FOUND THE CUSTOMER FROM THE DB WITH THE INFO {}",
					customer);
			Logger.info("CustomerRepository :: deleteCustomer :: REMOVING THE CUSTOMER FROM THE DB");
			manager.remove(customer);
			Logger.info("ENDED :: CustomerRepository :: deleteCustomer");
			return true;
		} catch (NullPointerException e) {
			Logger.info("---------------NullPointerException--------------------");
			Logger.error("CustomerRepository :: deleteCustomer :: THE CUSTOMER {} IS FOUND EITHER WITH NULL OR UNSUPPOERTED_FORM",customer);
			Logger.info("ENDED :: CustomerRepository :: deleteCustomer");
			throw new CustomerDeletionException();
		} catch (IllegalArgumentException e) {
			Logger.info("---------------IllegalArgumentException--------------------");
			Logger.error("CustomerRepository :: deleteCustomer :: THE CUSTOMER {} IS FOUND EITHER WITH NULL OR UNSUPPOERTED_FORM",customer);
			Logger.info("ENDED :: CustomerRepository :: deleteCustomer");
			throw new CustomerDeletionException();
		} catch (Exception e) {
			Logger.info("---------------Exception--------------------");
			Logger.error("CustomerRepository :: deleteCustomer :: UNKNOWN EXCEPTION {}", e.toString());
			Logger.info("ENDED :: CustomerRepository :: deleteCustomer");
			throw new Exception("SOMETHING_PROBLEM_UNEXPECTED_HAS_OCCURED-WHILE_DELETING_THE_USER " + customer);
		}
	}

	public List<Order> getOrdersWithCustomer(String name) {
		Customer customer = null;
		customer = manager.find(Customer.class, name);
		if (customer == null) {
			System.out.println("------------------------------The customer with the given Name is not available-------------------------");
			return null;
		}
		List<Order> orders = customer.getOrders();
		System.out.println("------------------------------------- " + orders
				+ " ------------------------------------------------------");
		return orders;
	}

}
