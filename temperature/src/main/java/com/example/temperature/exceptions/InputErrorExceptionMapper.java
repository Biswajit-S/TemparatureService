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
 * Mapper Class for InputErrorException Class. Returns a HTTP Status 400.
 * Intended to throw custom Input Error messages.
 * 
 * @author Biswajit_Sahoo
 *
 */
@Provider
public class InputErrorExceptionMapper implements ExceptionMapper<InputErrException> {
	
	final static public Logger LOGGER = Logger.getLogger(InputErrorExceptionMapper.class);
	
	@Override
	public Response toResponse(InputErrException exception) {
		
		LOGGER.error(exception.getMessage(), exception);
		
		StatusMessage errorMessage = new StatusMessage();
		errorMessage.setError(exception.getErrorCode(), exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
