package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//video 180
// IRetryAnalyzer is an interface

public class Retry implements IRetryAnalyzer{

	private int count =0;
	private int maxTry=1;   // how many times you want to re-run it
	
	// we want whenever a test fails , it report in by extent report then it comes into this block
	// and ask us if I need to re-run again?
	// some ppl say runs 2 or 3 times to make sure it is real failure and they will get same failure result
	
	// everything related to the test that failed is inside the Result variable
	// to re-run it we have to find the method that failed and type:
	// 	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	
	@Override
	public boolean retry(ITestResult result) {      
		if(count < maxTry)
        {
        	// re-run test case again. when we say return true it will re-run the test again
        	count++;        	
        	return true;
        }
        // when it returns false, it won't re-run again
		return false;
	}
	
	
}
