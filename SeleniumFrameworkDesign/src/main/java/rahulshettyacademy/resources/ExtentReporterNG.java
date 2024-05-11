package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";//report path
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);//method to create report
		reporter.config().setReportName("Web Automation Results");//method to set report name
		reporter.config().setDocumentTitle("Test Results");//method for title name
		
		ExtentReports report =new ExtentReports();//initialize reporting variable
		report.attachReporter(reporter);//attach reporter to main class
		report.setSystemInfo("Tester", "Supratik Pyne");//tester key-value pair info
		return report;
	}

}
