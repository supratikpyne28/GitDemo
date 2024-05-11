package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
		
		}
	
	//WebElement email=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")//PageFactory model to find locators in selenium
	WebElement email;
	
	@FindBy(id="userPassword")//PageFactory model to find locators in selenium
	WebElement password;
	
	@FindBy(id="login")//PageFactory model to find locators in selenium
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")//PageFactory model to find error message locator
	WebElement errorMessage;
	
	//actions method to login to the application
	public ProductCatalog loginApplication(String emailId, String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		loginBtn.click();
		ProductCatalog productCatalog=new ProductCatalog(driver);
		return productCatalog;
	}
	
	//public method to catch error
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	//public method to reach landing page
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	

}
