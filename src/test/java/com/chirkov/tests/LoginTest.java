package com.chirkov.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.pages.LoginPage;
import com.chirkov.pages.TopNavPage;
import com.chirkov.utils.DataSupplier;

public class LoginTest extends BaseTest{
	
	public LoginPage loginPage;
	public TopNavPage topNav;
	private String primaryUserEmail;
	private String primaryUserPwd;
	
	
	@BeforeClass
	public void setUp(){
		loginPage = new LoginPage(driverFactory);
		topNav= new TopNavPage(driverFactory);
		primaryUserEmail = DataSupplier.props.getProperty("primaryUserEmail");
		primaryUserPwd = DataSupplier.props.getProperty("primaryUserPwd");
	}
		
	@Test
	public void invalidEmailLogin(){
		loginPage.goTo();
		loginPage.login().withEmail(primaryUserEmail).withPassword(primaryUserPwd+"1").submit();
		wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorModal));
		Assert.assertTrue(loginPage.getErrorModal.isDisplayed(), "Error modal is not displayed");
		loginPage.closeErrorModal();
	}
	
	@Test
	public void validEmailLogin() throws Exception {
		loginPage.goTo();
		AssertJUnit.assertEquals(driver.getTitle(), loginPage.PAGE_TITLE);
		loginPage.login()
			.withEmail(primaryUserEmail)
			.withPassword(primaryUserPwd)
			.submit();
		wait.until(ExpectedConditions.visibilityOf(topNav.profileImage));
		Assert.assertTrue(topNav.profileImage.isDisplayed(), "Profile image is not Displayed");
	}
	
}
