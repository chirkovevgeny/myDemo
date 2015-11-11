package com.chirkov.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.interfaces.ILoginProvider;
import com.chirkov.utils.DataSupplier;

public class LoginPage extends BasePage {

	public LoginPage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	@FindBy(name="email_address")
	public WebElement getEmailInputField;
	
	@FindBy(name="password")
	public WebElement getPasswordInputField;
	
	@FindBy(id="login")
	public WebElement getLoginBtn;
	
	@FindBy(css="div.declaraModal-wrapper")
	public WebElement getErrorModal;
	
	@FindBy(css="div.declaraModal-wrapper button")
	public WebElement getCloseModalBtn;
	
	public final String PAGE_TITLE = "Login to Declara";
	
	public void goTo(){
		driver.get(DataSupplier.getURL()+"/login");
	}

	public void login(String email, String password, ILoginProvider loginProvider){
		this.goTo();
		loginProvider.login(email, password);
	}

	public void closeErrorModal() {
		waitAndClick(getCloseModalBtn);
	}
}
