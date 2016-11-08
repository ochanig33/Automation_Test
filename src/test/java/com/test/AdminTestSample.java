package com.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ideator.common.ConfigProperties;
import com.ideator.common.Ideator_Setup;
import com.ideator.page.AdminPage;
import com.ideator.page.LoginPage;

public class AdminTestSample extends Ideator_Setup {
	WebDriver driver;

	@BeforeMethod
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

	
	@Test(priority = 1)
	// Add user to community and verify that on mailinator site.
	public void addUserCommunity() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		AdminPage adminPage = new AdminPage(driver);
		loginPage.submitLoginCredential();
		adminPage.clickAdmin();
		adminPage.addUser();
	}
}
