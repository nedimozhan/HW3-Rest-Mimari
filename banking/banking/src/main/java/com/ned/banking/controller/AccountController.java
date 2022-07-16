package com.ned.banking.controller;

import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;
import com.ned.banking.service.IAccountService;

@RestController
public class AccountController {
	
	private IAccountService accountService;
	
	@Autowired
	public AccountController(@Qualifier("AccountService") IAccountService accountService) {
		this.accountService = accountService;
	}
	
	
	// Web Service - 1 ) Create Account
	@PostMapping(path = "/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequest request) {
	    return this.accountService.createAccountWithAllDetails(request);
	}
	
	// Web Service - 2 ) Account Detail
	@GetMapping(path = "/accounts/{accountnumber}")
	public ResponseEntity<Account> getAccountDetail(@PathVariable String accountnumber) {
	    //Account account = this.accountService.getAccountDetails(id);
	    return this.accountService.getAccountDetails(accountnumber);
	}
	
	// Web Service  - 3 ) Deposite Account
	@PutMapping("/accounts/{accountnumber}/balance")
	public ResponseEntity<Account> depositeMoney(@PathVariable String accountnumber,@RequestBody AccountDepositRequest request) {
	    Account account = this.accountService.depositeToAccount(accountnumber, request);
	    return new ResponseEntity<Account>(account,HttpStatus.OK);
	}
	
	// Web Service  - 4 ) Transfer Money
	@PutMapping("/accounts/{accountnumber}/balance/connection")
	public ResponseEntity<Object> transferMoney(@PathVariable String accountnumber,@RequestBody AccountTransferRequest request) {
	    return this.accountService.transferMoney(accountnumber, request);
	   
	}
	
	// Web Service - 5 ) Get logs
	@GetMapping("/accounts/{accountnumber}/logs")
	@CrossOrigin(origins = "http://localhost:6162")
	public ResponseEntity<Object> accountLog(@PathVariable String accountnumber) {
		return this.accountService.getAccountLogs(accountnumber);
	}
}
