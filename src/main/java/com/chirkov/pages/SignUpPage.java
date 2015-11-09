package com.chirkov.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.specs.User;
import com.chirkov.utils.DataSupplier;

public class SignUpPage extends BasePage{
	
	public SignUpPage(DriverFactory driverFactory) {
		super(driverFactory);
	}
	
	@FindBy(css="button.join")
	public WebElement getJoinEmailBtn;
	
	@FindBy(name="nameFirst")
	public WebElement getFirstNameInput;
	
	@FindBy(name="nameLast")
	public WebElement getLastNameInput;
	
	@FindBy(name="emailAddress")
	public WebElement getEmailAddressInput;

	@FindBy(name="new_declara_password")
	public WebElement getPasswordInput;

	@FindBy(css="button[type='submit']")
	public WebElement getSignUpBtn;
	
	@FindBy(xpath="//input[@name='nameFirst']/following-sibling::div[@class='aside msg']")
	public WebElement getFirstNameError;

	@FindBy(xpath="//input[@name='nameLast']/following-sibling::div[@class='aside msg']")
	public WebElement getLastNameError;
	
	@FindBy(xpath="//input[@name='emailAddress']/following-sibling::div[@aria-hidden='false']")
	public WebElement getInvalidEmailError;
	
	@FindBy(xpath="//div[@form='createAccountForm']/following-sibling::div[@aria-hidden='false']")
	public WebElement getEmptyEmailError;
	
	@FindBy(xpath="//input[@name='new_declara_password']/following-sibling::div[@class='aside msg ng-binding']")
	public WebElement getShortPasswordError;
	
	@FindBy(css="div.validateField.last>div.aside.msg")
	public WebElement getEmptyPaswordError;
	
	@FindBy(id="nextStep_first")
	public WebElement getFirstNextBtn;
	
	@FindBy(id="nextStep")
	public WebElement getSecondNextBtn;
	
	@FindBy(id="openInterestPicker")
	public WebElement getThirdNextBtn;
	
	@FindBy(css="div.interest.ng-scope")
	List<WebElement> getInterests;

	@FindBy(id="selectInterests")
	public WebElement getComeOnInBtn;
	

	public void goTo(){
		driver.get(DataSupplier.getURL()+"/join");
		wait.until(ExpectedConditions.visibilityOf(getJoinEmailBtn));
	}
	
	public void fillOuSignUpEmailForm(String firstName, String lastName, String email, String password){
		type(getFirstNameInput, firstName);
		type(getLastNameInput, lastName);
		type(getEmailAddressInput, email);
		type(getPasswordInput, password);
		waitAndClick(getSignUpBtn);
	}
	
	public void fillOutSignUpEmailForm(User user){
		type(getFirstNameInput, user.getName());
		type(getLastNameInput, user.getLastName());
		type(getEmailAddressInput, user.getEmail());
		type(getPasswordInput, user.getPassword());
		waitAndClick(getSignUpBtn);
	}
	
	public void pickRandomInterests(int NumOfIntersts){
		waitAndClick(getFirstNextBtn);
		waitAndClick(getSecondNextBtn);
		waitAndClick(getThirdNextBtn);
		clickToInterests(getInterests, NumOfIntersts);
		waitAndClick(getComeOnInBtn);
	}
	
	private void clickToInterests(List<WebElement> elements, int NumOfInterests){
		Random rand = new Random();
		List<Integer> interestIndexes = new ArrayList<Integer>();
		wait.until(ExpectedConditions.visibilityOfAllElements(getInterests));
		while (interestIndexes.size()<NumOfInterests){
			Integer randInt = (Integer)rand.nextInt(getInterests.size());
			if (!interestIndexes.contains(randInt)){
				waitAndClick(elements.get(randInt));
				interestIndexes.add(randInt);
			}
		}
	}

}
