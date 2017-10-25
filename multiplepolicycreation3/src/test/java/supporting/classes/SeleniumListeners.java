package supporting.classes;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class SeleniumListeners implements ITestListener, ISuiteListener, IInvokedMethodListener{
	
	TakeScreenShots ss;

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Reporter.log("before invocation - about to execute the method : " + method.getTestMethod().getRealClass().getSimpleName() + "." + method.getTestMethod().getMethodName(),true);
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		Reporter.log("after invocation - completed executing the method : " + method.getTestMethod().getRealClass().getSimpleName() + "." + method.getTestMethod().getMethodName(),true);
		
	}

	
	public void onStart(ISuite suite) {
		Reporter.log("Onsuite start: " + suite.getName(), true);
		
	}

	
	public void onFinish(ISuite suite) {
		Reporter.log("Onsuite finish: " + suite.getName(), true);
		
	}


	public void onTestStart(ITestResult result) {
		Reporter.log("On test start before each test: " + result.getName(), true);
	}

	
	public void onTestSuccess(ITestResult result) {
		/*Object currentclass = result.getInstance();
		WebDriver driver = ((PolicyTestNGTest)currentclass).getDriver();
		ss = new TakeScreenShots(driver);
		try {
			ss.getScreenshots();
		} catch (IOException e) { 
		 		e.printStackTrace();
		}*/
		
		printTestResults(result);
		
		
	}

	
	public void onTestFailure(ITestResult result) {
		/*Object currentclass = result.getInstance();
		WebDriver driver = ((PolicyTestNGTest)currentclass).getDriver();
		ss = new TakeScreenShots(driver);
		try {
			ss.getScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		printTestResults(result);
		
	}


	public void onTestSkipped(ITestResult result) {
		Reporter.log("On test skip: " + result.getName(), true);
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	public void onStart(ITestContext context) {
		Reporter.log("OnStart of all the tests: " + context.getName(), true);
		
	}


	public void onFinish(ITestContext context) {
		Reporter.log("Onfinish of all the tests: " + context.getName(), true);
		
	}
	
	
	public void printTestResults(ITestResult result)
	{
		String status = "";
		Reporter.log("test class: " +result.getTestClass().getName(),true);
		switch(result.getStatus())
		{
		case ITestResult.SUCCESS:
			status = "Passed";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
			break;
		}
		Reporter.log("testStatus: " +status,true);
	}

	
}
