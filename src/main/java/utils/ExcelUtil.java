package utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.FrameworkConstants;


public class ExcelUtil {

	private static final String HashMap = null;
	private static Workbook book;
	private static Sheet sheet;

	public static LinkedHashMap<String, String> readExcelSheetUI(String sheetName, String excelSheetName) {

//      Object[][] data = null;
		LinkedHashMap<String, String> testCaseData = new LinkedHashMap<String, String>();

		try {
			FileInputStream fis = new FileInputStream(excelSheetName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			// loop through the rows
			for (int i = 0; i < rowCount; i++) {
				try {

					for (int j = 0; j < columnCount; j++) { // loop through the columns
						try {

							try {
								testCaseData.put(sheet.getRow(0).getCell(j).getStringCellValue(),
										sheet.getRow(1).getCell(j).getStringCellValue());
                           
							} catch (NullPointerException e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testCaseData;

	}
	
	
	
	
	public static String[][] readExcelSheetUIData(String sheetName, String excelSheetName) {

		String [][] data = null;
		try {
			FileInputStream fis = new FileInputStream(excelSheetName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getPhysicalNumberOfRows();//sheet.getLastRowNum();

			int columnCount = sheet.getRow(0).getLastCellNum();//sheet.getRow(0).getPhysicalNumberOfCells();
		 
			System.out.println("Row count :"+ rowCount);
			System.out.println("Column count :"+ columnCount);
			data = new String[rowCount][columnCount];

			// loop through the rows
			for (int i = 0; i < rowCount; i++) {
				try {
					for (int j = 0; j < columnCount; j++) { // loop through the columns
						try {                    
							try {
                                  DataFormatter df = new DataFormatter();
                         //        System.out.println( df.formatCellValue(sheet.getRow(i).getCell(j)));  
                             data[i][j]= df.formatCellValue(sheet.getRow(i).getCell(j));
							} catch (NullPointerException e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}
	
	


	@DataProvider(name = "getUIData", parallel = true)
	public static Object[][] getUIData(Method method) {
		String testCaseName = method.getAnnotation(Test.class).testName();
		System.out.println("Test name :: " + testCaseName);
		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_UI.replace("$ENV$", FrameworkConstants.ENVTYPE);
		LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName, env_DataFile);
		return new Object[][] { { uiData } };
	}

	@DataProvider(name = "getAPIData", parallel = true)
	public static Object[][] getAPIData(Method method) {
		String testCaseName = method.getAnnotation(Test.class).testName();
		System.out.println("Test name :: " + testCaseName);
		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_API.replace("$ENV$", FrameworkConstants.ENVTYPE);
		LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName, env_DataFile);
		return new Object[][] { { uiData } };
	}

	@DataProvider(name = "getDBData", parallel = true)
	public static Object[][] getDBData(Method method) {
		String testCaseName = method.getAnnotation(Test.class).testName();
		System.out.println("Test name :: " + testCaseName);
		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_DB.replace("$ENV$", FrameworkConstants.ENVTYPE);
		LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName, env_DataFile);
		return new Object[][] { { uiData } };
	}
	
	
	@DataProvider(name= "getData")
	public static String[][] getData(Method method) {
		String testCaseName = method.getAnnotation(Test.class).testName();
		System.out.println("Test name :: " + testCaseName);
		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_DB.replace("$ENV$", FrameworkConstants.ENVTYPE);
		String[][] array=ExcelUtil.readExcelSheetUIData(testCaseName, env_DataFile);
		return array;
	}
	
	
//	@DataProvider(name= "getDataNew")
//	public static Object[][] getDataNew(Method method) {
//		String testCaseName = method.getAnnotation(Test.class).testName();
//		System.out.println("Test name :: " + testCaseName);
//		String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_DB.replace("$ENV$", FrameworkConstants.ENVTYPE);
//		 HashMap<Integer, List<String>> map = ExcelUtil.readExcelSheetUIHashMap(testCaseName, env_DataFile);
//		//return map;
//		return new Object[][] { { map } };
//	}
	


}
