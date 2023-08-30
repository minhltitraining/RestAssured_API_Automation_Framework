
package com.spree.test;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.spree.testdata.*;
import com.spree.util.ResponseUtil;
import com.spree.util.RestAssuredUtil;
import com.spree.util.RestRequestUtil;
import com.spree.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Address extends BaseTest {
	String accessToken;
	String addressId;
	ArrayList<String> addressIds;
	
	@BeforeClass
	public void pre_condition() {
		accessToken = TestUtil.oAuth_Token(); 
        RestAssuredUtil.setBasePath("/api/v2/storefront");
        addressIds = new ArrayList<>();
        getAllAddressIds();
        deleteAllAddresses();
	}


	public Response getAllAddressIds() {
		Response res = RestRequestUtil.responseWithTokenAndBody("get", accessToken, null, "/account/addresses");
		ResponseUtil.checkStatusIs200(res);
		JsonPath jsonPathEva = res.getBody().jsonPath();
		ArrayList<Map<String, String>> data = jsonPathEva.get("data");
		for (Map<String, String> address: data) {
			addressIds.add(address.get("id"));
		}
		return res;
	}
	
	public void deleteAllAddresses() {
		for (String id: addressIds) {
			Response res = RestRequestUtil.responseWithTokenAndBody("delete", accessToken, null, ("/account/addresses/" + id) );
			ResponseUtil.checkStatusIs204(res);
		}
	}
	
	
//	@Test(priority = 1)
//	public void T01_getAllAddress() {
//		getAllAddressIds();
//	}

//	@Test(dataProvider = "addressWithLabel", dataProviderClass = Spreecom_TestData.class, priority = 2)
//	public void T02_createAnAddress(String fName, String lName, String address1, String city, String zipcode,
//			String phone, String state, String country, String label) {
//		JSONObject body = TestUtil.generateJSONAddress(fName, lName, address1, city, 
//				zipcode, phone, state, country);
//		Response res = RestRequestUtil.responseWithTokenAndBody("post", accessToken, body, "/account/addresses");
//		ResponseUtil.checkStatusIs200(res);
//		addressId = ResponseUtil.getValue(res, "data.id");
//	}
	
	
//	@Test(priority = 3)
//	public void T03_deleteAnAddress() {
//		Response res = RestRequestUtil.responseWithTokenAndBody("delete", accessToken, null, ("/account/addresses/" + addressId) );
//		ResponseUtil.checkStatusIs204(res);
//	}
	
//	@Test(priority = 4)
//	public void T04_deleteAllAddress() {
//		deleteAllAddresses();
//	}
	
//	@Test(dataProvider = "addressWithLabel", dataProviderClass = Spreecom_TestData.class, priority = 5)
//	public void updateAddress(String fName, String lName, String address1, String city, String zipcode,
//			String phone, String state, String country, String label) {
//		
//		fName = "NewMinh";
//		JSONObject body = TestUtil.generateJSONAddress(fName, lName, address1, city, 
//				zipcode, phone, state, country);
//		Response res = RestRequestUtil.responseWithTokenAndBody("patch", accessToken, body, ("/account/addresses/" + addressId));
//		ResponseUtil.checkStatusIs200(res);
//		
//	}
	
//	@Test(dataProvider = "Addresses", dataProviderClass = Spreecom_TestData.class, priority = 6)
//	public void addMultipleAddress(String fName, String lName, String address1, String city, String phone,
//			String zipcode, String state, String country) {
//		JSONObject body = TestUtil.generateJSONAddress(fName, lName, address1, city, 
//				zipcode, phone, state, country);
//		Response res = RestRequestUtil.responseWithTokenAndBody("post", accessToken, body, "/account/addresses");
//		ResponseUtil.checkStatusIs200(res);
//		res = getAllAddressIds();
//	}
	
	@Test(dataProvider = "addressWithLabel", dataProviderClass = Spreecom_TestData.class,priority = 10)
	public void addAddressSameLabel(String fName, String lName, String address1, String city, String zipcode,
			String phone, String state, String country, String label) {
		JSONObject body = TestUtil.generateJSONAddress(fName, lName, address1, city, 
				zipcode, phone, state, country, label);
		Response res = RestRequestUtil.responseWithTokenAndBody("post", accessToken, body, "/account/addresses");
		ResponseUtil.checkStatusIs200(res);
		
		body = TestUtil.generateJSONAddress("NewFname", "NewLname", address1, city, 
				zipcode, phone, state, country, label);
		res = RestRequestUtil.responseWithTokenAndBody("post", accessToken, body, "/account/addresses");
		ResponseUtil.checkStatusIs422(res);
		ResponseUtil.checkValue(res, "error", "Address name has already been taken");
	}
	

	

}