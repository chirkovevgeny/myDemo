package com.chirkov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.chirkov.drivers.BaseDriver;
import com.chirkov.drivers.DriverFactory;

public abstract class BasePage extends BaseDriver{
	
	protected BasePage(WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
