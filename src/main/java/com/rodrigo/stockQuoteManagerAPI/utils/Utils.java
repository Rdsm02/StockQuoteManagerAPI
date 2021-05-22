package com.rodrigo.stockQuoteManagerAPI.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Utils() {
		
	}
	
	public static Date parseStringToDate(String date) throws ParseException {
		return sdf.parse(date);
	}
	
	public static String parseDateToString(Date date) {
		return sdf.format(date);
	}
	
}
