package com.ned.banking.service;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.responses.AccountCreateFailResponse;
import com.ned.banking.responses.AccountCreateSuccessResponse;

@Component
@Qualifier("AccountCreatorService")
public class AccountCreatorService implements IAccountCreator{
	
	private ILocalAccountRepository accountRepository;
	
	@Autowired
	public AccountCreatorService(@Qualifier("LocalRepositoryFile") ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public ResponseEntity<Object> createAccount(AccountCreateRequest request) {
		
		String type = request.getType();
		
		if(type.equals("TL") || type.equals("Dolar") || type.equals("Altın")) {
			Account account = (Account) accountRepository.addData(request);
			AccountCreateSuccessResponse successResponse = new AccountCreateSuccessResponse();
			successResponse.setAccountNumber(account.getAccountNumber());
			return new ResponseEntity<>(successResponse,null,HttpStatus.OK);
		}
		else {
			AccountCreateFailResponse failResponse = new AccountCreateFailResponse();
			failResponse.setMessage(failResponse.getMessage() + request.getType());
			return new ResponseEntity<>(failResponse,null,HttpStatus.BAD_REQUEST);
		}
	}
}
