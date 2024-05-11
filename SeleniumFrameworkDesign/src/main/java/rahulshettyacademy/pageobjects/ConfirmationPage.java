package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
	}
	
	@FindBy(css=".hero-primary")//PageFactory model to find auto-detect dropdown locator
	WebElement confirmation;
	
	public String getconfirmationMessage() {
		return confirmation.getText();
	}
}
