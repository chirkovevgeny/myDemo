package com.chirkov.drivers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chirkov.tests.HomePageTest;

public class DriverFactory {
	
	// The Chrome Driver locations under the resource folder
	private static String CHROME_MAC_DRIVER = "/drivers/mac/chromedriver";
	private static String CHROME_WINDOWS_DRIVER = "/drivers/windows/chromedriver.exe";
	public static final int TIMEOUT = 20;
	public static final int IMPL_TIMEOUT = 500;

	
	private WebDriver driver;
	private FluentWait<WebDriver> wait;
//	protected DesiredCapabilities capabilities;
	
	public DriverFactory () {
		//this.capabilities = capabilities;
	}
	
	public void setUpDriver (String browser) {
		if (browser == "chrome"){
			setupChromeDriver();
		}
		this.wait = createWait(TIMEOUT);
		setImpliciteWait(IMPL_TIMEOUT);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public FluentWait<WebDriver> getWait() {
		return wait;
	}
	
	public void setWait(){}
	
	protected FluentWait<WebDriver> createWait(int timeout) {
		return new WebDriverWait(driver, timeout).ignoring(
				NoSuchElementException.class,
				StaleElementReferenceException.class);
	}
	
	private void setImpliciteWait(int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
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
