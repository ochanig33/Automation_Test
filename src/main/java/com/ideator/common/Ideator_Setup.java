package com.ideator.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ideator.page.AdminPage;
import com.ideator.page.Homepage;
import com.ideator.page.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Ideator_Setup {
	Homepage homepage;
	LoginPage loginpage;
	AdminPage adminPage;
	ExtentReports report;
	ExtentTest logger;
	protected WebDriver driver ;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		ConfigProperties configproperties = new ConfigProperties();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String autoItPath = configproperties.getWindowAuthencation();
		String[] param = new String[] { autoItPath, "Authentication Required", "ideator", "acceptance" };
		Runtime.getRuntime().exec(param);
		driver.get(configproperties.getApplicationURL());
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void tearDown(){
		driver.close();
	}
	
}
