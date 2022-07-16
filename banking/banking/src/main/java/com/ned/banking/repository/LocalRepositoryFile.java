package com.ned.banking.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.service.AccountInfoCreator;

@Component
@Qualifier("LocalRepositoryFile")
public class LocalRepositoryFile implements ILocalAccountRepository{
	
	@Override
	public Object getDataById(String id) {
		return com.ned.banking.fileoperations.FileReader.getAccountById(id);
	}

	@Override
	public Object addData(Object object) {
		
		AccountCreateRequest request = (AccountCreateRequest)object;
		Account account = AccountInfoCreator.getCreator().createAccountInfo(request);
		com.ned.banking.fileoperations.FileWriter.writeToFile(account);
		return account;
	}

	@Override
	public Object updateData(Object object) throws FileNotFoundException {
		
		Account account = (Account)object;
		String accountNumber = account.getAccountNumber();
		com.ned.banking.fileoperations.FileWriter.resetFile(accountNumber);
		com.ned.banking.fileoperations.FileWriter.writeToFile(account);
		
		return account;
	}
}
