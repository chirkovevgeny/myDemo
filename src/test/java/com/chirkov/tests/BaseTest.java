package com.chirkov.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.utils.DataSupplier;

public abstract class BaseTest {
	
	protected DriverFactory driverFactory;
	protected WebDriver driver;
	protected final static Logger logger = LoggerFactory
			.getLogger(BaseTest.class);
	protected FluentWait<WebDriver> wait;
	
	@Parameters("env")
	@BeforeSuite()
	public void setupTestSuite(@Optional("qa")String testEnv){
		DataSupplier.setUpConfig(testEnv);
		System.out.println("Executing BeforeSuite for BaseTest");
	}
	
	@AfterSuite(alwaysRun=true)
	public void tearDownSuite() throws Exception {
		System.out.println("Executing AfterSuite for BaseTest");
	}
	
	@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void setUpDriver(@Optional("chrome") String browser){
		driverFactory = new DriverFactory();
		driverFactory.setUpDriver(browser);
		driver = driverFactory.getDriver();
		wait = driverFactory.getWait();
		System.out.println("Executing BeforeClass for BaseTest");
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() throws Exception {
		System.out.println("Executing AfterClass for BaseTest");
		driver.quit();
	}
	
}
