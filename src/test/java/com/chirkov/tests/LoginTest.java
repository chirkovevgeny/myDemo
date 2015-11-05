package com.chirkov.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chirkov.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	public LoginPage loginPage;
	
	@BeforeClass
	public void setUp(){
		System.out.println("Executing BeforeClass for LoginTest");
		loginPage = new LoginPage(driverFactory);
	}
		
	@Test
	public void testLoginBtn() throws Exception {
		loginPage.goTo();
		loginPage.login()
			.withEmail("jack.chirkov+11@declara.com")
			.withPassword("12345678")
			.submit();
		Thread.sleep(3000);
//		Assert.assertTrue(driver.getCurrentUrl().equals(DataSupplier.BASEURL+"/login"));
	}
	
}
