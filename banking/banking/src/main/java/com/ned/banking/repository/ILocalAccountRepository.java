package com.ned.banking.repository;

import java.io.FileNotFoundException;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;

public interface ILocalAccountRepository {
	
	public Object getDataById(String id);
	public Object addData(Object object);
	public Object updateData(Object object) throws FileNotFoundException;
	
}
