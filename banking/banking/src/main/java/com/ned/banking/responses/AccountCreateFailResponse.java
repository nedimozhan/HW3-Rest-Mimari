package com.ned.banking.responses;

public class AccountCreateFailResponse {
	private String message;
	
	public AccountCreateFailResponse() {
		this.message = "Invalid Account Type: ";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
