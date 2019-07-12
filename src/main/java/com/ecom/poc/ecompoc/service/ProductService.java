package com.ecom.poc.ecompoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.poc.ecompoc.models.Product;
import com.ecom.poc.ecompoc.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProductsWithOrderId(Integer id){
		List<Product> products;
		try {
			products = productRepository.findProductsWithOrderId(id);
			return products;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
