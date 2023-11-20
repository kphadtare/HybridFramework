package testCasesDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseDB.baseTestDB;
import reports.ExtentReportManager;
import utils.ExcelUtil;

public class DBTest extends baseTestDB {

		
	    //This Test case will verify total count of data in patients_data table
		@Test(testName="TC01", description = "Verify count of Patients data in DB", dataProvider="getDBData",dataProviderClass = ExcelUtil.class)
		public void verifyCountOfPatientsData(Map<String, String> map) throws SQLException {
			
			ExtentReportManager.info("DB Test :: Verify count of Patients data");
			ResultSet result = executeSelectQuery(map.get("quertToGetCount"));
			while(result.next()) {
				int count =result.getInt("count(*)");
				if(count==1000) {
					Assert.assertTrue(true, "Successfully Verified count of patients data");
					ExtentReportManager.info("Verified count of patients data is :"+ count);
				}
				else {
					Assert.fail("Count don't match");
					ExtentReportManager.info("Count of patients data is :"+ count);
				}
			}
		
		}
		
		//This test case will verify particular patient details like patient_id, age, email address
		//This test case will get Failed as expected and actual ID of Patient will not match
		
		@Test(testName="TC02", description ="Verify Patient details in DB", dataProvider = "getDBData", dataProviderClass = ExcelUtil.class)
		public void verifyPatientDetails(Map<String,String>map) throws SQLException {
			
			ExtentReportManager.info("DB Test :: Verify Patient details in DB");
			ResultSet result = executeSelectQuery(map.get("queryToFetchPatientDetails"));
			while(result.next()) {
				int patientActualID =result.getInt("patient_id");
				verifyPatientID(patientActualID, Integer.parseInt(map.get("expectedID")));
				String actualAge = result.getString("age");
				verifyPatientAge(actualAge, map.get("expectedAge"));
				String email = result.getString("email");
				verifyPatientEmail(email, map.get("email"));
				}
			}
			
		

		

	

}
