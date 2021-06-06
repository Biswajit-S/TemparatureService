package com.example.temperature.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.temperature.bean.StatusMessage;
import com.example.temperature.bean.TemperatureBean;
import com.example.temperature.constants.Constants;
import com.example.temperature.constants.StatusCodes;
import com.example.temperature.database.DbServices;
import com.example.temperature.dbModels.TemparatureDataDbModel;
import com.example.temperature.exceptions.InputErrException;
import com.example.temperature.exceptions.ServerErrException;
import com.example.temperature.util.Util;

/**
 * Intermediate between the Request Handler and the Database Layer. 
 * Intended to handle all the business logic, transformation before making a call to the downstream systems.
 * 
 * @author Biswajit_Sahoo
 * 
 */
public class TemperatureService {
	
	final static public Logger LOGGER = Logger.getLogger(TemperatureService.class);
	
	// Get a DB Service object.
	DbServices dbServices = new DbServices();
	Util util = new Util();
	
	/**
	 * Convert the input data to a TemparatureDataDbModel object List.
	 * TemparatureDataDbModel Class help to decouple the input payload and the DB Table. 
	 * Helps to restructure the Table format at a later point of time, if required.
	 * 
	 * Database stores the time as EPOCH format. Convert the input time String to EPOCH, with the necessary Util Class method.
	 * 
	 * If the Database returns any transaction, send a meaningful response code and message to the clinet.
	 */
	public StatusMessage saveTemperatureData(List<TemperatureBean> tempDataList) {
		List<TemparatureDataDbModel> tempDbModelList = new ArrayList<>();
		TemparatureDataDbModel tempDbModelObj = null;
		
		for(TemperatureBean temp:tempDataList) { 
			
			tempDbModelObj = new TemparatureDataDbModel(temp.getTemperatureValue(), util.getTimeinMillisFromStr(temp.getTime()));
			tempDbModelList.add(tempDbModelObj);
		}
		
		LOGGER.debug("Trying to Push the Data to the DB Services");
		StatusMessage statusMessage = dbServices.insertTemperatureDataToDb(tempDbModelList);
		
		if(statusMessage.getStatusCode().equals(StatusCodes.CODE_SUCCESS)) {
			LOGGER.debug("DB Transactions Successful");
			statusMessage.setDescription(StatusCodes.MSSG_TEMP_DATA_INSERT_SUCCESS);
		}
		else if(statusMessage.getStatusCode().equals(StatusCodes.CODE_TRANSACTION_ERROR)) {
			LOGGER.debug("DB Transactions Failed. Got Error Code :: " + StatusCodes.CODE_TRANSACTION_ERROR);
			LOGGER.debug("Setting Error Code as :: " + StatusCodes.CODE_TEMP_DATA_INSERT_ERROR);
			throw new ServerErrException(StatusCodes.CODE_TEMP_DATA_INSERT_ERROR, StatusCodes.MSSG_TEMP_DATA_INSERT_ERROR);
		}
		else {
			LOGGER.debug("DB Transactions Failed with unknown error.");
			LOGGER.debug("Setting Error Code as :: " + StatusCodes.CODE_SERVER_ERROR);
			throw new ServerErrException(StatusCodes.CODE_SERVER_ERROR, StatusCodes.MSSG_SERVER_ERROR);
		}
		
		return statusMessage;
	}
	
	/**
	 * Convert the TemparatureDataDbModel object Lists to TemperatureBean.
	 * TemparatureDataDbModel Class help to decouple the input payload and the DB Table. 
	 * Helps to restructure the Table format at a later point of time, if required.
	 * 
	 * According to the time interval requested by the client, generate the start time in EPOC format and pass it to the DB service.
	 * 
	 * Convert the EPOCH time received from DB service to human readable time String, with the necessary Util Class method.
	 * 
	 */
	public List<TemperatureBean> getTemperatureData(String interval) {
		
		List<TemperatureBean> tempDataList = new ArrayList<>();
		TemperatureBean tempBeanObj = null;
		long startTime = 0;
		
		
		if(interval.toUpperCase() == Constants.INTERVAL_HOUR) {
			startTime = util.getCurrentTimeinMillis() - util.convertHoursToMillis(1);
			LOGGER.debug("Setting start time for last 1 hour. Start Time :: " + startTime);
			
		}
		else if (interval.toUpperCase() == Constants.INTERVAL_DAY) {
			startTime = util.getCurrentTimeinMillis() - util.convertHoursToMillis(24);
			LOGGER.debug("Setting start time for last 1 Day. Start Time :: " + startTime);
		}
		else {
			throw new InputErrException(StatusCodes.CODE_INVALID_TIME_INTERVAL, StatusCodes.MSSG_INVALID_TIME_INTERVAL);
		}
		
		LOGGER.debug("Trying to fetch Data from the DB Services");
		List<TemparatureDataDbModel> tempDbModelList = dbServices.getTemperatureDataFromDb(startTime);
		
		for(TemparatureDataDbModel temp:tempDbModelList) { 
			
			tempBeanObj = new TemperatureBean();
			tempBeanObj.setTemperatureValue(temp.getTemperature());
			tempBeanObj.setTime(util.getTimeinString(temp.getTemperature()));
			tempDataList.add(tempBeanObj);
		}
		
		LOGGER.debug("Retrieve Successful. Returning the Data.");
		return tempDataList;
	}

}
