package rahulshettyacademy.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrder extends BaseTest{
	
	

	@Test(dataProvider = "getData",groups= {"PurchaseOrder"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalog productCatalog=landing.loginApplication(input.get("email"), input.get("password"));//logging in from landing page Model
		
		
		productCatalog.addProdToCart(input.get("prodName"));//adding selected product
		CartPage cartPage=productCatalog.goToCart();//moving to cart page
		
		
		Boolean match=cartPage.verifyProductDisplay(input.get("prodName"));
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.checkOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmation=checkoutPage.submitOrder();
		
		
		String actualMessage=confirmation.getconfirmationMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dataProvider = "getData",dependsOnMethods= {"submitOrder"})//method to test Orders Page History
	public void orderHistoryTest(HashMap<String, String> input) {
		
		ProductCatalog productCatalog=landing.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage=productCatalog.goToOrdersPage();//moving to orders page
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("prodName")));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");//converting json data to HashMap
		
		return new Object[][]{{data.get(0)},{data.get(1)}};//2D array to get HashMap data
	}
		
		
		
		
	

}
