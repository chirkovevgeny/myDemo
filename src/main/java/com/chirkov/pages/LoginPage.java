package com.chirkov.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;
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
	private String email = null;
	private String password = null;
	
	public void goTo(){
		driver.get(DataSupplier.getURL()+"/login");
	}
	
	public LoginPage login(){
		return this;
	}
	
	public LoginPage withEmail(String email){
		this.email = email;
		return this;
	}

	public LoginPage withPassword(String password){
		this.password = password;
		return this;
	}
	
	public void submit() {
		type(getEmailInputField, email);
		type(getPasswordInputField, password);
		getLoginBtn.click();
	}
	
	public void closeErrorModal() {
		waitAndClick(getCloseModalBtn);
	}
}
