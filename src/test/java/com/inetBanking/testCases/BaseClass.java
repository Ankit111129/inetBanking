package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.Utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig =new ReadConfig();
	
	
	public String baseUrl = readConfig.gerApplicationURL();
	public String username = readConfig.getUserName();
	public String password = readConfig.getpassword();
	public static WebDriver driver;
	
	
	
	
	
	@BeforeClass
	@Parameters("browser")
	
	public void setup(String br)
	
	{
	   
	
		
	if(br.equals("chrome"))	
	{
		//System.setProperty(password, baseUrl)
		System.setProperty("webdriver.chrome.driver" ,readConfig.getchromePath());
		driver = new ChromeDriver();
		
	
	}
	
	else if(br.equals("firefox")){
		
		System.setProperty("webdriver.gecko.driver" ,readConfig.GetFirefoxPath());
		driver = new FirefoxDriver();
	}
	
	   // driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
	    driver.get(baseUrl);
		
	}
	
	
	@AfterClass
	public void tearDown()
	
	{ 	
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver , String testcasename) throws IOException 
	{
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+ testcasename + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}
	
	
}
