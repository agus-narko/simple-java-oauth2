package com.agus.java.resource.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static String getDatetimeMilisecondNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public static String getDatetimeNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public static String getTimeNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("HHmmss");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public static String getDateNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public static String getYearNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("yyyy");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public static String getMonthDateNow(){
		//get datenow
    	DateFormat dateFormat = new SimpleDateFormat("MMdd");
    	Date now = Calendar.getInstance().getTime();
    	String datetime = dateFormat.format(now);
    	
		return datetime;
	}
	
	public Long getDays(String startDate, String endDate){
		Long diff = new Long(0);
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyy");
		try {
			Date sDate = formatDate.parse(startDate);
			Date eDate = formatDate.parse(endDate);
			diff = TimeUnit.DAYS.convert(eDate.getTime() - sDate.getTime(), TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}
	
	public long getMonthFloorBy30Days(String startDate, String endDate){
		Long diffMonth = new Long(0);
		Long diff = new Long(0);
		getDays(startDate, endDate);
		diffMonth = (Long) (diff/30);
		
		return diffMonth;
	}
	
	public long getMonthAndDaysBy30Days(String startDate, String endDate){
		Long month = new Long(0);
		Long diff = new Long(0);
		Long diffMonth = new Long(0);
		Long diffDays = new Long(0);
		
		getMonthFloorBy30Days(startDate, endDate);
		month = diffMonth * 30;
		diffDays = diff - month;
		
		return diffDays;
	}



}
