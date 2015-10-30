package com.chirkov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.chirkov.drivers.DriverFactory;
import com.chirkov.tests.BaseTest;
import com.google.common.base.Predicate;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

import test.configuration.Base;

public class HomePage extends BasePage{

	//private WebDriver driver;
	
	@FindBy(css="a.button.login")
	public WebElement loginButton;
	
	@FindBy(css=".heroContent>.button.action")
	public WebElement signUpButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLogIn(){
		driver.get(BASEURL);
		waitAndClick(loginButton);
		System.out.println(wait);
		System.out.println(urlLogin);
		//wait.until(urlLogin);
	}
	
	public void clickSignUp(){
		driver.get(BASEURL);
		waitAndClick(signUpButton);
		wait.until(urlJoin);
	}
	
	Predicate<WebDriver> urlLogin = dr -> dr.getCurrentUrl().equals(BASEURL+"/login");
	Predicate<WebDriver> urlJoin = dr -> dr.getCurrentUrl().equals(BASEURL+"/join");
	
//	Predicate<WebDriver> myPredicate = x ->x.findElement(By.id("id")).isDisplayed();
//
//	WebDriverWait wait = (WebDriverWait)new WebDriverWait(driver, 10);
//	wait.until(myPredicate);
	
	
}
