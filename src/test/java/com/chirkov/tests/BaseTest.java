package com.chirkov.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.chirkov.drivers.DriverFactory;

public class BaseTest {
	
	protected DriverFactory driverFactory;
	protected WebDriver driver;
	
	public BaseTest(){	
	}
	
	@BeforeSuite
	public void setupTestSuite(){
		System.out.println("Before Suite is running");
		driverFactory = new DriverFactory();
		driverFactory.setUpDriver("chrome");
		driver = driverFactory.getDriver();
	}
	
	@BeforeClass
	public void verifyLoggedOut(){
		System.out.println("verifying that user is logged out");
	}
	
	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}	
}
