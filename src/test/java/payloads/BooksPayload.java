package payloads;

public class BooksPayload {
	
	public static String addBookPayload() {
		return "{\r\n"
				+ "    \"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "    \"isbn\":\"bbbbbds\",\r\n"
				+ "    \"aisle\":\"227\",\r\n"
				+ "    \"author\":\"John foe\"\r\n"
				+ "}";
	}
	
	public static String deleteBookPayload(Object ID) {
		return "{\r\n"
				+ "       \"ID\": \""+ID+"\"\r\n"
				+ "}";
		
	}
	
	public static String addBookPayload(String isbn, String aisle) {
		return "{\r\n"
				+ "    \"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "    \"isbn\":\""+isbn+"\",\r\n"
				+ "    \"aisle\":\""+aisle+"\",\r\n"
				+ "    \"author\":\"John foe\"\r\n"
				+ "}";
	}


}
