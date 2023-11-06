package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import constants.FrameworkConstants;
import listeners.TestListeners;
import utils.DriverFactory;
import utils.propertiesHelper;

@Listeners({TestListeners.class})
public class baseTest {

	public WebDriver driver;
	DriverFactory driverFactory;
	public ExtentTest test;
	public static ExtentReports extentReports;
	public static String link = "";
	private static Logger logger = LogManager.getLogger(baseTest.class);

	@BeforeMethod
	public void launchApplication() {
		String browser = FrameworkConstants.BROWSER;
		System.out.println("Browser :: " + browser);
		logger.info("Browser :: " + browser);
		driverFactory = new DriverFactory();
		driver = driverFactory.initializeBrowser(browser);
		driver.get(propertiesHelper.getValue("URL"));
		System.out.println("Url launched");
		logger.info("Url launched :" + propertiesHelper.getValue("URL") );
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	

	


	public WebDriver getDriver() {
		return this.driver;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

}
