package com.ideator.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AdminPage {
	WebDriver driver;

	@FindBy(css = ".user__image-small>img")
	WebElement avtar;
	@FindBy(xpath = "//button[text()='+ Add User']")
	WebElement addUser;

	@FindBy(xpath = "//input[@type='text']")
	WebElement invitationTextbox;

	@FindBy(xpath = "//a[contains(text(),'Send Invites')]")
	WebElement sendInvite;

	@FindBy(xpath = ".//*[text()='Close']")
	WebElement close;

	@FindBy(xpath = "//a[contains(text(),'Create new idea')]")
	WebElement newIdeaButton;

	@FindBy(xpath = ".//label[contains(@class, 'ng-binding')]")
	WebElement onbordingField;
	
	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// Click Admin Button
	public void clickAdmin() {
		avtar.click();
		List<WebElement> elementList = driver.findElements(By.cssSelector(".user-nav__link"));
		for (WebElement element : elementList) {
			if ((element.getText()).equals("Admin")) {
				element.click();
				break;
			}
		}
	}

	public void ClickPrivateMessage() {
		avtar.click();
		List<WebElement> elementList = driver.findElements(By.cssSelector(".user-nav__link"));
		for (WebElement element : elementList) {
			if ((element.getText()).equals("Private Messages")) {
				element.click();
				break;
			}
		}
	}
	
	public void sendMessage() {
		List<WebElement> elements = driver.findElements(By.xpath("//div[2]/h3"));
		for (WebElement element : elements) {
			if (element.getText().contains("vip@mailinator.com")) {
				element.click();
				break;
			}
			
		}
		WebElement abc = driver.findElement(By.xpath("//div[2]/div[2]/private_messages-feed/div"));
		WebElement temp = abc.findElement(By.xpath("//textarea[contains(@class, 'ng-pristine ng-untouched ng-valid')]"));
		temp.sendKeys("hi");
	}
	
	
	// Add user(Send Invite) & check whether invite mail send to user's inbox or not  Here we use mailinator Global mailbox for Testing
	public void addUser() throws InterruptedException {
		addUser.click();
		String userEmail = "adsfg@mailinator.com";
		int endIndex=userEmail.indexOf("@");
		String UserName = userEmail.substring(0, endIndex);
		invitationTextbox.sendKeys(userEmail);
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.ENTER);
//		sendInvite.click();
		sendInvite.click();
		Thread.sleep(3000);
		close.click();
		driver.navigate().refresh();
		clickSignOut();
		Thread.sleep(2000);
		Mailinator mailinator = new Mailinator(driver);
		mailinator.openMailinatorAndVerifyMail(UserName,userEmail);
		clickAdmin();
		Assert.assertTrue(isUserAdded(userEmail));
	}
	
	private void clickSignOut() {
		avtar.click();
		List<WebElement> elementList = driver.findElements(By.cssSelector(".user-nav__link"));
		for (WebElement element : elementList) {
			if ((element.getText()).equals("Sign Out")) {
				element.click();
				break;
			}
		}
	}
	
	public boolean isUserAdded(String userEmail) throws InterruptedException {
		if (isElementExist(By.xpath("//a[contains(text(),'Load More')]"))) {
			clickLoadMoreButton();
		}
		List<WebElement> userInfos = driver.findElements(By.xpath("//div/ui-view"));
		for (WebElement userInfo : userInfos) {
			if (userInfo.getText().contains(userEmail)) {
				return true;
			}
		}
		return false;
	}

	private boolean isElementExist(By xpath) {
			try {
				driver.findElement(xpath);
			} catch (NoSuchElementException ex) {
				return false;
			}
			return true;
		}

	private void clickLoadMoreButton() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Load More')]")).click();
		Thread.sleep(2000);
		if (isElementExist(By.xpath("//a[contains(text(),'Load More')]"))) {
			clickLoadMoreButton();
		}
	}
	

	public void onBordingON() {
		newIdeaButton.click();
		Assert.assertTrue(isLocationPresent() && isCategoryPresent());
	}
	public void onBordingOFF() {
		newIdeaButton.click();
		Assert.assertFalse(isLocationPresent() && isCategoryPresent());
	}

	private boolean isLocationPresent() {
		List<WebElement> field1 = driver.findElements(By.xpath(".//label[contains(@class, 'ng-binding')]"));
		for (WebElement field : field1) {
			if (field.getAttribute("for").equals("idea_location")) {
				return true;
			}
		}
		return false;
	}

		private boolean isCategoryPresent() {
			List<WebElement> field1 = driver.findElements(By.xpath(".//label[contains(@class, 'ng-binding')]"));
			for (WebElement field : field1) {
				if (field.getAttribute("for").equals("idea_category")) {
					return true;
				}
			}
			return false;
		}
}