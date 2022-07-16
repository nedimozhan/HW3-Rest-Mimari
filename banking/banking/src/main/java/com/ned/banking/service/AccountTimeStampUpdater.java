package com.ned.banking.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTimeStampUpdater {
	
	private static AccountTimeStampUpdater timeStampUpdater = null;
	private Timestamp timestamp;
	private Date date;
	
	private AccountTimeStampUpdater() {
		date = new Date();
	}
	
	public static AccountTimeStampUpdater getTimeStampUpdater() {
		
		if(timeStampUpdater == null) {
			timeStampUpdater = new AccountTimeStampUpdater();
		}
		return timeStampUpdater;
	}
	
	public String getTimeStamp() {
		//return new Timestamp(date.getTime());
		return String.valueOf(System.currentTimeMillis());
	}
}
