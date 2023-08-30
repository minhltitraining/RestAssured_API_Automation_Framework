package com.spree.util;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {
	
    public static void setBaseURI() {
    	RestAssured.baseURI = "https://demo.spreecommerce.org";
    }
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }
    
    public static RequestSpecification getReqSpec() {
    	return given();
    }
    
    public static RequestSpecification auth2(String accessToken) {
    	return given().auth().oauth2(accessToken);
    }
   
    public static RequestSpecification setContentTypeJSONAndBody(JSONObject body) {
    	return given().header("Content-Type","application/JSON").body(body.toJSONString());
    }
    
    public static Response post(RequestSpecification request, String path) {
    	return request.post(path);
    }
    
    public static Response get(RequestSpecification request, String path) {
    	return request.get(path);
    }
    
    public static Response put(RequestSpecification request, String path) {
    	return request.put(path);
    }
    
    public static Response patch(RequestSpecification request, String path) {
    	return request.patch(path);
    }
    
    public static Response delete(RequestSpecification request, String path) {
    	return request.delete(path);
    }

}



