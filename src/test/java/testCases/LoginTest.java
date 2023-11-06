package testCases;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.baseTest;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;
import utils.ExcelUtil;

public class LoginTest extends baseTest {
	public LoginPage objLoginPage;
	public HomePage objHomePage;
	public ExtentTest test;
	public static ExtentReports extentReports;
	public static String link = "";

	public LoginTest() {
		objLoginPage = new LoginPage();
		objHomePage = new HomePage();
	}

	@Test(testName = "TC01", priority = 1, groups = {
			"P1" }, dataProvider = "getUIData", dataProviderClass = ExcelUtil.class, description = "Place Order")
	public void placeOrder(Map<String, String> map) {
		ExtentReportManager.info("Automation Test: Place Order");
		objLoginPage.enterUserName(map.get("userName"));
		objLoginPage.enterPassword(map.get("password"));
		objLoginPage.clickLoginBtn();
		objLoginPage.verifyTitle(map.get("title"));
		objHomePage.verifyAddToCartBtnDisplayed();
		objHomePage.clickAddToCartBtn();
		objHomePage.clickCartIcon();
		objHomePage.clickCheckoutBtn();
		objHomePage.enterFirstName(map.get("firstName"));
		objHomePage.enterLastname(map.get("lastName"));
		objHomePage.enterZipCode(map.get("zipCode"));
		objHomePage.clickContinueBtn();
		objHomePage.verifyCheckoutOverviewDisplayed();
		objHomePage.clickFinishBtn();
		objHomePage.verifyOrderPlacedSuccessfully(map.get("successMessge"));
        ExtentReportManager.info("Order placed successfully");
	}

	@Test(testName = "TC02", priority = 2, groups = {"P2" }, description = "Invalid credentials test", dataProvider = "getUIData", dataProviderClass = ExcelUtil.class)
	public void loginWithInValidCredentials(Map<String, String> map) {
		ExtentReportManager.info("Automation Test: Login Sauce Demo with invalid Login Credentials");
		objLoginPage.enterUserName(map.get("userName"));

		objLoginPage.enterPassword(map.get("password"));
		objLoginPage.clickLoginBtn();

		objLoginPage.verifyErrorMessageDisplayed();
		objHomePage.verifyAddToCartBtnDisplayed();
		ExtentReportManager.info("Verified Login Sauce Demo with invalid Login Credentials");
	}

}
