package utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import constants.FrameworkConstants;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShotHelper {
	static DriverFactory driverFactory;
	
	
	public ScreenShotHelper() {
		driverFactory = new DriverFactory();
	}
	public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
	
	public static File getScreenshot(String screenshotName) {
		Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(allScreenBounds);
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		String path = getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		//	Log.info("Folder created: " + folder);
		}
		String filePath = path + File.separator + screenshotName + dateName + ".png";
		File file = new File(filePath);
		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}
	
	
	public static File getScreenshotAs(WebDriver driver,String screenshotName) {
	//	WebDriver driver = driverFactory.getDriver();
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		TakesScreenshot srcshot = (TakesScreenshot) driver;
		File file = srcshot.getScreenshotAs(OutputType.FILE);
		String path = getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";
		String filePath = path + File.separator + screenshotName + dateName + ".png";
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFile;
	}
	

	public static String takeFullPageScreenshot(WebDriver driver, String screenshotName)  {
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		String path = getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";
		String filePath = path + File.separator + screenshotName + dateName + ".png";
		   Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	       try {
			ImageIO.write(s.getImage(),"PNG",new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       File destFile=new File(filePath);
	       System.out.println("file path is:"+filePath);
		return destFile.getAbsolutePath();
		
	   
		}

}
