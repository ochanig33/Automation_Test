package com.ideator.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class AuthencationAutoIt {
	
	@Test
	 public void test() throws IOException, InterruptedException {
	  String [] param=new String[]{"C:\\Users\\saurabhdh\\Desktop\\Ideator Tickets\\AutoIt\\AuthencationHandler - Copy.exe","Authentication Required","ideator","acceptance"};
	  Runtime.getRuntime().exec(param);
	  Thread.sleep(2000);
	  //System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	  WebDriver driver =new FirefoxDriver();
	     driver.manage().window().maximize();
	     driver.get("http://ucsdtest.ideator.com");
	 
	 }
}
