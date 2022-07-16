package com.ned.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.logoperation.Log;
import com.ned.banking.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountLogService")
public class AccountLogService implements IAccountLog{
	
	ILocalAccountRepository logFileRepository;
	
	@Autowired
	public AccountLogService(@Qualifier("LocalRepositoryLogFile") ILocalAccountRepository logFileRepository) {
		this.logFileRepository = logFileRepository;
	}
	
	@Override
	public List<Log> getAccountLogs(String accountNumber) {
		return (List<Log>)logFileRepository.getDataById(accountNumber);		
	}
}
