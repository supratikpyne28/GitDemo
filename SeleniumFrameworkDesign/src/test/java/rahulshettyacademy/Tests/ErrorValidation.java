package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"errorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		landing.loginApplication("supratikpyne28@outlook.com", "Selenium@8017");//logging in from landing page Model
		Assert.assertEquals("Incorrect email or password.", landing.getErrorMessage());//validating error message for incorrect login
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String prodName="ZARA COAT 3";
		ProductCatalog productCatalog=landing.loginApplication("supratikpyne28@gmail.com", "Selenium@8017");//logging in from landing page Model
		
		
		productCatalog.addProdToCart(prodName);//adding selected product
		CartPage cartPage=productCatalog.goToCart();//moving to cart page
		
		
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		
		Assert.assertFalse(match);
	}
		
		
		
		
	

}
