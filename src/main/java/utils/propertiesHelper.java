package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.envType;

public class propertiesHelper {
	static Properties prop;

	public static void loadAllProperties() {
		prop = new Properties();
		FileReader file;
		try {
			String path = getProjectDir() + "/src/test/resources/config/config.properties";
			
			file = new FileReader(path);
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseURI() {
		String url = prop.getProperty("url");
		return url;
	}

	public static String getValue(String key) {
		prop = new Properties();
		FileReader file;
	

		String path =getProjectDir()+ "/src/test/resources/config/config.properties";
		
	//	propertiesHelper.propertyLoader("/src/test/resources/config/config.properties");
		try {
			file = new FileReader(path);
			prop.load(file);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String value = prop.getProperty(key);
		return value;
	}

	public static String getProjectDir() {
		String projectPath = System.getProperty("user.dir");
		return projectPath;
	}

	public static Properties propertyLoader(String path) {
		prop = new Properties();
		try {

			FileReader file = new FileReader(path);
			try {
				prop.load(file);
			} catch (IOException e) {
				System.out.println("File not found");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return prop;
	}

	public static void loadEnvConfig() {
		String env = prop.getProperty("ENVTYPE");

		switch (envType.valueOf(env)) {
		case QA:
			propertiesHelper.propertyLoader("/src/test/resources/config/qa_config.properties");
			System.out.println("Loaded qa env properties");
			break;

		default:
			propertiesHelper.propertyLoader("/src/test/resources/config/config.properties");
			System.out.println("Loaded config properties");

		}
	}

}
