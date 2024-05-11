package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();//setting up chrome driver without requiring to install chromedriver.exe
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String prodName="ZARA COAT 3";
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		
		driver.findElement(By.id("userEmail")).sendKeys("supratikpyne28@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@8017");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(prodName))
				.findFirst().orElse(null);//java streams method to iterate all products and return required product
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();//last selection of tag type
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));//wait for added to cart element appear
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));//wait for loader disappearing
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(s->s.getText().equals(prodName));//matching any product having that name
		
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();//inputting value using actions class
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String actualMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();

	}

}
