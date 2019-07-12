package com.ecom.poc.ecompoc.exception;

public class CustomerCreationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String username;
	
	public CustomerCreationException(String message, String username) {
		this.message = message;
		this.username = username;
	}
	
	public CustomerCreationException() {
		
	}
	
	@Override
	public String toString() {
		return this.message + " " + this.username; 
	}

}
