package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject()
	{
		//ExtentReports, ExtentSparkReporter    both are classes and ne we need to connect them to build the report
		// this is responsible to create report and we need to provide a path to store your report		
		String path = System.getProperty("user.dir") +"\\reports\\index.html";	
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		// now we can change report default configurations
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		// extend report object is main class that is responsible to drive all your reporting execution
		ExtentReports extent = new ExtentReports();
		// you have to attach report to this main class. this main class is responsible to create and cosolidate your test execution
		extent.attachReporter(reporter);
		// if you want to mention a tester name
		extent.setSystemInfo("Tester", "Heina");
		
		return extent;
	}
}
