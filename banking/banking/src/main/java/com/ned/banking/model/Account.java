package com.ned.banking.model;

import com.ned.banking.model.AccountCreateRequest;

public class Account {

	private String name;
	private String surname;
	private String email;
	private String tc;
	private String type;
	private String accountNumber;
	private String accountBalance;
	private String accountDateOfUpdate;
	
	public Account(AccountCreateRequest accountCreateRequest,String accountNumber,String accountBalance,String accountDateOfUpdate) {
		this.name = accountCreateRequest.getName();
		this.surname = accountCreateRequest.getSurname();
		this.email = accountCreateRequest.getEmail();
		this.tc = accountCreateRequest.getTc();
		this.type = accountCreateRequest.getType();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountDateOfUpdate = accountDateOfUpdate;
	}
	
	public Account() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTc() {
		return tc;
	}
	
	public void setTc(String tc) {
		this.tc = tc;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public String getAccountDateOfUpdate() {
		return accountDateOfUpdate;
	}
	
	public void setAccountDateOfUpdate(String accountDateOfUpdate) {
		this.accountDateOfUpdate = accountDateOfUpdate;
	}
}