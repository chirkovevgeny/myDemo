package com.chirkov.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.utils.DataSupplier;

public class LoginPage extends BasePage {

	@FindBy(name="email_address")
	public WebElement emailInputField;
	
	@FindBy(name="password")
	public WebElement passwordInputField;
	
	@FindBy(id="login")
	public WebElement loginBtn;
	
	private String email = null;
	private String password = null;
	
	public LoginPage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
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
		type(emailInputField, email);
		type(passwordInputField, password);
		loginBtn.click();
	}
}
