/**
 * 
 */
package com.example.temperature.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.example.temperature.constants.Constants;
import com.example.temperature.constants.StatusCodes;
import com.example.temperature.exceptions.InputErrException;
import com.example.temperature.requests.RequestHandler;

/**
 * Utility class provides generic services and implementations.
 * 
 * @author Biswajit_Sahoo
 */
public class Util {
	
	final static public Logger LOGGER = Logger.getLogger(RequestHandler.class);
	
	/**
	 * Return the current time in Milli Seconds 
	 * @return long
	 * 
	 */
	public long getCurrentTimeinMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Convert a time provided in String format to Long.
	 * @return long
	 * @param timeInStr String
	 */
	public long getTimeinMillisFromStr(String timeInStr) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
		Date date = null;
		
		try {
			date = sdf.parse(timeInStr);
		}
		catch (ParseException e) {
			throw new InputErrException(StatusCodes.CODE_TIME_FORMAT_ERROR, StatusCodes.MSSG_TIME_FORMAT_ERROR);
		}
		
		return date.getTime();
	}
	
	/**
	 * Convert a time provided in Long to String.
	 * 
	 * @return String
	 * @param timeInLong String
	 */
	public String getTimeinString(long timeInLong) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone(Constants.TEMPERATURE_DB_TIME_ZOME));
        
        return sdf.format(new Date(timeInLong)).toString();
		
	}
	
	/**
	 * Convert number of hours to Milliseconds.
	 * 
	 * @return long
	 * @param hours Int
	 */
	public long convertHoursToMillis(int hours) {
		
		return hours * 3600000;
	}

}
