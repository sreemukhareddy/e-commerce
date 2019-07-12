package com.ecom.poc.ecompoc.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.poc.ecompoc.exception.CustomerDeletionException;
import com.ecom.poc.ecompoc.models.Customer;
import com.ecom.poc.ecompoc.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private final org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		Logger.info("STARTED CustomerController :: createCustomer :: WITH_CUSTOMER --> {}", customer);
		try {
			if (customerService.createCustomer(customer)) {
				Logger.info("ENDED CustomerController :: createCustomer");
				return new ResponseEntity<String>("The customer has been created with the name " + customer.getName(),
						HttpStatus.OK);
			} else {
				Logger.warn("EXCEPTION :: THE_USER_WITH_THE_USER_NAME --> {} IS_ALREADY_EXISTING_IN_THE_DB",
						customer.getName());
				Logger.info("ENDED CustomerController :: createCustomer");
				return new ResponseEntity<String>("THE_USER_ALREADY_EXISTS...!!!", HttpStatus.OK);
			}
		} catch (Exception e) {
			Logger.error("EXCEPTION :: {}", e);
		}
		Logger.info("ENDED CustomerController :: createCustomer");
		return new ResponseEntity<String>("A problem has been encountered...!!!", HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{cus_name}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("cus_name") String name) {
		Logger.info("STARTED CustomerController :: deleteCustomer :: WITH_CUSTOMER --> {}", name);
		Customer customer = new Customer(name);
		try {
			if (customerService.deleteCustomer(customer)) {
				Logger.info("ENDED :: CustomerController :: deleteCustomer :: WITH_CUSTOMER --> {}", name);
				return new ResponseEntity<String>("The Customer with the given user_name " + name + " has been deleted",
						HttpStatus.OK);
			}
		} catch (CustomerDeletionException e) {
			Logger.error(" CustomerDeletionException ");
			Logger.error("ERROR :: CustomerController :: deleteCustomer :: {}", e.toString());
			Logger.info("ENDED :: CustomerController :: deleteCustomer :: WITH_CUSTOMER --> {}", name);
			return new ResponseEntity<String>("THE CUSTOMER WITH THE GIVEN NAME IS NOT PRESENT IN THE DB",
					HttpStatus.OK);
		} catch (Exception e) {
			Logger.error(" EXCEPTION ");
			Logger.error("ERROR :: CustomerController :: deleteCustomer :: {}", e.toString());
			Logger.info("ENDED :: CustomerController :: deleteCustomer :: WITH_CUSTOMER --> {}", name);
			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);
		}
		Logger.info("ENDED :: CustomerController :: deleteCustomer :: WITH_CUSTOMER --> {}", name);

		return new ResponseEntity<String>(
				String.format("Some problem has occured or the customer might not have been persisted"), HttpStatus.OK);
	}

}
