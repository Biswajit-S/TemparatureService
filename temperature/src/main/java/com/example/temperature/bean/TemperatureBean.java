/**
 * 
 */
package com.example.temperature.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Biswajit_Sahoo
 * 
 * Model class to interact with the Client. 
 * Accepts the Temperature Value and the Time.
 */
@XmlRootElement
public class TemperatureBean {
	
	private int temperatureValue;
	private String time;

	public int getTemperatureValue() {
		return temperatureValue;
	}

	public void setTemperatureValue(int temperatureValue) {
		this.temperatureValue = temperatureValue;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}	

}
