package com.chirkov.providers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.interfaces.ILoginProvider;
import com.chirkov.pages.BasePage;

public class TwitterLoginProvider extends BasePage implements ILoginProvider {

	@FindBy(id="username_or_email")
	public WebElement getEmailInputField;
	
	@FindBy(id="password")
	public WebElement getPasswordInputField;
	
	@FindBy(id="allow")
	public WebElement getAuthorizeAppBtn;
	
	@FindBy(css="button.action.twitter")
	public WebElement getLoginTwitterBtn;
	
	public TwitterLoginProvider(DriverFactory driverFactory) {
		super(driverFactory);
	}

	@Override
	public void login(String email, String password) {
		waitAndClick(getLoginTwitterBtn);
		type(getEmailInputField, email);
		type(getPasswordInputField, password);
		waitAndClick(getAuthorizeAppBtn);
	}

}
