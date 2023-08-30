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

public class RestRequestUtil {
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

	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	public static void setContentTypeJson() {
		given().header("Content-Type", "application/JSON");
	}

	public static void setBody(JSONObject body) {
		given().body(body.toJSONString());
	}

	private static RequestSpecification reqWithToken(RequestSpecification req, String token) {
		return req.auth().oauth2(token);
	}

	private static RequestSpecification reqWithJsonBody(RequestSpecification req, JSONObject body) {
		return req.contentType(ContentType.JSON).body(body);
	}

	public static Response getResponse(String path) {
		return given().get(path);
	}

	public static Response postResponse(String path) {

		return given().post(path);
	}

	public static Response patchResponse(String path) {

		return given().patch(path);
	}

	public static Response deleteResponse(String path) {

		return given().delete(path);
	}

	public static Response responseWithTokenAndBody(String method, String token, JSONObject body, String path) {
		RequestSpecification tokenbody;
		if (token == null) {
			tokenbody = reqWithJsonBody(given(), body);
		} else {
			if (body == null) {
				tokenbody = reqWithToken(given(), token);
			} else {
				tokenbody = reqWithJsonBody(reqWithToken(given(), token), body);
			}

		}

		switch (method) {
		case "get":
			return tokenbody.get(path).then().extract().response();
		case "post":
			return tokenbody.post(path).then().extract().response();
		case "put":
			return tokenbody.put(path).then().extract().response();
		case "delete":
			return tokenbody.delete(path).then().extract().response();
		case "patch":
			return tokenbody.patch(path).then().extract().response();
		}
		System.out.println("no good method");
		return null;
	}

}
