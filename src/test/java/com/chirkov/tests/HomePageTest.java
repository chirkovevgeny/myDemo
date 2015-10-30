package com.chirkov.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.pages.HomePage;

public class HomePageTest extends BaseTest{
	
	public HomePage homePage;
	
	@BeforeClass
	public void setUp(){
		homePage = new HomePage(driver);
	}
		
	@Test
	public void testLoginBtn() throws Exception {
		homePage.clickLogIn();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().equals(baseDriver.BASEURL+"/login"));
	}
	
	@Test
	public void testSignUpBtn() throws InterruptedException {
		homePage.clickSignUp();
		Assert.assertTrue(driver.getCurrentUrl() == (baseDriver.BASEURL+"/join"));
		Thread.sleep(5000);
	}
}
