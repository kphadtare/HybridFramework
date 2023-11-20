package baseDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import listeners.TestListenerAPI;
import reports.ExtentReportManager;
import utils.propertiesHelper;

@Listeners({ TestListenerAPI.class })
public class baseTestDB {

	Connection con = null;
	Statement stmt;
	ResultSet result = null;

	@BeforeMethod
	public void beforeMethod() {
		String DB_URL = propertiesHelper.getValue("DB_ConnectionString");
		String DB_USER = propertiesHelper.getValue("DB_USER");
		String DB_PASSWORD = propertiesHelper.getValue("DB_PASSWORD");

		try {

			String dbClass = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbClass).newInstance();

			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Statement object to send the SQL statement to the Database

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() throws SQLException {
		con.close();
	}

	public ResultSet executeSelectQuery(String query) throws SQLException {
		stmt = con.createStatement();
		result = stmt.executeQuery(query);
		System.out.println("Query executed " + query);
		ExtentReportManager.info("Query :: " + query + " is executed successfully");
		return result;

	}
	
	public void verifyPatientID(int patientIDActual, int expectedID) {
		Assert.assertEquals(patientIDActual, expectedID);
		ExtentReportManager.pass("Verified patient ID is:"+ expectedID);
	}
	
	public void verifyPatientAge(String patientAgeActual, String expectedAge) {
		Assert.assertEquals(patientAgeActual, expectedAge);
		ExtentReportManager.pass("Verified patient age is:"+ expectedAge);
	}
	
	public void verifyPatientEmail(String patientEmailActual, String expectedEmail) {
		Assert.assertEquals(patientEmailActual, expectedEmail);
		ExtentReportManager.pass("Verified patient email is:"+ expectedEmail);
	}
}
