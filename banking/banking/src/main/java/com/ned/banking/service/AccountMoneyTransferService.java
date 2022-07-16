package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountTransferRequest;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.repository.IRemoteCurrencyRepository;
import com.ned.banking.responses.AccountTransferFailResponse;
import com.ned.banking.responses.AccountTransferSuccessResponse;

@Component
@Qualifier("AccountMoneyTransferService")
@Scope("prototype") 
public class AccountMoneyTransferService implements IAccountMoneyTransferring{
	
	private ILocalAccountRepository accountRepository;
	private IRemoteCurrencyRepository currencyRepository;
	
	@Autowired
	public AccountMoneyTransferService(@Qualifier("LocalRepositoryFile") ILocalAccountRepository accountRepository,
									   @Qualifier("RemoteCurrencyRepository") IRemoteCurrencyRepository currencyRepository) {
		this.accountRepository = accountRepository;
		this.currencyRepository = currencyRepository;
	}
	
	@Override
	public ResponseEntity<Object> transferMoney(Account account, AccountTransferRequest request) {
		
		String accountNumber = request.getTransferredAccountNumber();
		Account toTransferAccount = (Account)this.accountRepository.getDataById(accountNumber);
	
		float fromAccountBalance = Float.parseFloat(account.getAccountBalance());
		float toAccountBalance = Float.parseFloat(toTransferAccount.getAccountBalance());
		float amount = Float.parseFloat(request.getAmount())/2;
		
		if(fromAccountBalance >= amount) {
			//fromAccountBalance -= (amount/2);
			//toAccountBalance = toAccountBalance + (amount/2);
			
			if(account.getType().equals("TL") && toTransferAccount.getType().equals("Dolar")) {
				float currency = this.currencyRepository.currencyUSD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * 1 / currency);
			}
			else if(account.getType().equals("Dolar") && toTransferAccount.getType().equals("TL")) {
				float currency = this.currencyRepository.currencyUSD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) *currency);
			}
			else if(account.getType().equals("TL") && toTransferAccount.getType().equals("Altın")) {
				float currency = 1/this.currencyRepository.currencyGOLD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) *currency);
				
			}
			else if(account.getType().equals("Altın") && toTransferAccount.getType().equals("TL")) {
				float currency = this.currencyRepository.currencyGOLD_TL();
				System.out.println("ALTIN : " + String.valueOf(currency));
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) *currency);
			}
			else if(account.getType().equals("Altın") && toTransferAccount.getType().equals("Dolar")) {
				float currAltınTL = this.currencyRepository.currencyGOLD_TL();
				float currencyUSDTL = this.currencyRepository.currencyUSD_TL();
				
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * currAltınTL / currencyUSDTL);
				
			}
			else if(account.getType().equals("Dolar") && toTransferAccount.getType().equals("Altın")) {
				float currAltınTL = this.currencyRepository.currencyGOLD_TL();
				float currencyUSDTL = this.currencyRepository.currencyUSD_TL();
				
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) / currAltınTL * currencyUSDTL);
			}
			
			
			
			account.setAccountBalance(String.valueOf(fromAccountBalance));
			toTransferAccount.setAccountBalance(String.valueOf(toAccountBalance));
			
			account.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp().toString());
			toTransferAccount.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp().toString());
			
			try {
				this.accountRepository.updateData(toTransferAccount);
				this.accountRepository.updateData(account);
				
				AccountTransferSuccessResponse successResponse = new AccountTransferSuccessResponse();
				return new ResponseEntity<Object>(successResponse,null,HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<Object>(null,null,HttpStatus.SERVICE_UNAVAILABLE);
			}
		}
		else {
			AccountTransferFailResponse failResponse = new AccountTransferFailResponse();
			return new ResponseEntity<Object>(failResponse,null,HttpStatus.BAD_REQUEST);
		}
	}
}
