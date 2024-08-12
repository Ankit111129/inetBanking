package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.PageObject.LoginPage;
import com.inetBanking.Utilities.XLUtils;

public class TC_LoginPage002 extends BaseClass{

	
	@Test(dataProvider="LoginData")
	public void loginTDD(String username, String password) throws InterruptedException
	{
	
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		Thread.sleep(3000);
		
		
		if (isAlertPresent()== true)
		{
			driver.switchTo().alert().accept(); //close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			
		}
		
		else {
			Assert.assertTrue(true);
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent() // user define method to check if alaert is present or not
	{
		try {
		driver.switchTo().alert();
		return true;
		}
		
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name = "LoginData")
	String[][]  getData() throws IOException
	{
		String path =  System.getProperty("user.dir") + "/src/test/java/com/inetBanking/TestData/LoginData.xlsx";
		
		System.out.println(path);
		
		int rownum= XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String [rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
	}
	
	
	
}
