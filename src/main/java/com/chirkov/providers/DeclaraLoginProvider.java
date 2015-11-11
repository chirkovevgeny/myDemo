package com.chirkov.providers;

import com.chirkov.drivers.BaseDriver;
import com.chirkov.drivers.DriverFactory;
import com.chirkov.interfaces.ILoginProvider;
import com.chirkov.pages.LoginPage;

public class DeclaraLoginProvider extends BaseDriver implements ILoginProvider {

	LoginPage loginPage;
	
	public DeclaraLoginProvider(DriverFactory driverFactory, LoginPage loginPage) {
		super (driverFactory);
		this.loginPage = loginPage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void login(String email, String password) {
		type(loginPage.getEmailInputField, email);
		type(loginPage.getPasswordInputField, password);
		loginPage.getLoginBtn.click();
	}

}
