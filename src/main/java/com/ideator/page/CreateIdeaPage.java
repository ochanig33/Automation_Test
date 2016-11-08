package com.ideator.page;

	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
	public class CreateIdeaPage {
	    WebDriver driver;
	    @FindBy(xpath="//a[contains(text(),'Create new idea')]")
	    WebElement createNewIdea;
	    
	    @FindBy(id="idea_name")
	    WebElement ideaName;
	  
	    @FindBy(xpath=".//*[@id='new_idea']/div[3]/div/select")
	    WebElement category;
	    
	    @FindBy(id="location")
	    WebElement location;
	    
	    @FindBy(id="idea_description")
	    WebElement elevatorPitch;
	    
	    @FindBy(name="commit")
	    WebElement submitButton;
	    
	    public CreateIdeaPage(WebDriver driver){
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	        public void clickCreateNewIdea(){
	        	createNewIdea.click();
	        	ideaName.sendKeys("Selenium123");
	        	Select dropdown = new Select(category);
	        	dropdown.selectByVisibleText("Category 1");
	        	location.sendKeys("Pune, Maharashtra, India");
	        	elevatorPitch.sendKeys("Test");
	        	submitButton.click();
	        }

	}

