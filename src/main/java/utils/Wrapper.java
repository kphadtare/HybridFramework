package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;







public class Wrapper {
	DriverFactory driverFactory;

	public Wrapper() {
		driverFactory = new DriverFactory();

	}

	public void staticWait() {
		driverFactory.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public static void sleep(double second) {
		try {
			Thread.sleep((long) (second * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickWebElement(By locator) {
		try {
			staticWait();
			driverFactory.getDriver().findElement(locator).click();
		} catch (Exception e) {
			System.out.println("Failed to click :" + e.getMessage());
			Log.error("Failed to click on element :" + locator + " " + e.getMessage());
		}
	}

	public void setText(By locator, String value) {
		try {
			staticWait();
			driverFactory.getDriver().findElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Failed to setText :" + e.getMessage());
			Log.error("Failed to setText value :" + value + "in " + locator + " " + e.getMessage());
		}
	}

	public String getText(By locator) {
		try {
			staticWait();
			String text = driverFactory.getDriver().findElement(locator).getText();
			return text;
		} catch (Exception e) {
			System.out.println("Failed to setText :" + e.getMessage());
		}
		return "";
	}

	public String getTitle() {
		staticWait();
		return driverFactory.getDriver().getTitle();
	}

	public boolean isDisplayed(By locator) {
		try {
           staticWait();
			return driverFactory.getDriver().findElement(locator).isDisplayed();
		} catch (Exception e) {
			e.getMessage();
			Log.error("Element is not displayed :" + locator);
			return false;
		}

	}
	
//	public void extractJSONData(String response) {
//		JsonPath js = new JsonPath(response);
//		return js;
//	}
	
}
