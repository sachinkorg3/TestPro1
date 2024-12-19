package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification  req;

	/*
	 * ResponseSpecification responsesepc; RequestSpecification res; Response
	 * response;
	 */
	public static RequestSpecification requestSpecification  () throws IOException
	{
		if(req==null)
		{
		PrintStream streamLog = new  PrintStream(new FileOutputStream("Loggin.txt"));
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		req=	new RequestSpecBuilder().setBaseUri(getGolbalValue("baseURL")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(streamLog))
		.addFilter(ResponseLoggingFilter.logResponseTo(streamLog))
			   .setContentType(ContentType.JSON).build();
		 return req;
		}

		 return req;
	}
	public static  String   getGolbalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream file1 = new FileInputStream("E:\\Selenium\\RestAPIBDDFramewok\\src\\test\\java\\resources\\global.properties");
		prop.load(file1);
		return prop.getProperty(key);
	  
	}
	
	public String getJsonpath(Response response ,String key)
	{
		
		String resp =response.asString();
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
		
		
		
		
	}
}
