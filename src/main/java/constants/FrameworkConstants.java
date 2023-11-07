package constants;

import utils.propertiesHelper;

public final class FrameworkConstants {

	private FrameworkConstants() {

	}

	public static final String BROWSER = propertiesHelper.getValue("BROWSER");
	public static final String PROJECT_PATH = propertiesHelper.getProjectDir();

	public static final String EXCEL_DATA_FILE_PATH_UI = propertiesHelper.getValue("EXCEL_DATA_FILE_PATH_UI");
	public static final String EXCEL_DATA_FILE_PATH_API = propertiesHelper.getValue("EXCEL_DATA_FILE_PATH_API");
	public static final String EXCEL_DATA_FILE_PATH_DB = propertiesHelper.getValue("EXCEL_DATA_FILE_PATH_DB");
	public static final String API_LOG_FILE_PATH = propertiesHelper.getValue("API_Log_PATH");
	
	public static final String ENVTYPE = propertiesHelper.getValue("ENVTYPE");

	public static final String EXTENT_REPORT_FOLDER = propertiesHelper.getValue("EXTENT_REPORT_FOLDER");

	public static final String EXTENT_REPORT_NAME = propertiesHelper.getValue("EXTENT_REPORT_NAME");
	public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
	public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
	public static String EXTENT_REPORT_FILE_PATH = PROJECT_PATH + propertiesHelper.getValue("EXTENT_REPORT_FILE_PATH");
	public static final String REPORT_TITLE = propertiesHelper.getValue("REPORT_TITLE");
	public static final String AUTHOR = propertiesHelper.getValue("AUTHOR");

	public static final String SCREENSHOT_ALL_STEPS_IN_EXTENT = propertiesHelper
			.getValue("screenshot_all_steps_in_extent");
	
	
	//API
	public static final String baseURL= "http://216.10.245.166";
	public static final String postRequest="Library/Addbook.php";
	public static final String getBookRequest= "Library/GetBook.php";
	public static final String deleteBookRequest= "Library/DeleteBook.php";
}
