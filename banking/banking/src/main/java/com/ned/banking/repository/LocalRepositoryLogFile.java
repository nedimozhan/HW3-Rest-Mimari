package com.ned.banking.repository;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ned.banking.logoperation.LogSeperator;

@Component
@Qualifier("LocalRepositoryLogFile")
public class LocalRepositoryLogFile implements ILocalAccountRepository{
	
	LogSeperator logSeperator;
	
	
	
	public LogSeperator getLogSeperator() {
		return logSeperator;
	}
	
	@Autowired
	public void setLogSeperator(LogSeperator logSeperator) {
		this.logSeperator = logSeperator;
	}

	@Override
	public Object getDataById(String id) {
		
		return logSeperator.getLogList(id);
	}

	@Override
	public ResponseEntity<Object> addData(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateData(Object object) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
