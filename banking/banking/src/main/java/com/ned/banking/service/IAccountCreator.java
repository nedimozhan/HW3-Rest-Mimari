package com.ned.banking.service;

import org.springframework.http.ResponseEntity;

import com.ned.banking.model.AccountCreateRequest;

public interface IAccountCreator {
	
	public ResponseEntity<Object> createAccount(AccountCreateRequest request);
}
