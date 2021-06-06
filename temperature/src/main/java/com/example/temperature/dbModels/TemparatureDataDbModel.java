/**
 * 
 */
package com.example.temperature.dbModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class to interact with the Database table "token_data". 
 * ID - Primary Key, Auto Generated
 * temperature - Temperature Value, Received from Client
 * updatedTime - updatedTime, Received from Client
 * 
 * @author Biswajit_Sahoo
 */
@Entity
@Table(name = "token_data")
public class TemparatureDataDbModel {
	
	@Id
	@Column(name="TEMPARATURE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tempId;
	
	@Column(name = "TEMPARATURE")
	private int temperature;
	
	@Column(name = "UPDATED_TIME")
	private long updatedTime;
	
	public TemparatureDataDbModel() {
		// TODO Auto-generated constructor stub
	}
	
	public TemparatureDataDbModel(int temperature, long updatedTime) {
		this.temperature = temperature;
		this.updatedTime = updatedTime;
	}
	
	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

}
