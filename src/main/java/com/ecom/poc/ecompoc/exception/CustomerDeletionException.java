package com.ecom.poc.ecompoc.exception;

public class CustomerDeletionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	public CustomerDeletionException(String username) {
		this.username = username;
	}

	public CustomerDeletionException() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "THE CUSTOMER WITH THE GIVEN ID " + this.username + "IS NOT THERE IN THE DB";
	}

}
