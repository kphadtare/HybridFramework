package testCasesDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import baseDB.baseTestDB;
import reports.ExtentReportManager;
import utils.ExcelUtil;

public class DBTest extends baseTestDB {
	List<String> list;
	HashMap<Integer, List<String>> dataMap;

		
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
				verifyPatientID(patientActualID, Integer.parseInt(map.get("patientID")));
				String actualAge = result.getString("age");
				verifyPatientAge(actualAge, map.get("expectedAge"));
				String email = result.getString("email");
				verifyPatientEmail(email, map.get("email"));
				}
			}
			
		//This test will execute for set of data and compare data
		@Test(testName="TC03", description ="Verify Patient details in DB", dataProvider = "getData", dataProviderClass = ExcelUtil.class)
		public void verifyMultiplePatientDetails(String query, String patientId, String age, String emailID) throws SQLException {
			
			ExtentReportManager.info("DB Test :: Verify Patient details in DB");
			ResultSet result = executeSelectQuery(query);
			while(result.next()) {
				int patientActualID =result.getInt("patient_id");
				verifyPatientID(patientActualID, Integer.parseInt(patientId));
				String actualAge = result.getString("age");
				verifyPatientAge(actualAge, age);
				String email = result.getString("email");
				verifyPatientEmail(email, emailID);
				}
			}
			
		
		
			@Test(testName = "TC04", description = "Verify Patient details in DB", dataProvider = "getDataNew", dataProviderClass = excelUtilsData.class)
			public void comparePatientDetailsFromDBWithReport(ArrayList<patientDetails> excelData) throws SQLException {
				ExtentReportManager.info("DB Test :: Verify Patient details in DB");
				ResultSet result = executeSelectQuery(
						"SELECT patient_id, first_name,age,email FROM sys.patients_data limit 10;");

				List<patientDetails> dbDataList = new ArrayList<patientDetails>();
				while (result.next()) {
					patientDetails details = new patientDetails();
					details.setPatient_id(result.getString("patient_id"));
					details.setFrist_name(result.getString("first_name"));
					details.setAge(result.getString("age"));
					details.setEmail(result.getString("email"));
					dbDataList.add(details);
				}
				for (patientDetails p : dbDataList) {
					System.out.println(p);
					if(excelData.contains(p)) {
						Assert.assertTrue(excelData.contains(p)," Data matched :"+ p);
					} else {
						Optional<patientDetails> opd = excelData.stream()
								                       .filter(e -> e.getPatient_id().equals(p.getPatient_id()))
								                       .findFirst();

						Assert.assertTrue(excelData.contains(p)," Data not matched :"+ " Expected :"+ p + " Actual :"+ opd.get() );
					}
					
					ExtentReportManager.pass("Verified data matched :" + p + " ,present in DB data" );
				}
			}

	

}
