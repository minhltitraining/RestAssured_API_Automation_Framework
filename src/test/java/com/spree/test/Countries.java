package com.spree.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import com.spree.util.RestAssuredUtil;
import com.spree.testdata.Spreecom_TestData;
import com.spree.util.ResponseUtil;


public class Countries extends BaseTest {

	@Test(priority=1)
    public void T01_getDefaultCountries() {
        res = RestAssuredUtil.getResponse("/countries/default");
        ResponseUtil.checkStatusIs200(res);
        //jp = RestAssuredUtil.getJsonPath(res,"data.attributes.iso_name");
        //System.out.println(testUtil.getClients(jp));
        JsonPath jsonPathEvaluator = res.getBody().jsonPath();
		  String isoname=jsonPathEvaluator.get("data.attributes.iso_name").toString();
		  System.out.println("ISO Name is =>  " + isoname);
		  
    }
    
//    @Test(priority=2,dataProvider="country_iso",dataProviderClass=Spreecom_TestData.class)
//    public void T02_retrieveaCountry(String iso, String iso_name, String iso3) {
//        res = RestAssuredUtil.getResponse("/countries/"+iso);
//        testUtil.checkStatusIs200(res);
//       // jp = RestAssuredUtil.getJsonPath(res,"data.attributes.iso_name");
//       // System.out.println(testUtil.getClients(jp));
//       // Assert.assertEquals(testUtil.getClients(jp), "INDIA");
//        JsonPath jsonPathEvaluator = res.getBody().jsonPath();
//		  String isoname=jsonPathEvaluator.get("data.attributes.iso_name").toString();
//		  System.out.println("ISO Name is =>  " + isoname);
//		  
//    }
//    
//    @Test(priority=3)
//    public void T03_listallCountries() {
//        res = RestAssuredUtil.getResponse("/countries");
//        testUtil.checkStatusIs200(res);
//        //jp = RestAssuredUtil.getJsonPath(res,"data.attributes.iso_name");
//        //System.out.println(testUtil.getClients(jp));
//        JsonPath jsonPathEvaluator = res.getBody().jsonPath();
//		  String isoname=jsonPathEvaluator.get("data.attributes.iso_name").toString();
//		  System.out.println("ISO Name is =>  " + isoname);
//		  
//    }

   /* @Test
    public void T02_GetAllValue_UsingJSONPath() {
        res = RestAssuredUtil.getResponse("/read?id=5890");
        testUtil.checkStatusIs200(res);
        jp = RestAssuredUtil.getJsonPath(res);
        System.out.println("Opt: " + jp.get("name"));
        System.out.println("Description: " + jp.get("description"));
        System.out.println("Type: " + jp.get("price"));
    }*/
}