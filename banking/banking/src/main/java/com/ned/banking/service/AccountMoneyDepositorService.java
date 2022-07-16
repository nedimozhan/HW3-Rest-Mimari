package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountMoneyDepositorService")
public class AccountMoneyDepositorService implements IAccountMoneyDepositor{
	
	private ILocalAccountRepository accountRepository;
	
	@Autowired
	public AccountMoneyDepositorService(@Qualifier("LocalRepositoryFile") ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account depositeMoney(Account account, AccountDepositRequest request) {
		
		int totalAmount = Integer.parseInt(account.getAccountBalance()) +Integer.parseInt(request.getAmount());
		account.setAccountBalance(String.valueOf(totalAmount));
		account.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp().toString());
		
		try {
			return (Account)this.accountRepository.updateData(account);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
