package basic;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelDrivenTest {
	
	@Test
	public void addBook() throws IOException {
		
		DataDrivenTest d = new DataDrivenTest();
		ArrayList al = d.getData("RestAssured","RestAddBook");
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
 		hm.put("name",al.get(1));
		hm.put("isbn",al.get(2));
		hm.put("aisle",al.get(3));
		hm.put("author",al.get(4));
		
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = given().header("Content-Type", "application/json")
		.body(hm)
		.when().post("/Library/Addbook.php")
		.then().extract().response().asString();
		JsonPath js = new JsonPath(resp);
		System.out.println(js.get("ID"));
		
	}

}
