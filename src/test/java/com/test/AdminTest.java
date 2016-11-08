package com.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ideator.common.Ideator_Setup;
import com.ideator.page.AdminPage;
import com.ideator.page.Homepage;
import com.ideator.page.LoginPage;
import com.ideator.page.SuperAdminPage;
import com.ideator.util.ReportDetails;

public class AdminTest extends Ideator_Setup {

	Homepage homepage;
	LoginPage loginPage;
	AdminPage adminPage;
	SuperAdminPage superAdminPage;

	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		super.setup();
		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		superAdminPage = new SuperAdminPage(driver);
	}

	@Test(priority = 1)
	// Add user to community and verify that on mailinator site.
	public void Login() throws InterruptedException {
		loginPage.submitLoginCredential();
		Assert.assertEquals("https://ucsdtest.ideator.com/ideas?after_login=true", driver.getCurrentUrl());
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus()){
			ReportDetails.onTestFailure(driver, result);
		}
	}
	
	@Test(priority = 2)
	// Add user to community and verify that on mailinator site.
	public void addUserCommunity() throws InterruptedException {
		adminPage.clickAdmin();
		adminPage.addUser();
	}

	@Test(priority = 3)
	// Check when custom OnbordingON.
	public void checkCustomOnBordingON() throws InterruptedException,
			IOException {
		superAdminPage.SuperAdminCredential();
		superAdminPage.clickSuperAdmin();
		superAdminPage.onBordingONCommunity();
		adminPage.onBordingON();
	}

	
	
	@Test(priority = 4)
	// Check when custom OnbordingOFF.
	public void checkCustomOnBordingOFF() throws InterruptedException,
			IOException {
		loginPage.submitLoginCredential();
		superAdminPage.SuperAdminCredential();
		superAdminPage.clickSuperAdmin();
		superAdminPage.onBordingOFFCommunity();
		adminPage.onBordingOFF();
		driver.close();
	}

	// @Test(priority = 4)
	// Send Private message
	public void privateMessage() throws InterruptedException, IOException {
		loginPage.submitLoginCredential();
		adminPage.ClickPrivateMessage();
		adminPage.sendMessage();
		driver.close();
	}

	/*// @Test(priority = 1)
	// Login page test case.
	public void ExecuteWithoutLogin() throws InterruptedException {
		withoutLoginPage.createIdeaWithoutLogin();
		driver.close();
	}*/

}