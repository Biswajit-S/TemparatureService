/**
 * Test APIs exposed to Read and Submit Temperate data.
 * Host and port according to the server, for unit testing.
 */
package com.example.temperature;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import io.restassured.RestAssured;

/**
 * @author Biswajit_Sahoo
 * 
 * 
 */
public class TestTemparatureService {
	
	String REST_API = "/temperature/rest/service";

	
	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8580;
    }
	
	
	@Test
	public void testGetForOneHour() {
		get(REST_API + "?interval=hour")
        .then()
        .statusCode(200)
        .body("statusCode", equalTo("200")); // Check if list
		
	}
	
	@Test
	public void testGetForOneDay() {
		get(REST_API + "?interval=day")
        .then()
        .statusCode(200)
        .body("statusCode", equalTo("200")); // Check if list
		
	}
	
	@Test
	public void testGetWithInvalidInterval() {
		get(REST_API + "?interval=month")
        .then()
        .statusCode(400)
        .body("statusCode", equalTo("4102"));
		
	}
	
	@Test
	public void testGetWithMissingInterval() {
		get(REST_API + "?interval=month")
        .then()
        .statusCode(400)
        .body("statusCode", equalTo("4103"));
		
	}
	
	@Test
	public void testSubmitWithValidList() {
		List<JSONObject> payload = new ArrayList<>();
		JSONObject jsonParams = null;
		
		jsonParams = new JSONObject();
		jsonParams.put("temperatureValue", 20);
		jsonParams.put("time", "2021-06-06 16:40:00");
		payload.add(jsonParams);
		
		jsonParams = new JSONObject();
		jsonParams.put("temperatureValue", 22);
		jsonParams.put("time", "2021-06-06 16:00:00");
		payload.add(jsonParams);
		
		with().body(payload)
	      .when()
	      .request("POST", REST_API)
	      .then()
	      .statusCode(200)
	      .body("description", equalTo("Data updated successfully"));
		
	}
	
	@Test
	public void testSubmitWithSingleItemAsList() {
		List<JSONObject> payload = new ArrayList<>();
		JSONObject jsonParams = null;
		
		jsonParams = new JSONObject();
		jsonParams.put("temperatureValue", 20);
		jsonParams.put("time", "2021-06-06 16:40:00");
		payload.add(jsonParams);

		
		with().body(payload)
	      .when()
	      .request("POST", REST_API)
	      .then()
	      .statusCode(200)
	      .body("description", equalTo("Data updated successfully"));
		
	}
	
	@Test
	public void testSubmotWithInvalidDataFormat() {
		List<JSONObject> payload = new ArrayList<>();
		JSONObject jsonParams = null;
		
		jsonParams = new JSONObject();
		jsonParams.put("temperatureValue", 20);
		jsonParams.put("time", "2021-JUN-06 16:40:00");
		payload.add(jsonParams);

		with().body(payload)
	      .when()
	      .request("POST", REST_API)
	      .then()
	      .statusCode(400)
	      .body("statusCode", equalTo("4101"));
		
	}
	

}
