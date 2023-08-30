package com.spree.test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.spree.util.*;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public Response res = null; //Response
    public JsonPath jp  = null; //JsonPath

    //Instantiate a Helper Test Methods (testUtils) Object
    ResponseUtil statusCodeUtil = new ResponseUtil();



    @BeforeClass
    public void setup() throws FileNotFoundException, IOException, ParseException {
        //Test Setup
    	RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("/api/v2/storefront"); //Setup Base Path
//        RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssuredUtil.resetBaseURI();
        RestAssuredUtil.resetBasePath();
    }
}

