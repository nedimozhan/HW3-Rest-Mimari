package com.ned.banking.service;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;

public class AccountInfoCreator {
	
	private static AccountInfoCreator creator = null;
	
	private AccountInfoCreator() {
		
	}
	
	public static AccountInfoCreator getCreator() {
		
		if(creator == null) {
			creator = new AccountInfoCreator();
		}
		
		return creator;
	}
	
	private String generateAccountNumber() {
		
		//Generate random numbers(5 digits)
		int number1 = ThreadLocalRandom.current().nextInt(10000, 99999);
		int number2 = ThreadLocalRandom.current().nextInt(10000, 99999);
		
		// Concate two numbers(5 digits). Result number is 10 digits.
		String generatedNumber = String.valueOf(number1) + String.valueOf(number2);
		
		return generatedNumber;
	}

	public Account createAccountInfo(AccountCreateRequest request) {
		
		String timeStamp = String.valueOf(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp());
		String balance = "0";
		String accountNumber = generateAccountNumber();
		
		Account account = new Account(request, accountNumber, balance, timeStamp);
		
		return account;
	}
}
