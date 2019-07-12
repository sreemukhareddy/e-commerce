package com.ecom.poc.ecompoc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecom.poc.ecompoc.models.Product;

@Repository
@Transactional
public class ProductRepository {

	@Autowired
	private EntityManager entityManager;

	public void addProducts(Product product) {
		entityManager.persist(product);
		System.out.println("The produts have been saved to the db......---------------...........");
	}
	
	public List<Product> findProductsWithOrderId(Integer id) {
		Query createNamedQuery = entityManager.createNamedQuery("get_products_with_order_id");
		createNamedQuery.setParameter("id", id);
		List<Product> products = null;
		products = createNamedQuery.getResultList();
		if(products == null) {
			System.out.println("--------------------PRODUCTS_ARE_NOT_THERE_FOR_A_PARTICULAR_ID-------------------");
		}
		return products;
	}

}
