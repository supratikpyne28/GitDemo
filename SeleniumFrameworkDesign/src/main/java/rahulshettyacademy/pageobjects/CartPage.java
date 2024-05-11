package rahulshettyacademy.pageobjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
		
		}
	
	//WebElement email=driver.findElement(By.id("userEmail"));
	@FindBy(css=".cartSection h3")//PageFactory model to find locators in selenium
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")//PageFactory model to find locators in selenium
	private WebElement checkout;
	
	
	
	//actions method to login to the application
	public Boolean verifyProductDisplay(String prodName) {
		Boolean match=cartProducts.stream().anyMatch(s->s.getText().equals(prodName));
		return match;
	}
	//public method to reach checkout page
	public CheckoutPage checkOut() {
		checkout.click();
		return new CheckoutPage(driver);
	}
	
	

}
