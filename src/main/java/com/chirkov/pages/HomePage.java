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
	public WebElement getLoginButton;
	
	@FindBy(css=".heroContent>.button.action")
	public WebElement getSignUpButton;
	
	public HomePage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	public void goTo(){
		driver.get(DataSupplier.getURL());
		wait.until(ExpectedConditions.elementToBeClickable(getLoginButton));
	}
	
	public void clickLogIn(){
		waitAndClick(getLoginButton);
		wait.until(loginUrlBuilt);
	}
	
	public void clickSignUp(){
		waitAndClick(getSignUpButton);
		wait.until(joinUrlBuilt);
	}
	
	private final Predicate<WebDriver> loginUrlBuilt = dr -> dr.getCurrentUrl().equals(DataSupplier.getURL()+"/login");
	private final Predicate<WebDriver> joinUrlBuilt = dr -> dr.getCurrentUrl().equals(DataSupplier.getURL()+"/join");
		
	
}
