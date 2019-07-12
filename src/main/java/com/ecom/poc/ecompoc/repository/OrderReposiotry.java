package com.ecom.poc.ecompoc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecom.poc.ecompoc.models.Order;

@Repository
@Transactional
public class OrderReposiotry {

	@Autowired
	private EntityManager manager;

	public void placeOrder(Order order) {
		manager.persist(order);	
	}
	
	public List<Order> getCustomerWithOrders(String name) {
		Query createNamedQuery = manager.createNamedQuery("get_orders_with_products");
		createNamedQuery.setParameter("name", name);
		List<Order> resultList = null;
		resultList = createNamedQuery.getResultList();
		if(resultList == null) {
			System.out.println("-----------------------------------THE CUSTOMER WITH THE NAME HASN'T ADDED ANY ORDERS------------------");
		} else {
			System.out.println("------------------" + resultList);
		}
		return resultList;
	}
	
	public void deleteOrderWithOrderID(Order order) {
		manager.remove(order);
	}
}
