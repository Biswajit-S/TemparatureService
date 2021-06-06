/**
 * 
 */
package com.example.temperature.exceptions;

/**
 * Intended to throw custom Input Error messages.
 * 
 * @author Biswajit_Sahoo
 */
public class InputErrException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2355218415824432033L;
	
	private String errorCode;
	
	public InputErrException(String errorCode, String message) {
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
