package testCasesDB;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.FrameworkConstants;

public class excelUtilsData {

	public static ArrayList<patientDetails> readExcelSheetUIHashMap(String sheetName, String excelSheetName) {
		ArrayList<patientDetails> list = new ArrayList<patientDetails>();

		try {
			FileInputStream fis = new FileInputStream(excelSheetName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum();

			// loop through the rows
			for (int i = 1; i < rowCount; i++) {

				patientDetails details = new patientDetails();
				try {

					DataFormatter formatter = new DataFormatter();
					details.setPatient_id(formatter.formatCellValue(sheet.getRow(i).getCell(1)));
					details.setFrist_name(formatter.formatCellValue(sheet.getRow(i).getCell(2)));
					details.setAge(formatter.formatCellValue(sheet.getRow(i).getCell(3)));
					details.setEmail(formatter.formatCellValue(sheet.getRow(i).getCell(4)));

					list.add(details);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@DataProvider(name = "getDataNew")
	public static Object[] getDataNew(Method method) {
		String testCaseName = method.getAnnotation(Test.class).testName();
		System.out.println("Test name :: " + testCaseName);
		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_DB.replace("$ENV$", FrameworkConstants.ENVTYPE);
		ArrayList<patientDetails> map = excelUtilsData.readExcelSheetUIHashMap(testCaseName, env_DataFile);
		return new Object[] { map };
	}

}
