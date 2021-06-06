/**
 * 
 */
package com.example.temperature.database;

import java.util.List;

import org.hibernate.Session;

import com.example.temperature.bean.StatusMessage;
import com.example.temperature.constants.StatusCodes;
import com.example.temperature.dbModels.TemparatureDataDbModel;
import com.example.temperature.exceptions.ServerErrException;

import org.apache.log4j.Logger;
import org.hibernate.Query;

/** 
 * Intended to perform the Database transactions. 
 * 
 * @author Biswajit_Sahoo
 */

@SuppressWarnings("deprecation")
public class DbServices {
	
	final static public Logger LOGGER = Logger.getLogger(DbServices.class);
	
	// Get a DB connection
	DbServerConnection dbServerConnection = new DbServerConnection();
	Session session = null;
	
	/**
	 * Insert the Data to the Database Table with a batch of 100. This can be changed, based on DB performance.
	 * 
	 * @return StatusMessage
	 *  
	 */
	public StatusMessage insertTemperatureDataToDb(List<TemparatureDataDbModel> tempDataList) {
		TemparatureDataDbModel tempData = null;
		
		
		
		StatusMessage statusMessage = new StatusMessage();
		
		try {
			
			LOGGER.debug("Trying to Get a Session");
			session = dbServerConnection.getSession();
			
			LOGGER.debug("Got a Session and Proceeding.");
			
			session.beginTransaction();
			
			for(int index=0; index < tempDataList.size(); index++) {
				tempData = tempDataList.get(index);
				session.save(tempData);
				
				// If Batch size is 100, Flush a batch of 100 and release memory.
				if( index % 100 == 0 ) { 
				      session.flush();
				      session.clear();
				   }
			}
			
			LOGGER.debug("Transaction was successfull. Attempting Commit.");

			session.getTransaction().commit();
			session.close();
			
			LOGGER.debug("Insert Successful. Set Status code as :: " + StatusCodes.CODE_SUCCESS);
			
			// If everything went well, set SUCCESS CODE.
			statusMessage.setStatus(StatusCodes.CODE_SUCCESS, StatusCodes.MSSG_SUCCESS);
			
		}catch (Exception e) {
			LOGGER.debug("Insert Failed. Set Status code as :: " + StatusCodes.CODE_TRANSACTION_ERROR);
			// If there is a exception Log the error and return transaction error.
			statusMessage.setStatus(StatusCodes.CODE_TRANSACTION_ERROR, StatusCodes.MSSG_TRANSACTION_ERROR);
		}
		
		return statusMessage;
	}
	
	/**
	 * Fetch Temperature Data from the Database Table.
	 * Accepts the start time after which the data needs to be retrieved.
	 *  
	 *  @return List<TemparatureDataDbModel>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TemparatureDataDbModel> getTemperatureDataFromDb(long startTime) {
		
		List<TemparatureDataDbModel> tempDbModelList = null;
		
		try {
			LOGGER.debug("Trying to Get a Session");
			session = dbServerConnection.getSession();
			LOGGER.debug("Got a Session and Proceeding.");
			
			String hql = "FROM temp_data where temperature >= :startTime";
			
			Query query = session.createQuery(hql);
			query.setParameter("startTime", startTime);
			
			LOGGER.debug("Got all the records after :: " + startTime);
			tempDbModelList = query.list();
		}
		catch (Exception e) {
			LOGGER.debug("Read Failed. Set Status code as :: " + StatusCodes.CODE_TEMP_DATA_READ_ERROR);
			throw new ServerErrException(StatusCodes.CODE_TEMP_DATA_READ_ERROR, StatusCodes.MSSG_TEMP_DATA_READ_ERROR);
		}
		
		return tempDbModelList;
	}

}
