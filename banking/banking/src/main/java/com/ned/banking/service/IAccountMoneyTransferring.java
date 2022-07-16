package com.ned.banking.service;

import org.springframework.http.ResponseEntity;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountTransferRequest;

public interface IAccountMoneyTransferring {
	public ResponseEntity<Object> transferMoney(Account account, AccountTransferRequest request);
}
