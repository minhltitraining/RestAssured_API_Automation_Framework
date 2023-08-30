package com.spree.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import com.spree.util.RestRequestUtil;
import com.spree.testdata.Spreecom_TestData;
import com.spree.util.RestResponseUtil;

public class Countries extends BaseTest {

	@Test(priority = 1)
	public void T01_getDefaultCountries() {
		test = extent.createTest("Test case 1", "Get default countries");
		res = RestRequestUtil.getResponse("/countries/default");
		RestResponseUtil.checkStatusIs200(res);
		String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
	}

	@Test(priority = 2, dataProvider = "country_iso", dataProviderClass = Spreecom_TestData.class)
	public void T02_retrieveaCountry(String iso, String iso_name, String iso3) {
		test = extent.createTest("Test case 2", "Retrieve a country");
		res = RestRequestUtil.getResponse("/countries/" + iso);
		RestResponseUtil.checkStatusIs200(res);
		String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
	}
    
    @Test(priority=3)
    public void T03_listallCountries() {
    	test = extent.createTest("Test case 3", "List all countries");
        res = RestRequestUtil.getResponse("/countries");
        RestResponseUtil.checkStatusIs200(res);
        String isoname = RestResponseUtil.getValue(res, "data.attributes.iso_name");
		System.out.println("ISO Name is =>  " + isoname);
    }

	/*
	 * @Test public void T02_GetAllValue_UsingJSONPath() { res =
	 * RestAssuredUtil.getResponse("/read?id=5890"); testUtil.checkStatusIs200(res);
	 * jp = RestAssuredUtil.getJsonPath(res); System.out.println("Opt: " +
	 * jp.get("name")); System.out.println("Description: " + jp.get("description"));
	 * System.out.println("Type: " + jp.get("price")); }
	 */

}