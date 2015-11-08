package com.chirkov.pages;

import org.openqa.selenium.support.PageFactory;

import com.chirkov.drivers.BaseDriver;
import com.chirkov.drivers.DriverFactory;



public abstract class BasePage extends BaseDriver{
	
	protected BasePage(DriverFactory driverFactory) {
		super(driverFactory);
		PageFactory.initElements(driver, this);
	}
	
}
