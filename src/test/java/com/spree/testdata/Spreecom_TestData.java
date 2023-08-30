package com.spree.testdata;

import org.testng.annotations.DataProvider;

public class Spreecom_TestData {
	@DataProvider(name="country_iso")
	public Object[][] iso_name(){
		// Two dimensional object
	    return new Object[][] {
	            {"usa","UNITED STATES","USA"},
	            {"ind","INDIA","IND"},
	            {"pak","PAKISTAN","PAK"},
	            {"gb","UNITED KINGDOM","GBR"},
	            {"afg","AFGHANISTAN","AFG"}
	            };
	}
	
	@DataProvider(name="Addresses")
	public Object[][] addresses(){
		return new Object[][] {
			{ "Komal", "K", "213 Main St", "Phoenix", "0987654321", "12030", "AZ", "US" },
			{ "Lily", "N", "903 South Dr", "Chicago", "0987654321", "58320", "IL", "US" },
			{ "Dean", "B", "468 Mori Ln", "Houston", "0987654321", "93717", "TX", "US" },
			{ "Wendy", "W", "473 Lincoln Dr", "SF", "0987654321", "93473", "CA", "US" },
			{ "Sam", "P", "229 Fox Ln", "Austin", "0987654321", "75030", "TX", "US" },
		};
	}
	
	@DataProvider(name="BadAddresses")
	public Object[][] badAddresses(){
		return new Object[][] {
			{"", "N1", "100 1st St", "Dallas", "75001","1", "TX", "US", "emptyFirstname"},
			{"Minh1", "", "100 1st St", "Dallas", "75001","1", "TX", "US", "emptyLastname"},
			{"Minh1", "N1", "", "Dallas", "75001","1", "TX", "US", "emptyAddress1"},
			{"Minh1", "N1", "100 1st St", "", "75001","1", "TX", "US", "emptyCity"},
			{"Minh1", "N1", "100 1st St", "Dallas", "","1", "TX", "US", "emptyZipcode"},
			{"Minh1", "N1", "100 1st St", "Dallas", "75001","", "TX", "US", "emptyPhoneNumber"},
			{"Minh1", "N1", "100 1st St", "Dallas", "75001","1", "", "US", "emptyState"},
			{"Minh1", "N1", "100 1st St", "Dallas", "75001","1", "TX", "", "emptyCountry"}	
		};
	}
	
	@DataProvider(name="addressWithLabel")
	public Object [][] addressWithLabel(){
		return new Object[][] {
			{"Minh1", "N1", "100 1st St", "Dallas", "75001","1", "TX", "US", "Work"}
		};
	}
	
}