package com.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ideator.common.Ideator_Setup;
import com.ideator.page.AdminPage;
import com.ideator.page.CreateIdeaPage;
import com.ideator.page.Homepage;
import com.ideator.page.LoginPage;
import com.ideator.page.SuperAdminPage;
import com.ideator.util.ReportDetails;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class PublicUser extends Ideator_Setup {

	Homepage homepage;
	LoginPage loginPage;
	AdminPage adminPage;
	SuperAdminPage superAdminPage;
	CreateIdeaPage createIdeaPage;
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException {
		super.setup();
		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		superAdminPage = new SuperAdminPage(driver);
		createIdeaPage = new CreateIdeaPage(driver);
		report=new ExtentReports("D:\\report\\LearnAutomation.html");
	}

	@Test(priority = 1)
	// Login check
	
	public void Login() throws InterruptedException {
		
		logger=report.startTest("VerifyLogin");
		loginPage.submitLoginCredential();
		Assert.assertEquals("https://ucsdtest.ideator.com/sign_in", driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "URL verified");
		
		report.endTest(logger);
		report.flush();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus()){
			ReportDetails.onTestFailure(driver, result);
		
		}
	}

	@Test(priority = 2)
	// Create new idea
	public void createNewIdea() throws InterruptedException {
		logger=report.startTest("Verify create new idea");
		createIdeaPage.clickCreateNewIdea();
		Assert.assertEquals("https://ucsdtest.ideator.com/ideas?after_login=true", driver.getCurrentUrl());
logger.log(LogStatus.FAIL, "Error found");
		
		report.endTest(logger);
		report.flush();
	}
	
	}

/*	@Test(priority = 3)
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

	// @Test(priority = 1)
	// Login page test case.
	public void ExecuteWithoutLogin() throws InterruptedException {
		withoutLoginPage.createIdeaWithoutLogin();
		driver.close();
	}

}*/


