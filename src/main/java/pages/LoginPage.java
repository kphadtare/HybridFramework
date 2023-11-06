package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;

import reports.ExtentReportManager;
import utils.Wrapper;

public class LoginPage {
	Wrapper objWrapper;
	public ExtentTest test;
	private static Logger logger = LogManager.getLogger(LoginPage.class);

	
	By inpUserName= By.id("user-name");
	By inpPassword = By.id("password");
	By btnLogin = By.id("login-button");
	
	By locErrorMsg = By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']");
	
	public LoginPage() {
		 objWrapper = new Wrapper();
	}
	
	public void enterUserName(String userName) {
		objWrapper.sleep(1);
		
		objWrapper.setText(inpUserName,  userName);
		ExtentReportManager.passWithScreenshot("Enterted userName :"+ userName);
		logger.info("Username entered :"+ userName);
		
	}
	
	public void enterPassword(String password) {
		objWrapper.sleep(1);
		objWrapper.setText(inpPassword,  password);
		logger.info("Password entered :"+ password);
		ExtentReportManager.passWithScreenshot("Enterted password :"+ password);
	}
	
	public void clickLoginBtn() {
		objWrapper.sleep(1);
		objWrapper.clickWebElement(btnLogin);
		ExtentReportManager.passWithScreenshot("Clicked on Login button ");
		logger.info("Clicked on Login button");
	}
	
	public void verifyTitle(String expectedTitle) {
		objWrapper.sleep(1);
		String actualTitle = objWrapper.getTitle();
		System.out.println("Actual Title is :"+ actualTitle);
		System.out.println("Expected title is :"+ expectedTitle);
		assertEquals(actualTitle, expectedTitle);
		ExtentReportManager.passWithScreenshot("Verified title is :"+ expectedTitle);
		logger.info("Verified title is "+ expectedTitle);
	}
	
	public void verifyErrorMessageDisplayed() {
		boolean result = objWrapper.isDisplayed(locErrorMsg);
		assertTrue(result, "Verified error message displayed");
		ExtentReportManager.passWithScreenshot("Verified error message displayed");
		logger.info("Verified error message displayed");
	}
	
	
	

}
