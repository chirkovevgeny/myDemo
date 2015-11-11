package com.chirkov.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.chirkov.drivers.DriverFactory;

public class TopNavPage extends BasePage{
	
	@FindBy(id="ProfileNavLink")
	public WebElement getProfileImage;
	
	@FindBy(css="a[title='My Profile']")
	public WebElement getProfileMenue;
	
	@FindBy(id="LogOutLink")
	public WebElement getLogOutLink;
	
	public TopNavPage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	public void logOut(){
		wait.until(ExpectedConditions.visibilityOf(getProfileImage));
		actions.moveToElement(getProfileImage).perform();
		waitAndClick(getLogOutLink);
	}
	

}
