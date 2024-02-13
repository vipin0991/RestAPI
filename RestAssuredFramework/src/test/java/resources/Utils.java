package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	
	public RequestSpecification requestSpecBuild() throws IOException {
		if(req==null) {
		PrintStream log = new PrintStream(new File("logging.txt"));
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalProp("baseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalProp(String k) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(new File("../RestAssuredFramework/src/test/java/resources/global.properties"));
		p.load(fis);
		return p.getProperty(k);
	}
	
	public String getJsonData(Response resp, String key) {
		String response = resp.asString();
		JsonPath js = new JsonPath(response);
		return js.get(key).toString(); //to return string representation of object
		
	}
	
	
	
}
