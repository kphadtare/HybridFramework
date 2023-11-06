package listeners;

import java.util.ArrayList;

import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import reports.ExtentReportManager;
import utils.Log;
import utils.propertiesHelper;

public class TestListenerAPI implements ITestListener, ISuiteListener, IInvokedMethodListener ,IExecutionListener {
	
	ArrayList<Object[]> result;
	static int count_totalTCs;

	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	
	public TestListenerAPI() {
		
	}
	
	public void onStart(ISuite suite) {
		Log.info("Suite started: " + suite.getName());
		System.out.println("");
		System.out.println("========= Installing testData==============");
		propertiesHelper.loadAllProperties();
		propertiesHelper.loadEnvConfig();
		ExtentReportManager.initReports();
		result = new ArrayList<Object[]>();
		result.add(new String[] { "Testcase Name", "Testcase Description", "Testcase Status", "Screenshot Path" });
		System.out.println("============ Installed testData===============");
		System.out.println("Suite Name : " + suite.getName());
	}

	public String getTestName(ITestResult result) {
		return result.getTestName() != null ? result.getTestName()
				: result.getMethod().getConstructorOrMethod().getName();
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println(iTestResult.getName() + " test case started");
		count_totalTCs = count_totalTCs + 1;
		Log.info(iTestResult.getName() + " test case started");
		ExtentReportManager.createTest(iTestResult.getName());
		System.out.println("Description :" + iTestResult.getMethod().getDescription());

	}

	public void onTestSuccess(ITestResult iTestResult) {
		count_passedTCs = count_passedTCs + 1;
		ExtentReportManager.info("Test case: " + getTestName(iTestResult) + " is passed.");
		ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " is passed.");
	}

	public void onTestFailure(ITestResult iTestResult) {
		ExtentReportManager.info("Test case: " + getTestName(iTestResult) + " is failed.");
		Log.error("Test case: " + getTestName(iTestResult) + " is failed.");
		count_failedTCs = count_failedTCs + 1;
		ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());

	}

	public void onFinish(ISuite suite) {
		Log.info("End Suite: " + suite.getName());
		System.out.println("End Suite: " + suite.getName());
		ExtentReportManager.flushReports();
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Before every method in the Test Class
		// System.out.println(method.getTestMethod().getMethodName());
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// After every method in the Test Class
		// System.out.println(method.getTestMethod().getMethodName());
	}

}
