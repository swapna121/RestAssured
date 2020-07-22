package com.restPractice;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.testng.Assert;

import helper.Resuable;
import helper.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestPractice {
public static void main(String args[]) {
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
	System.out.println(response);
	JsonPath json=new JsonPath(response);
	String placeid=json.getString("place_id");
	System.out.println(placeid);
	
	//Update Place
	String newAddress="29, nager bazar, cohen 09";
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\""+placeid+"\",\r\n" + 
			"\"address\":\""+newAddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}\r\n" + 
			"").when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
    //Get Place
	
	String getresponse=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.queryParam("place_id",placeid)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	System.out.println(getresponse);
	
	JsonPath js1=Resuable.rawToJson(getresponse);
    String actualAddress=js1.getString("address");
	System.out.println("Actual address: "+actualAddress);
	Assert.assertEquals(newAddress, actualAddress);
	
	//given().log().all().		queryParam("key","qaclick123")
	//queryParam("place_id",placeid)
	
	/*"{\r\n" + 
	"\"place_id\":\""+placeid+"\",r\n" +
	"\"address\":\""+newAddress+"\",r\n" +
	"\"key\":\"qaclick123\"\r\n" +
	"}"*/
	//Assert.assertEquals(actual, expected);
	
	/*RestAssured.baseURI="https://restful-booker.herokuapp.com";
	//RestAssured.given()
		//.baseUri("https://restful-booker.herokuapp.com")
	// When
	
	RestAssured.given().when()
		.get("/booking")
	// Then
	.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		// To verify booking count
		.body("bookingid.sum()", Matchers.hasSize(10))
		// To verify booking id at index 3
		.body("bookingid[3]", Matchers.equalTo(1));			
	*/
	
}
}
