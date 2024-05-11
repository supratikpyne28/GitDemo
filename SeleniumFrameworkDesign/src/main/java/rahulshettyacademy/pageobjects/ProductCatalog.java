package rahulshettyacademy.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;//initializing by using constructor
		PageFactory.initElements(driver, this);//initializing driver by page factory structure to catch 'driver'
		
		}
	
	
	@FindBy(css=".mb-3")//PageFactory model to find locators in selenium
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loading;
	
	By productsBy=By.cssSelector(".mb-3");//products selector to write wait method
	By addToCart=By.cssSelector(".card-body button:last-of-type");//add to cart selector
	By toastMessage=By.cssSelector("#toast-container");//toast container selector
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String prodName) {
		

		WebElement prod= getProductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(prodName))
				.findFirst().orElse(null);//java streams method to iterate all products and return required product
		return prod;
	}
	
	public void addProdToCart(String prodName) throws InterruptedException {
		
		WebElement prod=getProductByName(prodName);
		prod.findElement(addToCart).click();//last selection of tag type
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(loading);
	}
	
	

	

}
