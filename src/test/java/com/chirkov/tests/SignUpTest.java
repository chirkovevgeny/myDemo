package com.chirkov.tests;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.pages.SignUpPage;
import com.chirkov.pages.TopNavPage;
import com.chirkov.specs.User;
import com.chirkov.utils.TestData;

public class SignUpTest extends BaseTest {

	public SignUpPage signUpPage;
	public TopNavPage topNav;
	private User newUser = User.createRandom();

	@BeforeClass
	public void setUp() {
		signUpPage = new SignUpPage(driverFactory);
		topNav = new TopNavPage(driverFactory);
	}

	@Test(priority = 1)
	public void navigateToEmailSignUpPage() {
		signUpPage.goTo();
		signUpPage.getJoinEmailBtn.click();
		wait.until(ExpectedConditions.visibilityOf(signUpPage.getSignUpBtn));
		Assert.assertTrue(signUpPage.getSignUpBtn.isDisplayed(), "Sign Up button is not displayed");
	}

	@Test(dataProviderClass = TestData.class, dataProvider = "signUpData", priority = 2)
	public void testInvalidSignUp(String firstName, String lastName, String email, String password, String fNameError,
			String lNameError, String emailError, String passwordError) {
		signUpPage.fillOuSignUpEmailForm(firstName, lastName, email, password);
		if (!fNameError.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOf(signUpPage.getFirstNameError));
			Assert.assertTrue(signUpPage.getFirstNameError.getText().equals(fNameError),
					"First name error validation failed");
		} else
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getFirstNameError),
					"First name error displayed");
		if (!lNameError.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOf(signUpPage.getLastNameError));
			Assert.assertTrue(signUpPage.getLastNameError.getText().equals(lNameError),
					"Last name error validation have failed");
		} else
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getLastNameError),
					"Last name error displayed");
		if (!emailError.isEmpty()) {
			if (email.isEmpty()) {
				wait.until(ExpectedConditions.visibilityOf(signUpPage.getEmptyEmailError));
				Assert.assertTrue(signUpPage.getEmptyEmailError.getText().equals(emailError),
						"Empty email error validation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getInvalidEmailError),
						"Invaled email error displayed");
			} else {
				wait.until(ExpectedConditions.visibilityOf(signUpPage.getInvalidEmailError));
				Assert.assertTrue(signUpPage.getInvalidEmailError.getText().equals(emailError),
						"Invalid email error validation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyEmailError),
						"Empty email error displayed");
			}
		} else {
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyEmailError),
					"Empty email error displayed");
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getInvalidEmailError),
					"Invalid email error displayed");
		}
		if (!passwordError.isEmpty()) {
			if (password.isEmpty()) {
				wait.until(ExpectedConditions.visibilityOf(signUpPage.getEmptyPaswordError));
				Assert.assertTrue(signUpPage.getEmptyPaswordError.getText().equals(passwordError),
						"Empty password error vaslidation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getShortPasswordError),
						"Short password email error displayed");
			} else {
				wait.until(ExpectedConditions.visibilityOf(signUpPage.getShortPasswordError));
				Assert.assertTrue(signUpPage.getShortPasswordError.getText().equals(passwordError),
						"Short password error validation failed");
				Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyPaswordError),
						"Empty password error displayed");
			}
		} else {
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getShortPasswordError),
					"Short password error displayed");
			Assert.assertTrue(signUpPage.isElementNotDisplayed(signUpPage.getEmptyPaswordError),
					"Empty password error displayed");
		}
	}

	@Test(priority = 3)
	public void successfullSignUp() {
		signUpPage.goTo();
		signUpPage.getJoinEmailBtn.click();
		int numOfInterests = ThreadLocalRandom.current().nextInt(3, 15);
		logger.info(newUser.toString());
		signUpPage.fillOutSignUpEmailForm(newUser);
		logger.info("Number of selected interests is " + Integer.toString(numOfInterests));
		signUpPage.pickRandomInterests(numOfInterests);
		wait.until(ExpectedConditions.visibilityOf(topNav.getProfileImage));
		Assert.assertTrue(topNav.getProfileImage.isDisplayed(), "Profile image is not Displayed");
	}
}
