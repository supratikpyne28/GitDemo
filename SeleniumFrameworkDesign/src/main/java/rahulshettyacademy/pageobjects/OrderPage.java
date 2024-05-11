package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
		
		}
	@FindBy(css="tr td:nth-child(3)")//PageFactory model to find locators in selenium
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")//PageFactory model to find locators in selenium
	WebElement checkout;
	
	public Boolean verifyOrderDisplay(String prodName) {
		Boolean match=productNames.stream().anyMatch(s->s.getText().equals(prodName));
		return match;
	}

}
