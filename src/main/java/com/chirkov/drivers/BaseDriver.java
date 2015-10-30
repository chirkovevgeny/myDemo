package com.chirkov.drivers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chirkov.tests.HomePageTest;

public class BaseDriver {
	
	public FluentWait<WebDriver> wait;
	protected WebDriver driver;
	public final String BASEURL = "https://qa-olive.int.declara.com";
	public static final int TIMEOUT = 20;
	private static final String CHROME_MAC_DRIVER = "/drivers/mac/chromedriver";
	private static final String CHROME_WINDOWS_DRIVER = "/drivers/windows/chromedriver.exe";
	
	
	public BaseDriver() {
	}
	
	// The Chrome Driver locations under the resource folder
		
//		protected DesiredCapabilities capabilities;
			
		public void setUpDriver (String browser) {
			if (browser == "chrome"){
				setupChromeDriver();
			}
			this.wait = wait(TIMEOUT);
			setImpliciteWait(TIMEOUT);
		}
		
		public WebDriver getDriver() {
			return driver;
		}
		
		private void setImpliciteWait(int timeout) {
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		}

		protected FluentWait<WebDriver> wait(int timeout) {
			return new WebDriverWait(driver, timeout).ignoring(
					NoSuchElementException.class,
					StaleElementReferenceException.class);
		}
		
		protected void waitForElement(WebElement element, int timeout) {
			try {
				wait(timeout).until(
						ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {
			}
		}
		
		protected void waitAndClick(WebElement element) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e) {
			}
			element.click();
		}
		
		protected void type(WebElement element, String text) {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			element.clear();
			element.sendKeys(text);
		}

//		setupChromeDriver should be called before each test class
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
		   this.driver = new ChromeDriver();
		}
	
}
