package com.ned.banking.service;

import java.util.List;

import com.ned.banking.logoperation.Log;

public interface IAccountLog {
	public List<Log> getAccountLogs(String accountNumber);
}
