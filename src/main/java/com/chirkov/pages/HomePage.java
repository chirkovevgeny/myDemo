package com.chirkov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.constants.DataConstants;
import com.chirkov.drivers.DriverFactory;
import com.google.common.base.Predicate;

public class HomePage extends BasePage{
	
	@FindBy(css="a.button.login")
	public WebElement loginButton;
	
	@FindBy(css=".heroContent>.button.action")
	public WebElement signUpButton;
	
	public HomePage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	public void clickLogIn(){
		driver.get(DataConstants.BASEURL);
		waitAndClick(loginButton);
		wait.until(urlLogin);
	}
	
	public void clickSignUp(){
		driver.get(DataConstants.BASEURL);
		waitAndClick(signUpButton);
		wait.until(urlJoin);
	}
	
	private final Predicate<WebDriver> urlLogin = dr -> dr.getCurrentUrl().equals(DataConstants.BASEURL+"/login");
	private final Predicate<WebDriver> urlJoin = dr -> dr.getCurrentUrl().equals(DataConstants.BASEURL+"/join");
		
	
}
