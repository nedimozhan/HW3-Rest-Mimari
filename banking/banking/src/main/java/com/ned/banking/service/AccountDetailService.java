package com.ned.banking.service;

import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountDetailService")
public class AccountDetailService implements IAccountDetail{
	
	private ILocalAccountRepository accountRepository;
	
	@Autowired
	public AccountDetailService(@Qualifier("LocalRepositoryFile") ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public ResponseEntity<Account> getAccountDetail(String accountNumber) {
		Account account = (Account)this.accountRepository.getDataById(accountNumber);
		ResponseEntity<Account> responseEntity = new ResponseEntity<Account>(account,null,HttpStatus.OK);
		responseEntity.ok().lastModified(Long.parseLong(account.getAccountDateOfUpdate()));
		return responseEntity;
	}
}
