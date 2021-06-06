/**
 * Model class to define and send status and error codes.
 */
package com.example.temperature.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Biswajit_Sahoo
 * 
 */
@XmlRootElement
public class StatusMessage {
	
	private String statusCode;
	private String errorCode;
	private String description;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String statusCode, String description) {
		this.statusCode = statusCode;
		this.description = description;
		
	}
	
	public void setError(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

}
