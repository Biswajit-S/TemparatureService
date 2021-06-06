/**
 * 
 */
package com.example.temperature.requests;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.example.temperature.bean.StatusMessage;
import com.example.temperature.bean.TemperatureBean;
import com.example.temperature.business.TemperatureService;
import com.example.temperature.constants.Constants;
import com.example.temperature.constants.StatusCodes;
import com.example.temperature.exceptions.InputErrException;

/**
 * Entry point for all the requests. 
 * Whenever required, checks the input data before forwarding the request to the business layer.
 * Similarly for the response, does the necessary checks to transformation, fore sending a response to the client.
 * 
 * @author Biswajit_Sahoo
 * 
 */
@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class RequestHandler {
	
	final static public Logger LOGGER = Logger.getLogger(RequestHandler.class);
	
	/**
	 * Create a Business Service Object to call all the services in the Business Layer.
	 */
	TemperatureService bso = new TemperatureService();
	
	
	/**
	 * Call the Business Layer service to save temperature data, AS IS.
	 * Accepts a list of data in JSON format.
	 * Return a Status Message with status code and description, which is set by the Business Service.
	 * 
	 * @return JSON
	 */
	@POST
	public StatusMessage saveTemperatureData(List<TemperatureBean> tempData) {
		
		// Do not log the entire pay load.
		LOGGER.debug("Got a request to save data with payload size " + tempData.size() + ". Forwarding the request to Business Service >> saveTemperatureData()" );
		
		return bso.saveTemperatureData(tempData);
	}
	
	/**
	 * Call the Business Layer service to retrieve temperature data.
	 * Accepts the time interval (hour or day) as query param. Currently only two values supported.
	 * 	- "hour": for last 1 hour
	 *  - "day": for last 24 hours
	 * 
	 * @param interval day | hour (as Query param)
	 * @return JSON
	 */
	@GET
	public List<TemperatureBean> getTemperatureData(@QueryParam(Constants.INTERVAL) String interval) {
		
		LOGGER.debug("Got a request to fetch temperature data. Input Query Interval as :: " + interval);
		
		if (interval != null) {
			LOGGER.debug("Forwarding the request to Business Service >> getTemperatureData()" );
			return bso.getTemperatureData(interval);
		}
		else {
			throw new InputErrException(StatusCodes.CODE_MISSING_TIME_INTERVAL, StatusCodes.MSSG_MISSING_TIME_INTERVAL);
		}
	}

}
