package com.chirkov.drivers;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.chirkov.tests.HomePageTest;

public class DriverFactory {
	
	// The Chrome Driver locations under the resource folder
	private static String CHROME_MAC_DRIVER = "/drivers/mac/chromedriver";
	private static String CHROME_WINDOWS_DRIVER = "/drivers/windows/chromedriver.exe";
	protected WebDriver driver;
//	protected DesiredCapabilities capabilities;
	
	public DriverFactory () {
		//this.capabilities = capabilities;
		setUpDriver("chrome");
	}
	
	public void setUpDriver (String browser) {
		if (browser == "chrome"){
			setupChromeDriver();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
//	setupChromeDriver should be called before each test class
	private void setupChromeDriver() {
	   // OS type
	   if (System.getProperty("os.name").contains("Mac")) {
	      File cDriver = new File(HomePageTest.class.getResource(CHROME_MAC_DRIVER).getFile());
	       
	      // Is it executable
	      if (!cDriver.canExecute()) {
	         cDriver.setExecutable(true);
	      }
	      System.setProperty("webdriver.chrome.driver", HomePageTest.class.getResource(CHROME_MAC_DRIVER).getFile());
	       
	   } else {
	      System.setProperty("webdriver.chrome.driver", HomePageTest.class.getResource(CHROME_WINDOWS_DRIVER).getFile()); 
	   }
	   driver = new ChromeDriver();
	}
	
}
