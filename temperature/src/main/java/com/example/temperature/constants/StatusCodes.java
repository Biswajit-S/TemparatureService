/**
 * 
 */
package com.example.temperature.constants;

/**
 * Holds the custom status codes and messages, returned to the client, as well as different application layer.
 * 
 * @author Biswajit_Sahoo
 * 
 */
public class StatusCodes {
	
	public static final String CODE_SUCCESS = "200";
	public static final String MSSG_SUCCESS = "Success";
	
	public static final String CODE_SERVER_ERROR = "500";
	public static final String MSSG_SERVER_ERROR = "Error Processing the request";
	
	public static final String CODE_TRANSACTION_ERROR = "591";
	public static final String MSSG_TRANSACTION_ERROR = "Error during the internal transaction";
	
	
	public static final String CODE_TIME_FORMAT_ERROR = "4101";
	public static final String MSSG_TIME_FORMAT_ERROR = "Invalid Time Format";
	
	public static final String CODE_INVALID_TIME_INTERVAL = "4102";
	public static final String MSSG_INVALID_TIME_INTERVAL = "Invalid Time Interval";
	
	public static final String CODE_MISSING_TIME_INTERVAL = "4103";
	public static final String MSSG_MISSING_TIME_INTERVAL = "Time Interval is Missing in the Request";
	
	
	public static final String CODE_TEMP_DATA_INSERT_ERROR = "5001";
	public static final String MSSG_TEMP_DATA_INSERT_ERROR = "Updating Temperature Data us Unsuccessful";
	
	public static final String CODE_TEMP_DATA_READ_ERROR = "5002";
	public static final String MSSG_TEMP_DATA_READ_ERROR = "Could not fetch data from Temperature DB";
	
	public static final String CODE_DB_CONN_ERROR = "5003";
	public static final String MSSG_DB_CONN_ERROR = "Database Connection Failed";
	
	public static final String MSSG_TEMP_DATA_INSERT_SUCCESS = "Data updated successfully";

}
