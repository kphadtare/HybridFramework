package reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import constants.FrameworkConstants;
import utils.DriverFactory;
import utils.ScreenShotHelper;

public class ExtentReportManager extends DriverFactory {

	private static ExtentReports extentReports;
	private static String link = "";
	DriverFactory driverFactory;

	public static void initReports() {
		if (Objects.isNull(extentReports)) {
			extentReports = new ExtentReports();

			link = FrameworkConstants.EXTENT_REPORT_FILE_PATH;
			System.out.println("link report:" + link);

			ExtentSparkReporter spark = new ExtentSparkReporter(link);
			extentReports.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle(FrameworkConstants.REPORT_TITLE);
			spark.config().setReportName(FrameworkConstants.REPORT_TITLE);
			extentReports.setSystemInfo("Framework Name", FrameworkConstants.REPORT_TITLE);
			extentReports.setSystemInfo("Author", FrameworkConstants.AUTHOR);

			System.out.println("Extent Reports is installed.");
		}
	}

	public static void flushReports() {
		if (Objects.nonNull(extentReports)) {
			extentReports.flush();
		}
		ExtentTestManager.unload();

	}

	public static void createTest(String testCaseName) {

		ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName));
	}

	public static void createTest(String testCaseName, String description) {
		ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName + "," + description));
	}

	public static void info(String message) {
		ExtentTestManager.getExtentTest().info(message);
	}

	public static void logMessage(Status status, String message) {
		ExtentTestManager.getExtentTest().log(status, message);
	}

	public static void passWithScreenshot(String message) {
		Media build = MediaEntityBuilder
				.createScreenCaptureFromPath(String
						.valueOf(ScreenShotHelper.takeFullPageScreenshot(driver, message.replaceAll("[^a-zA-Z]+", ""))))
				.build();

		if (FrameworkConstants.SCREENSHOT_ALL_STEPS_IN_EXTENT.equalsIgnoreCase("Yes")) {
			ExtentTestManager.getExtentTest().pass(message, build);
		} else {
			ExtentTestManager.getExtentTest().pass(message);
		}
	}

	public static void pass(String message) {

		ExtentTestManager.getExtentTest().pass(message);
	}

}
