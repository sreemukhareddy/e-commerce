package com.ecom.poc.ecompoc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.models.Order;
import com.ecom.poc.ecompoc.models.Product;
import com.ecom.poc.ecompoc.requet.bo.OrderBO;
import com.ecom.poc.ecompoc.response.bo.CustomerWithOrder;
import com.ecom.poc.ecompoc.service.CustomerService;
import com.ecom.poc.ecompoc.service.OrderService;
import com.ecom.poc.ecompoc.service.ProductService;

@RestController
@RequestMapping(path="/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addOrders(@RequestBody OrderBO orderBO) {
		
		Customer customer = new Customer();
		customer.setName(orderBO.getName());
		
		Order order = new Order();
		order.setCustomer(customer);
		
		List<Product> products = orderBO.getProducts();
		System.out.println("---------------------------------" + products);
		for(int i = 0; i < products.size(); i++) {
			products.get(i).setOrders(order);
		}
		
		try {
			orderService.placeOrder(customer, order, products, orderBO.getCredit_card());
		} catch (Exception e) {
			return new ResponseEntity<String>("Something bad has happened...please troubleshoot the application for any errors", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Everything successful", HttpStatus.OK);
	}
	
	@GetMapping(path="/{name}")
	public ResponseEntity<CustomerWithOrder> getOrdersWithCustomerName(@PathVariable("name") String name) {
		CustomerWithOrder customerWithOrder = new CustomerWithOrder();
		if(name == null) {
			customerWithOrder.setMessage("Please provide the username in the pathparam");
			return new ResponseEntity<CustomerWithOrder>(customerWithOrder, HttpStatus.OK);
		}
		List<Order> ordersWithCustomerName = null;
		ordersWithCustomerName = orderService.getOrdersWithCustomerName(name);
		customerWithOrder.setName(name);
		
		Map<Integer, List<Product>> ordersWithProducts = new HashMap<Integer, List<Product>>();
		
		if(ordersWithCustomerName != null) {
			for(Order o : ordersWithCustomerName) {
				Integer id = o.getId();
				System.out.println(String.format("----------------------------", id, "---------------"));
				List<Product> productsWithOrderId = productService.getProductsWithOrderId(id);
				ordersWithProducts.put(id, productsWithOrderId);
			}
			customerWithOrder.setOrders(ordersWithProducts);
			customerWithOrder.setMessage("The orders have been retrieved successfully");
			return new ResponseEntity<CustomerWithOrder>(customerWithOrder, HttpStatus.OK);
		}
		customerWithOrder.setMessage("Something must have gone wrong in this application...");
		return new ResponseEntity<CustomerWithOrder>(customerWithOrder, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteOrder/{cus_name}/{order_id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("cus_name") String name, @PathVariable("order_id") Integer orderId){
		System.out.println("---------------------------------IN_THE_ORDER_CONTROLLER_----------deleteOrder----------------------");
		System.out.println("Customer name --> " + name + " OrderID " + orderId);
		List<Order> orders = customerService.getOrdersWithCustomer(name);
		System.out.println("--------------------------------- " + orders + " ---------------------------------------" );
		boolean isOrderToBeDeleatedThere = false;
		Order orderToBeDeleated = null;
		for(Order o : orders) {
			if(o.getId() == orderId) {
				isOrderToBeDeleatedThere = true;
				orderToBeDeleated = o;
				System.out.println("---------------------------ORDER_TO_BE_DELEATED_IS_FOUND---------------------" + orderToBeDeleated);
				break;
			}
		}
		if(isOrderToBeDeleatedThere) {
			orderService.deleteOrder(orderToBeDeleated);
			return new ResponseEntity<String>("The Order With The Given User Has Been Deleted...", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something bad has happened...please troubleshoot the application for any errors", HttpStatus.OK);
	}
}
