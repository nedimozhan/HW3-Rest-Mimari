package com.ned.banking.model;

public class AccountTransferRequest {
	
	private String transferredAccountNumber;
	private String amount;
	
	public String getTransferredAccountNumber() {
		return transferredAccountNumber;
	}
	
	public void setTransferredAccountNumber(String transferredAccountNumber) {
		this.transferredAccountNumber = transferredAccountNumber;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
