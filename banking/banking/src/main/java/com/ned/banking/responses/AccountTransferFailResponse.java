package com.ned.banking.responses;

public class AccountTransferFailResponse {
	
	private String message;
	
	public AccountTransferFailResponse() {
		this.message = "Insufficient balance";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
