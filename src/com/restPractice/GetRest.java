package com.restPractice;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class GetRest {
	//@Test
	 public static void main(String args[])
	 {
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = "https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification httpRequest = RestAssured.given();
	 
	 // Make a GET request call directly by using RequestSpecification.get() method.
	 // Make sure you specify the resource name.
	 Response response = httpRequest.request(Method.GET, "/accuracy");
	 
	 // Response.asString method will directly return the content of the body
	 // as String.
	 System.out.println("Response Body is =>  " + response.asString());
	 }
}
