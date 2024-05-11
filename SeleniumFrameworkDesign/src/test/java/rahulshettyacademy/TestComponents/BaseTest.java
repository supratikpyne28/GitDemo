package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landing=new LandingPage(driver);//object of Landing Page class
	public WebDriver initializeDriver() throws IOException {
		
		Properties properties=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+
				"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		properties.load(file);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");//ternary operation to get browser name
		//String browserName=properties.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();//class to call headless method
			WebDriverManager.chromedriver().setup();//setting up chrome driver without requiring to install chromedriver.exe
			if(browserName.contains("headless")) {
				options.addArguments("headless");//headless argument
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//Firefox code
			WebDriverManager.firefoxdriver().setup();//setting up firefox driver without requiring to install chromedriver.exe
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//MS edge code
			WebDriverManager.edgedriver().setup();//setting up edge driver without requiring to install chromedriver.exe
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read JSON File to String
		String jsonData=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);//new methos accepts file location to convert and conversion type
		
		//convert String to HashMap using Jackson binder
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
			
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		landing=new LandingPage(driver);
		landing.goTo();
		return landing;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;//cast driver to take screenshot type
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");//destination of ss
		FileUtils.copyFile(source, dest);//putting ss in project path
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

}
