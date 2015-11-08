package com.chirkov.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.chirkov.pages.LoginPage;
import com.chirkov.pages.SignUpPage;
import com.chirkov.pages.TopNavPage;
import com.chirkov.utils.DataSupplier;
import com.chirkov.utils.TestData;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Verify;

public class SignUpTest extends BaseTest{
	
	public SignUpPage signUpPage;
	private String primaryUserEmail;
	
	@BeforeClass
	public void setUp(){
		System.out.println("Executing BeforeClass for SignUpTest");
		signUpPage = new SignUpPage(driverFactory);
//		topNav= new TopNavPage(driverFactory);
		primaryUserEmail = DataSupplier.props.getProperty("primaryUserEmail");
	}
	
	@Test
	public void navigateToEmailSignUpPage(){
		signUpPage.goTo();
		signUpPage.getJoinEmailBtn.click();
		wait.until(ExpectedConditions.visibilityOf(signUpPage.getSignUpBtn));
		Assert.assertTrue(signUpPage.getSignUpBtn.isDisplayed(), "Sign Up button is not displayed");
		
	}
	
	@Test(dataProviderClass = TestData.class, dataProvider = "signUpData")
	public void testInvalidSignUp(String firstName,String lastName,String email, String password, 
								String fNameError, String lNameError, String emailError, String passwordError){
		signUpPage.SignUpWithEmail(firstName, lastName, email, password);
		if (!fNameError.isEmpty()){
			Assert.assertTrue(signUpPage.getFirstNameError.getText().equals(fNameError), "First name error validation failed");
		}
		else Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getFirstNameError), "First name error displayed");
		if (!lNameError.isEmpty()){
			Assert.assertTrue(signUpPage.getLastNameError.getText().equals(lNameError), "Last name error validation have failed");
		}
		else Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getLastNameError), "Last name error displayed");
		if (!emailError.isEmpty()){
			if (email.isEmpty()){
				Assert.assertTrue(signUpPage.getEmptyEmailError.getText().equals(emailError), "Empty email error validation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getInvalidEmailError), "Invaled email error displayed");	
			}
			else{
				Assert.assertTrue(signUpPage.getInvalidEmailError.getText().equals(emailError), "Invalid email error validation failed");	
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyEmailError), "Empty email error displayed");
			}
		}
		else {
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyEmailError), "Empty email error displayed");
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getInvalidEmailError),"Invalid email error displayed");
		}
		if (!passwordError.isEmpty()){
			if (password.isEmpty()){
				Assert.assertTrue(signUpPage.getEmptyPaswordError.getText().equals(passwordError), "Empty password error vaslidation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getShortPasswordError), "Short password email error displayed");
			}
			else{
			Assert.assertTrue(signUpPage.getShortPasswordError.getText().equals(passwordError), "Short password error validation failed");
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyPaswordError), "Empty password error displayed");
			}
		}
		else{
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getShortPasswordError), "Short password error displayed");
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyPaswordError), "Empty password error displayed");
		}
	}
	
//	@Test

}
