package testCases;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import base.baseTestAPI;
import constants.FrameworkConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payloads.BooksPayload;
import reports.ExtentReportManager;
import utils.ExcelUtil;

public class APITest extends baseTestAPI {
	JsonPath js;
	String id;
	SessionFilter session;
	ResponseSpecification resp;
	RequestSpecification req;
	public static ExtentReports extentReports;
	private static Logger logger = LogManager.getLogger(APITest.class);

	@Test(testName = "TC01", priority = 1, description = "Add book api test", dataProvider = "getAPIData", dataProviderClass = ExcelUtil.class)
	public void addBookTest(Map<String, String> map) {
		ExtentReportManager.info("Started Add book api test");
		logger.info("Test started : TC01- Add book api test");
		session = new SessionFilter();
		req = new RequestSpecBuilder().setBaseUri(FrameworkConstants.baseURL)
				.addHeader("Content-Type", "application/json").build();
		resp = new ResponseSpecBuilder().expectStatusCode(Integer.parseInt(map.get("expectedStatusCode"))).build();
		RequestSpecification res = 
				given().config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Accept"))).log().all()
				.spec(req)
				.body(BooksPayload.addBookPayload());
		String response = res.filter(session).when().post(FrameworkConstants.postRequest).then().spec(resp).extract()
				.response().asString();

		js = new JsonPath(response);
		Assert.assertEquals(js.get("Msg"), map.get("successMessage"));
		id = js.get("ID");
		ExtentReportManager.info("Verified Add book api test successfully");
	}

	@Test(testName = "TC02", priority = 2, description = "Get book api test", dataProvider = "getAPIData", dataProviderClass = ExcelUtil.class)
	public void getBookByID(Map<String, String> map) {
		ExtentReportManager.info("Started Get book api test");
		logger.info("Test started : TC02- Get book api test");
		String getBookResponse = given()
				.config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Content-Type"))).log()
				.all().spec(req).queryParam("ID", id).when().get(FrameworkConstants.getBookRequest).then().spec(resp)
				.extract().response().asString();
		js = new JsonPath(getBookResponse);
		Assert.assertEquals(js.getString("book_name"), map.get("book_name"));
		ExtentReportManager.info("Verified Get book api test");
	}

	@Test(testName = "TC03", priority = 3, description = "Delete book api test", dataProvider = "getAPIData", dataProviderClass = ExcelUtil.class)
	public void DeleteBook(Map<String, String> map) {
		ExtentReportManager.info("Started Delete book api test");
		logger.info("Test started : TC03- Delete book api test");
		String deleteResponse = given()
				.config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Content-Type"))).log()
				.all().spec(req).body(BooksPayload.deleteBookPayload(id)).when()
				.post(FrameworkConstants.deleteBookRequest).then().spec(resp).extract().response().asString();
		js = new JsonPath(deleteResponse);
		Assert.assertEquals(js.get("msg"), map.get("deleteSuccessMsg"));
		ExtentReportManager.info("Verified Delete book api test");
	}

}
