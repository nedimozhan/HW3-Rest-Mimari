package com.ned.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ned.banking.logoperation.ILogEncoder;
import com.ned.banking.logoperation.Log;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;

@Component
@Qualifier("AccountService")
public class AccountService implements IAccountService{
	
	IAccountCreator accountCreatorService;
	IAccountDetail accountDetailService;
	IAccountMoneyDepositor accountMoneyDepositorService;
	IAccountMoneyTransferring accountMoneyTransferringService;
	IAccountLog accountLogService;
	
	private KafkaTemplate<String,String> producer;
	private ILogEncoder transferEncoder;
	private ILogEncoder depositEncoder;
	
	@Autowired
	public void setProducer(KafkaTemplate<String, String> producer) {
		this.producer = producer;
	}
	
	@Autowired
	public void setTransferEncoder(@Qualifier("TransferLogEncoder") ILogEncoder transferEncoder) {
		this.transferEncoder = transferEncoder;
	}
	
	@Autowired
	public void setDepositEncoder(@Qualifier("DepositeLogEncoder") ILogEncoder depositEncoder) {
		this.depositEncoder = depositEncoder;
	}

	@Autowired
	public AccountService(
			@Qualifier("AccountCreatorService") IAccountCreator accountCreatorService,
			@Qualifier("AccountDetailService") IAccountDetail accountDetailService,
			@Qualifier("AccountMoneyDepositorService") IAccountMoneyDepositor accountMoneyDepositorService, 
			@Qualifier("AccountMoneyTransferService") IAccountMoneyTransferring accountMoneyTransferringService,
			@Qualifier("AccountLogService") IAccountLog accountLogService
			) {
		
		this.accountCreatorService = accountCreatorService;
		this.accountDetailService = accountDetailService;
		this.accountMoneyDepositorService = accountMoneyDepositorService;
		this.accountMoneyTransferringService = accountMoneyTransferringService;
		this.accountLogService = accountLogService;
	}

	@Override
	public ResponseEntity<Object> createAccountWithAllDetails(AccountCreateRequest request) {		
		return this.accountCreatorService.createAccount(request);
	}
	
	@Override
	public ResponseEntity<Account> getAccountDetails(String accountNumber) {
		return this.accountDetailService.getAccountDetail(accountNumber);
	}
	
	@Override
	public Account depositeToAccount(String accountNumber,AccountDepositRequest request) {
		
		Account account = getAccountDetails(accountNumber).getBody();
		
		// Encode to (2341231231 deposit amount:100) format
		String encodedLog = this.depositEncoder.encodeInfo(account, request);
		
		Account updatedAccount = this.accountMoneyDepositorService.depositeMoney(account, request);
		producer.send("logs",encodedLog);
		return updatedAccount;
	}

	@Override
	public ResponseEntity<Object> transferMoney(String accountNumber, AccountTransferRequest request) {
		
		// Get account
		Account account = getAccountDetails(accountNumber).getBody();
		
		// Encode to (2341231231 transfer amount:100,transferred_account:4513423423) format
		String encodedLog = this.transferEncoder.encodeInfo(account, request); 
		String result = this.accountMoneyTransferringService.transferMoney(account, request).getStatusCode().toString();
		if(result.equals("200 OK")) { producer.send("logs",encodedLog);}
		return this.accountMoneyTransferringService.transferMoney(account, request);
	}

	@Override
	public ResponseEntity<Object> getAccountLogs(String accountNumber) {
		//return this.accountLogService.getAccountLogs(accountNumber);
		return new ResponseEntity<Object>(this.accountLogService.getAccountLogs(accountNumber),null,HttpStatus.OK);
	}
}
