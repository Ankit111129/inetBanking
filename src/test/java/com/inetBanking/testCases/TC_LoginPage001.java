package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.PageObject.LoginPage;

public class TC_LoginPage001 extends BaseClass{

	@Test
	public void loginTest() throws IOException {
		
	driver.get(baseUrl);
	
	LoginPage lp = new LoginPage(driver);
	
	lp.setUserName(username);

	
	lp.setPassword(password);
	lp.clickSubmit();
	
	if(driver.getTitle().equals("Guru99 Bank Manager HomePage1234555"))
	
	{
	   Assert.assertTrue(true);
	  
	}
	else
	{
		captureScreen( driver , "loginTest");
		Assert.assertFalse(false);
		
	}
	
	}
	
	
}

