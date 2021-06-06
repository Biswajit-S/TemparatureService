/**
 * 
 */
package com.example.temperature.exceptions;

/**
 * Intended to throw custom Server Error messages.
 * 
 * @author Biswajit_Sahoo
 */
public class ServerErrException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5910829041073048872L;
	
	private String errorCode;
	
	public ServerErrException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
