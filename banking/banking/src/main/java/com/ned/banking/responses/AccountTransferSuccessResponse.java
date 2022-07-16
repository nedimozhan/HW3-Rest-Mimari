package com.ned.banking.responses;

public class AccountTransferSuccessResponse {
	private String message;
	
	
	public AccountTransferSuccessResponse() {
		this.message = "Transferred Successfully";
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
}
