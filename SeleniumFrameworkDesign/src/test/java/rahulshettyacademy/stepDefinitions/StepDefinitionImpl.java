package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public ConfirmationPage confirmation;
	
	@Given("I landed on ECommerce page")
	public void I_landed_on_ECommerce_page() throws IOException {
		
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalog=landing.loginApplication(username, password);
	}
	
	@When("^I add (.+) in Cart$")
	public void i_add_product_to_cart(String prodName) throws InterruptedException {
		
		productCatalog.addProdToCart(prodName);//adding selected product
		
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String prodName) {
		
		cartPage=productCatalog.goToCart();//moving to cart page
		
		
		Boolean match=cartPage.verifyProductDisplay(prodName);
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.checkOut();
		checkoutPage.selectCountry("India");
		confirmation=checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed in ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		
		String actualMessage=confirmation.getconfirmationMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string) {
		
		Assert.assertEquals(string, landing.getErrorMessage());
		driver.close();
	}
	

}
