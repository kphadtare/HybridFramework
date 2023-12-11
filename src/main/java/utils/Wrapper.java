package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Wrapper extends DriverFactory {
	DriverFactory driverFactory;

	public void staticWait() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println("Failed to click :" + e.getMessage());
			Log.error("Failed to click on element :" + locator + " " + e.getMessage());
		}
	}

	public void setText(By locator, String value) {
		try {
			staticWait();
			driver.findElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Failed to setText :" + e.getMessage());
			Log.error("Failed to setText value :" + value + "in " + locator + " " + e.getMessage());
		}
	}

	public String getText(By locator) {
		try {
			staticWait();
			String text = driver.findElement(locator).getText();
			return text;
		} catch (Exception e) {
			System.out.println("Failed to setText :" + e.getMessage());
		}
		return "";
	}

	public String getTitle() {
		staticWait();
		return driver.getTitle();
	}

	public boolean isDisplayed(By locator) {
		try {
			staticWait();
			return driver.findElement(locator).isDisplayed();
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
