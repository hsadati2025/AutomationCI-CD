package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

// ITestListener is an interface provided by TestNG to listen to your test results

public class Listeners extends BaseTest implements ITestListener{
	
	// inside class ExtentReporterNG, methos getReportObject is defined static we we do not need to create object of that class to access this method
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	// video 179  thread safe
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	
	
	// before running any testNG test your code control will reach this block
	@Override
	public void onTestStart(ITestResult result) {		
		// here we have to create entry for that test in the extentReport
		
		// object test is unique to your test method and is responsible to listen and report all of what happening 
		// back to the extent report      extent.createTest(testCaseName);  monitor that method and save result passed/failed in the extent report
		test = extent.createTest(result.getMethod().getMethodName());
		
		// for object test it will assign a unique thread Id of errorValidationTest
		extentTest.set(test);
	}

	
	// if any test passes then it will come to this method
	@Override
	public void onTestSuccess(ITestResult result) {
		// if test passed, in the extent resport write this message
		////test.log(Status.PASS, "Test Passed.");
	    extentTest.get().log(Status.PASS, "Test Passed.");
	}



	// this method will execute only if something/any test fails
	@Override
	public void onTestFailure(ITestResult result) {
		// if test passed, in the extent resport write this message
		//test.log(Status.FAIL, "Test Failed.");
		
		// it will write error message in the report
		/////test.fail(result.getThrowable());
		
		//// video 179  thread safe
		extentTest.get().fail(result.getThrowable());
		

		
		// Test case name
		String testCaseName= result.getMethod().getMethodName();
		// get screenshot and attach it to the report
		String filePath = null;
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			filePath = getScreenShot(testCaseName, driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		////test.addScreenCaptureFromPath(filePath, testCaseName);
		extentTest.get().addScreenCaptureFromPath(filePath, testCaseName);
	
	}

	
	
	
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	// this is the last method that is going to be executed at last
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		//it makes sure the report is going to be generated
		extent.flush();
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
}
