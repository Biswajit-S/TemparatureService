/**
 * 
 */
package com.example.temperature.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.example.temperature.bean.StatusMessage;
import com.example.temperature.constants.StatusCodes;

/**
 *
 * Generic Exception. If no other Exception Classes are of a Match, this will be returned.
 * Returns a HTTP Status 500.
 * 
 * @author Biswajit_Sahoo
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	
	final static public Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class);
	
	@Override
	public Response toResponse(Exception exception) {
		
		LOGGER.error(exception.getMessage(), exception);

		StatusMessage errorMessage = new StatusMessage();
		errorMessage.setError(StatusCodes.CODE_SERVER_ERROR, StatusCodes.MSSG_SERVER_ERROR);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
