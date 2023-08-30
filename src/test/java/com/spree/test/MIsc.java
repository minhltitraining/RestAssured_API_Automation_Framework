package com.spree.test;

import org.testng.annotations.Test;

import com.spree.util.TestUtil;

public class MIsc {
	@Test
	public void f() {
		String accessToken = TestUtil.oAuth_Token();
		String userId = TestUtil.getUserId(accessToken);
		System.out.println(userId);
	}
}
