package com.ned.banking.service;

import org.springframework.http.ResponseEntity;

import com.ned.banking.model.Account;

public interface IAccountDetail {
	
	public ResponseEntity<Account> getAccountDetail(String accountNumber);
}
