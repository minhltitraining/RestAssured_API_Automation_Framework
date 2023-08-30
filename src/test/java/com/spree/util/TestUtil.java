package com.spree.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.IllegalFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtil {

	public static String oAuth_Token() {
		RestAssuredUtil.setBaseURI();
		RestAssuredUtil.setBasePath("");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("grant_type", "password");
		requestParams.put("username", "minh@spree.com");
		requestParams.put("password", "123456");
	
		RequestSpecification request = RestAssuredUtil.setContentTypeJSONAndBody(requestParams);
		Response response = RestAssuredUtil.post(request, "/spree_oauth/token");
		
		ResponseUtil.checkStatusIs200(response);
		String responseBody = response.getBody().asString();
		System.out.println(response.prettyPrint());
		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		String outh_token = jsonPathEvaluator.get("access_token").toString();
		System.out.println("oAuth Token is =>  " + outh_token);
		return outh_token;
		
	}

	//get userID given accessToken
	public static String getUserId(String accessToken) {
		RestAssuredUtil.setBaseURI();
		RestAssuredUtil.setBasePath("/api/v2/storefront");
//		RestAssuredUtil.auth2(accessToken);
		RequestSpecification req = RestAssuredUtil.auth2(accessToken);
		Response response = RestAssuredUtil.get(req, "/account");
		ResponseUtil.checkStatusIs200(response);
		return ResponseUtil.getValue(response, "data.id");
	}
	
	public static JSONObject generateJSONAddress(String fName, String lName, String address1,
			String city, String zipcode, String phone, String state, String country) {
		JSONObject newAddress = new JSONObject();
		newAddress.put("firstname", fName);
		newAddress.put("lastname", lName);
		newAddress.put("address1", address1);
		newAddress.put("city", city);
		newAddress.put("zipcode", zipcode);
		newAddress.put("phone", phone);
		newAddress.put("state_name", state);
		newAddress.put("country_iso", country);
		JSONObject body = new JSONObject();
		body.put("address", newAddress);
		return body;
	}
	public static JSONObject generateJSONAddress(String fName, String lName, String address1,
			String city, String zipcode, String phone, String state, String country, String label) {
		JSONObject newAddress = new JSONObject();
		newAddress.put("firstname", fName);
		newAddress.put("lastname", lName);
		newAddress.put("address1", address1);
		newAddress.put("city", city);
		newAddress.put("zipcode", zipcode);
		newAddress.put("phone", phone);
		newAddress.put("state_name", state);
		newAddress.put("country_iso", country);
		newAddress.put("label", label);
		JSONObject body = new JSONObject();
		body.put("address", newAddress);
		return body;
	}
	
	public static JSONObject readFile(String filename) throws IOException, ParseException
	{
		
		//Create json object of JSONParser class to parse the JSON data
		  JSONParser jsonparser=new JSONParser();
		  //Create object for FileReader class, which help to load and read JSON file
		  FileReader reader = new FileReader(".\\JSON_File\\"+filename);
		  //Returning/assigning to Java Object
		  Object obj = jsonparser.parse(reader);
		  //Convert Java Object to JSON Object, JSONObject is typecast here
		  JSONObject prodjsonobj = (JSONObject)obj;
		return prodjsonobj;
	}
	
	
	
}
