package com.chirkov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.utils.DataSupplier;
import com.google.common.base.Predicate;

public class HomePage extends BasePage{
	
	@FindBy(css="a.button.login")
	public WebElement loginButton;
	
	@FindBy(css=".heroContent>.button.action")
	public WebElement signUpButton;
	
	public HomePage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	public void goTo(){
		driver.get(DataSupplier.getURL());
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	}
	
	public void clickLogIn(){
		waitAndClick(loginButton);
		wait.until(urlLogin);
	}
	
	public void clickSignUp(){
		waitAndClick(signUpButton);
		wait.until(urlJoin);
	}
	
	private final Predicate<WebDriver> urlLogin = dr -> dr.getCurrentUrl().equals(DataSupplier.getURL()+"/login");
	private final Predicate<WebDriver> urlJoin = dr -> dr.getCurrentUrl().equals(DataSupplier.getURL()+"/join");
		
	
}
