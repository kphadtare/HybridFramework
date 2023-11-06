package base;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import listeners.TestListenerAPI;

@Listeners({TestListenerAPI.class})
public class baseTestAPI {
	

	private static Logger logger = LogManager.getLogger(baseTestAPI.class);
	
	
	@BeforeMethod
	public void beforeMethod() {
	  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	
	@AfterMethod
	public void afterMethode(ITestResult result) {
		if(result.getStatus()== ITestResult.FAILURE) {
			Throwable t = result.getThrowable();
			StringWriter error = new StringWriter();
			t.printStackTrace(new PrintWriter(error));
			logger.info(error.toString());
		}
	}
	
	
	
}
