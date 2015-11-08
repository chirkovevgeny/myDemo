package com.chirkov.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.chirkov.pages.LoginPage;
import com.chirkov.pages.TopNavPage;
import com.chirkov.utils.DataSupplier;

public class LoginTest extends BaseTest{
	
	public LoginPage loginPage;
	public TopNavPage topNav;
	private String primaryUserEmail;
	private String primaryUserPwd;
	
//	HashMap<String, String> loginMap;
	
//	@Factory(dataProvider = "loginData", dataProviderClass = TestData.class)
//	public LoginTest(String email, String password, String error){
//		loginMap = new HashMap<String, String>();
//		loginMap.put("email", email);
//		loginMap.put("password", password);
//		loginMap.put("error", error);
//	}
	
	
	@BeforeClass
	public void setUp(){
		System.out.println("Executing BeforeClass for LoginTest");
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
		//loginPage
	}
	
	@Test
	public void validEmailLogin() throws Exception {
		loginPage.goTo();
		AssertJUnit.assertEquals(driver.getTitle(), loginPage.PAGE_TITLE);
		loginPage.login()
			.withEmail(primaryUserEmail)//(loginMap.get("email"))//(DataSupplier.props.getProperty("primaryUserEmail"))
			.withPassword(primaryUserPwd)//(loginMap.get("password"))//(DataSupplier.props.getProperty("primaryUserPwd"))
			.submit();
		wait.until(ExpectedConditions.visibilityOf(topNav.profileImage));
		Assert.assertTrue(topNav.profileImage.isDisplayed(), "Profile image is not Displayed");
	}
	
}
