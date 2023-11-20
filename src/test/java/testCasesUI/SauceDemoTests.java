package testCasesUI;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import baseUI.baseTest;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;
import utils.ExcelUtil;

public class SauceDemoTests extends baseTest {
	public LoginPage objLoginPage;
	public HomePage objHomePage;
	public ExtentTest test;
	public static ExtentReports extentReports;
	public static String link = "";

	public SauceDemoTests() {
		objLoginPage = new LoginPage();
		objHomePage = new HomePage();
	}

	//This test case will verify Place Order scenario
	@Test(testName = "TC01", priority = 1, groups = {
			"P1" }, dataProvider = "getUIData", dataProviderClass = ExcelUtil.class, description = "Place Order")
	public void placeOrder(Map<String, String> map) {
		ExtentReportManager.info("Swag Labs Test :: Place Order");
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

	//This test case will verify User will get Error message when logged in with Invalid credentials
	@Test(testName = "TC02", priority = 2, groups = {"P2" }, description = "Invalid credentials test", dataProvider = "getUIData", dataProviderClass = ExcelUtil.class)
	public void loginWithInValidCredentials(Map<String, String> map) {
		ExtentReportManager.info("Swag Labs Test :: Verify Login with invalid Credentials");
		objLoginPage.enterUserName(map.get("userName"));

		objLoginPage.enterPassword(map.get("password"));
		objLoginPage.clickLoginBtn();

		objLoginPage.verifyErrorMessageDisplayed();
		//objHomePage.verifyAddToCartBtnDisplayed();
		ExtentReportManager.info("Verified user is not able to Login Sauce Demo with invalid Credentials");
	}

}
