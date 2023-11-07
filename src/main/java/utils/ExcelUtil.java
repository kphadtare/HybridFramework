package utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

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
      LinkedHashMap<String,String> testCaseData = new LinkedHashMap<String,String>();

      try {
          FileInputStream fis = new FileInputStream(excelSheetName);
          XSSFWorkbook workbook = new XSSFWorkbook(fis);
          XSSFSheet sheet = workbook.getSheet(sheetName);
          
          // get the number of rows
          int rowCount = sheet.getLastRowNum();

          // get the number of columns
          int columnCount = sheet.getRow(0).getLastCellNum();
//          data = new Object[rowCount][columnCount];

          // loop through the rows
          for (int i = 1; i < rowCount + 1; i++) {
              try {
//                  XSSFRow row = sheet.getRow(i);
                  for (int j = 0; j < columnCount; j++) { // loop through the columns
                      try {
//                          String cellValue = "";
                          try {

//                              row.getCell(j).getCellType();
                              testCaseData.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(1).getCell(j).getStringCellValue());
//                              cellValue = row.getCell(j).getStringCellValue();
                          } catch (NullPointerException e) {
                              e.printStackTrace();
                          }

//                          data[i - 1][j] = cellValue; // add to the data array
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
	
	 @DataProvider(name = "getUIData", parallel = true)
	    public static Object[][] getUIData(Method method) {
	        String testCaseName = method.getAnnotation(Test.class).testName();
	        System.out.println("Test name :: "+testCaseName);
	        String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_UI.replace("$ENV$",FrameworkConstants.ENVTYPE);
	         LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName,env_DataFile); 
	         return new Object[][] {{uiData}};
	     }
	 
	 
	 @DataProvider(name = "getAPIData", parallel = true)
	    public static Object[][] getAPIData(Method method) {
	        String testCaseName = method.getAnnotation(Test.class).testName();
	        System.out.println("Test name :: "+testCaseName);
	        String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_API.replace("$ENV$",FrameworkConstants.ENVTYPE);
	         LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName,env_DataFile); 
	         return new Object[][] {{uiData}};
	     }
	 
	 
	 @DataProvider(name = "getDBData", parallel = true)
	    public static Object[][] getDBData(Method method) {
	        String testCaseName = method.getAnnotation(Test.class).testName();
	        System.out.println("Test name :: "+testCaseName);
	        String env_DataFile = FrameworkConstants.EXCEL_DATA_FILE_PATH_DB.replace("$ENV$",FrameworkConstants.ENVTYPE);
	         LinkedHashMap<String, String> uiData = ExcelUtil.readExcelSheetUI(testCaseName,env_DataFile); 
	         return new Object[][] {{uiData}};
	     }

}
