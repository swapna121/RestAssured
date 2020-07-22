package com.restPractice;
 
import java.util.HashMap;
import java.util.Map;
 
import org.testng.annotations.Test;

import helper.Resuable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
 
public class herokuGet {
	
	//@Test
	public static void main(String args[])
	{
		Map<String,String> authPayload = new HashMap<String,String>();
		authPayload.put("username", "admin");
		authPayload.put("password", "password123");
		
		
		//GIVEN
		String response=RestAssured
		   .given()
			  .baseUri("https://restful-booker.herokuapp.com/auth")
			  .contentType(ContentType.JSON)
			  .body(authPayload)
			  .log()
			  .all()
		// WHEN
		   .when()
			   .post()
		// THEN
		   .then()
			   .assertThat()
			   .statusCode(200)
			   .log()
			   .all().extract().response().asString();;
		
		
		
		JsonPath js1=Resuable.rawToJson(response);
	    String token=js1.getString("token");
	    System.out.println("Token is _:"+token);
	    
	
	    	
	    		Map<String,Object> jsonBodyUsingMap = new HashMap<String,Object>();
	    		jsonBodyUsingMap.put("firstname", "Jim");
	    		jsonBodyUsingMap.put("lastname", "Brown");
	    		jsonBodyUsingMap.put("totalprice", 111);
	    		jsonBodyUsingMap.put("depositpaid", true);
	    		
	    		Map<String,String> bookingDatesMap = new HashMap<>();
	    		bookingDatesMap.put("checkin", "2021-07-01");
	    		bookingDatesMap.put("checkout", "2021-07-01");
	    		
	    		jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
	    		jsonBodyUsingMap.put("additionalneeds", "Breakfast");
	    		
	    		
	    		//GIVEN
	    		RestAssured
	    		   .given()
	    			  .baseUri("https://restful-booker.herokuapp.com/booking")
	    			  .contentType(ContentType.JSON)
	    			  .body(jsonBodyUsingMap)
	    			  .log()
	    			  .all()
	    		// WHEN
	    		   .when()
	    			   .post()
	    		// THEN
	    		   .then()
	    			   .assertThat()
	    			   .statusCode(200)
	    			   .log()
	    			   .all();
	    	}
	     
	    
	}
 
