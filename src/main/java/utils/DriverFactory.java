package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
public WebDriver initializeBrowser(String browser) {
	
		
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			//driver = new ChromeDriver(option);
			tlDriver.set(new ChromeDriver(option));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//driver= new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase("ie")) {
		//	driver = new InternetExplorerDriver();
			tlDriver.set(new InternetExplorerDriver());
		}
		return getDriver();
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}

}
