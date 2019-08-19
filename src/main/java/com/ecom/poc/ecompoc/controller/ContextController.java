package com.ecom.poc.ecompoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/context")
public class ContextController {

	@GetMapping
	public ResponseEntity<String> getContext(@RequestHeader("hello") String request){
		System.out.println(request);
		System.out.println("---------------------------");
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
}
