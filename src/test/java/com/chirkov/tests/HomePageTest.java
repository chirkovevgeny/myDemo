package com.chirkov.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.pages.HomePage;
import com.chirkov.utils.DataSupplier;

public class HomePageTest extends BaseTest{
	
	public HomePage homePage;
	
	@BeforeClass
	public void setUp(){
		System.out.println("Executing BeforeClass for HomePageTest");
		homePage = new HomePage(driverFactory);
	}
		
	@Test
	public void testLoginBtn() throws Exception {
		homePage.goTo();
		homePage.clickLogIn();
		Assert.assertTrue(driver.getCurrentUrl().equals(DataSupplier.getURL()+"/login"));
	}
	
	@Test
	public void testSignUpBtn() throws InterruptedException {
		homePage.goTo();
		homePage.clickSignUp();
		Assert.assertTrue(driver.getCurrentUrl().equals(DataSupplier.getURL()+"/join"));
		
	}
}
