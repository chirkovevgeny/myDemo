package com.chirkov.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.chirkov.drivers.BaseDriver;
import com.chirkov.drivers.DriverFactory;

public class BaseTest {
	
	protected BaseDriver baseDriver;
	protected WebDriver driver;
	
	public BaseTest(){	
	}
	
	@BeforeSuite
	public void setupTestSuite(){
		System.out.println("Before Suite is running");
		baseDriver = new BaseDriver();
		baseDriver.setUpDriver("chrome");
		driver = baseDriver.getDriver();
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
