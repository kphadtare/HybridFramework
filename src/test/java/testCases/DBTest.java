package testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DBTest {

		
		Connection con = null;
		Statement stmt;
	
		String DB_URL = "jdbc:mysql://localhost/Testdata";
		
		public static String DB_USER = "your_user";
	
		public static String DB_PASSWORD = "your_password";

		@BeforeTest
		public void setUp() throws Exception {
		try{
		
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass).newInstance();
	
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = con.createStatement();
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
		}

		@Test
		public void test() {
		try{
		String query = "select * from testingdata";

		ResultSet res = stmt.executeQuery(query);
		// Print the result untill all the records are printed
		// res.next() returns true if there is any next record else returns false
		while (res.next())
		{
		System.out.print(res.getString(1));
		System.out.print(" " + res.getString(2));
		System.out.print(" " + res.getString(3));
		System.out.println(" " + res.getString(4));
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}

		@AfterTest
		public void tearDown() throws Exception {
		// Close DB connection
		if (con != null) {
		con.close();
		}
		}

	

}
