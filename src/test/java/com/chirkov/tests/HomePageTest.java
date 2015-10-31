package com.chirkov.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.constants.DataConstants;
import com.chirkov.pages.HomePage;

public class HomePageTest extends BaseTest{
	
	public HomePage homePage;
	
	@BeforeClass
	public void setUp(){
		homePage = new HomePage(driverFactory);
	}
		
	@Test
	public void testLoginBtn() throws Exception {
		homePage.clickLogIn();
		Assert.assertTrue(driver.getCurrentUrl().equals(DataConstants.BASEURL+"/login"));
	}
	
	@Test
	public void testSignUpBtn() throws InterruptedException {
		homePage.clickSignUp();
		Assert.assertTrue(driver.getCurrentUrl().equals(DataConstants.BASEURL+"/join"));
	}
}
