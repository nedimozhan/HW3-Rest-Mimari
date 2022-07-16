package com.ned.banking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;

public interface IAccountService {
	
	public ResponseEntity<Object> createAccountWithAllDetails(AccountCreateRequest request);
	public ResponseEntity<Account> getAccountDetails(String id);
	public Account depositeToAccount(String id,AccountDepositRequest request);
	public ResponseEntity<Object> transferMoney(String id,AccountTransferRequest request);
	public ResponseEntity<Object> getAccountLogs(String accountNumber);
}
