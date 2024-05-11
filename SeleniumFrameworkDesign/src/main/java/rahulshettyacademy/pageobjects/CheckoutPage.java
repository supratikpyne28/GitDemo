package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
	}
	
	@FindBy(css="[placeholder='Select Country']")//PageFactory model to find auto-detect dropdown locator
	private WebElement countryDropDown;
	
	@FindBy(css=".ta-item:nth-of-type(2)")//PageFactory model to find auto-detect dropdown locator
	private WebElement country;
	
	@FindBy(css=".action__submit")//PageFactory model to find auto-detect dropdown locator
	private WebElement submitBtn;
	
	private By results=By.className("ta-results");
	
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(countryDropDown, countryName).build().perform();//inputting value using actions class
		waitForElementToAppear(results);
		country.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}
	

}
