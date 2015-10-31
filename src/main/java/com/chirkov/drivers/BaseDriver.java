package com.chirkov.drivers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseDriver {
	
	public FluentWait<WebDriver> wait;
	protected DriverFactory driverFactory;
	protected WebDriver driver;
	
	public BaseDriver(DriverFactory driverFactory) {
		this.driverFactory = driverFactory;
		driver = driverFactory.getDriver();
		wait = driverFactory.getWait();
	}
	
	// The Chrome Driver locations under the resource folder
		
//		protected DesiredCapabilities capabilities;
		
		public WebDriver getDriver() {
			return driver;
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

}
