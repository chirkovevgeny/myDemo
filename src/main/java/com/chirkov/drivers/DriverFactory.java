package com.chirkov.drivers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chirkov.utils.DataSupplier;

public class DriverFactory {
	
	// The Chrome Driver locations under the resource folder
	private static String CHROME_MAC_DRIVER = DataSupplier.props.getProperty("CHROME_MAC_DRIVER");
	private static String CHROME_WINDOWS_DRIVER = DataSupplier.props.getProperty("CHROME_WINDOWS_DRIVER");
	public static final int TIMEOUT = Integer.parseInt(DataSupplier.props.getProperty("TIMEOUT"));
	public static final int IMPL_TIMEOUT = Integer.parseInt(DataSupplier.props.getProperty("IMPL_TIMEOUT"));
	
	
	private WebDriver driver;
	private FluentWait<WebDriver> wait;
//	protected DesiredCapabilities capabilities;
	
	public DriverFactory () {
		//this.capabilities = capabilities;
	}
	
	public void setUpDriver (String browser) {
		if (browser.equalsIgnoreCase("chrome")){
			setupChromeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")){
			setupFirefoxDriver();
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
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
	}
	
//	setupChromeDriver should be called before each test class
	private void setupChromeDriver() {
	   // OS type
	   if (System.getProperty("os.name").contains("Mac")) {
	      File cDriver = new File(DriverFactory.class.getResource(CHROME_MAC_DRIVER).getFile());
	       
	      // Is it executable
	      if (!cDriver.canExecute()) {
	         cDriver.setExecutable(true);
	      }
	      System.setProperty("webdriver.chrome.driver", DriverFactory.class.getResource(CHROME_MAC_DRIVER).getFile());
	       
	   } else {
	      System.setProperty("webdriver.chrome.driver", DriverFactory.class.getResource(CHROME_WINDOWS_DRIVER).getFile()); 
	   }
	   driver = new ChromeDriver();
	}
	
	private void setupFirefoxDriver() {
		driver = new FirefoxDriver();
	}
	
}
