package com.ideator.page;

import org.openqa.selenium.WebDriver;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.IdeatorPage;
import com.ideator.common.WorkBookRead;

public class LoginPage extends IdeatorPage {

	WebDriver driver;

	@FindBy(id = "user-email")
	WebElement userEmail;

	@FindBy(id = "user-password")
	WebElement userPass;

	@FindBy(xpath = ".//input[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement loginButton;

	@FindBy(id = "user-first-name")
	WebElement userFirstName;

	@FindBy(id = "user-last-name")
	WebElement userLastName;

	@FindBy(css = "input[name=commit]")
	WebElement submitSignupButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void submitLoginCredential() {
		WorkBookRead wrb = new WorkBookRead();
		String[] loginData;
		try {
			loginData = wrb.ReadsheetLogin();
			String userName = loginData[0];
			String passWord = loginData[1];
			loginButton.click();
			userEmail.sendKeys(userName);
			userPass.sendKeys(passWord);
			submitButton.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void submitSignUpCredential(String signupUserEmail) throws InterruptedException {
		setText(userFirstName, "First1");
		// userFirstName.clear();
		// userFirstName.sendKeys("First");
		userLastName.clear();
		userLastName.sendKeys("Last1");
		userEmail.sendKeys(signupUserEmail);
		userPass.sendKeys("cybage@123");
		submitSignupButton.click();
		Thread.sleep(5000);
	}

}
