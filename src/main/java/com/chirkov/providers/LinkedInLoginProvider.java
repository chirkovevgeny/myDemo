package com.chirkov.providers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.interfaces.ILoginProvider;
import com.chirkov.pages.BasePage;

public class LinkedInLoginProvider extends BasePage implements ILoginProvider {

	public LinkedInLoginProvider(DriverFactory driverFactory) {
		super(driverFactory);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name="session_key")
	public WebElement getEmailInputField;
	
	@FindBy(name="session_password")
	public WebElement getPasswordInputField;
	
	@FindBy(name="authorize")
	public WebElement getAllowAccessBtn;
	
	@FindBy(css="button.action.linkedin")
	public WebElement getLoginLinkedInBtn;
	
	@Override
	public void login(String email, String password) {
		waitAndClick(getLoginLinkedInBtn);
		type(getEmailInputField, email);
		type(getPasswordInputField, password);
		waitAndClick(getAllowAccessBtn);
	}

}
