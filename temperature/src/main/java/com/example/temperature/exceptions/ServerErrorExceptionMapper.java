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

/**
 * Mapper Class for ServerErrorException Class. Returns a HTTP Status 500.
 * Intended to throw custom Server Error messages.
 * 
 * @author Biswajit_Sahoo
 */
@Provider
public class ServerErrorExceptionMapper implements ExceptionMapper<ServerErrException> {
	
	final static public Logger LOGGER = Logger.getLogger(ServerErrorExceptionMapper.class);
	
	@Override
	public Response toResponse(ServerErrException exception) {
		
		LOGGER.error(exception.getMessage(), exception);
		
		StatusMessage errorMessage = new StatusMessage();
		errorMessage.setError(exception.getErrorCode(), exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
