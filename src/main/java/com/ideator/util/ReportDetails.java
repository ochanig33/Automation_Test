package com.ideator.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class ReportDetails {

	public static void onTestFailure(WebDriver driver,ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		System.out.println(testClassName);
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		if (driver != null) {
		CaptureScreenShot(driver, screenShotName);
		}
	}
	
	
	public static void CaptureScreenShot(WebDriver driver, String ScreeenShotName) {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./src/main/resources/Screenshot/"+ScreeenShotName+".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception While taking Screenshot"+e.getMessage());
		}
	}

	public static String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

}


