package com.chirkov.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.chirkov.pages.LoginPage;
import com.chirkov.pages.TopNavPage;
import com.chirkov.providers.DeclaraLoginProvider;
import com.chirkov.providers.LinkedInLoginProvider;
import com.chirkov.providers.TwitterLoginProvider;
import com.chirkov.utils.DataSupplier;

public class LoginTest extends BaseTest{
	
	public LoginPage loginPage;
	public TopNavPage topNav;
	private String primaryUserEmail;
	private String primaryUserPwd;
	private String linkedInUserEmail;
	private String linkedInUserPwd;
	private String twitterUserEmail;
	private String twitterUserPwd;
	
	private DeclaraLoginProvider declaraLoginProvider;
	private TwitterLoginProvider twitterLoginProvider;
	private LinkedInLoginProvider linkedInLoginProvider;
	
	
	@BeforeMethod
	@BeforeClass
	public void setUp(){
		loginPage = new LoginPage(driverFactory);
		topNav= new TopNavPage(driverFactory);
		declaraLoginProvider = new DeclaraLoginProvider(driverFactory, loginPage);
		twitterLoginProvider = new TwitterLoginProvider(driverFactory);
		linkedInLoginProvider = new LinkedInLoginProvider(driverFactory);
		primaryUserEmail = DataSupplier.props.getProperty("primaryUserEmail");
		primaryUserPwd = DataSupplier.props.getProperty("primaryUserPwd");
		linkedInUserEmail = DataSupplier.props.getProperty("linkedInUserEmail");
		linkedInUserPwd = DataSupplier.props.getProperty("linkedInUserPwd");
		twitterUserEmail = DataSupplier.props.getProperty("twitterUserEmail");
		twitterUserPwd = DataSupplier.props.getProperty("twitterUserPwd");

	}
		
	@Test
	public void invalidEmailLogin(){
		loginPage.login(primaryUserEmail, primaryUserPwd+"1", declaraLoginProvider);
		wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorModal));
		Assert.assertTrue(loginPage.getErrorModal.isDisplayed(), "Error modal is not displayed");
		loginPage.closeErrorModal();
	}
	
	@Test
	public void validEmailLogin() {
		loginPage.login(primaryUserEmail, primaryUserPwd, declaraLoginProvider);
		wait.until(ExpectedConditions.visibilityOf(topNav.getProfileImage));
		Assert.assertTrue(topNav.getProfileImage.isDisplayed(), "Profile image is not Displayed");
		topNav.logOut();
	}
	
	@Test()
	public void validTwitterLogin() {
		loginPage.login(twitterUserEmail, twitterUserPwd, twitterLoginProvider);
		wait.until(ExpectedConditions.visibilityOf(topNav.getProfileImage));
		Assert.assertTrue(topNav.getProfileImage.isDisplayed(), "Profile image is not Displayed");
		topNav.logOut();
	}
	
	@Test()
	public void validLinkedInLogin() {
		loginPage.login(linkedInUserEmail, linkedInUserPwd, linkedInLoginProvider);
		wait.until(ExpectedConditions.visibilityOf(topNav.getProfileImage));
		Assert.assertTrue(topNav.getProfileImage.isDisplayed(), "Profile image is not Displayed");
		topNav.logOut();
	}
	
}
