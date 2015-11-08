package com.chirkov.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;

public class TopNavPage extends BasePage{
	
	@FindBy(id="ProfileNavLink")
	public WebElement profileImage;
	
	public TopNavPage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	

}
