package serialize;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {
	
	public static void main(String[] args) {
		
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		GetMap p = new GetMap();
		p.setAccuracy(50);
		p.setAddress("indore MP1");
		p.setName("sample name 011");
		p.setPhone_number("919807890000");
		p.setWebsite("http://www.samplesite.com");
		p.setLanguage("French-IN");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe Park");
		myList.add("shopping");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();
		
		/*String res = given().log().all().queryParam("key", "qaclick123")
				.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().extract().response().asString();*/
		
		RequestSpecification res = given().log().all().spec(req)
				.body(p);
		
		ResponseSpecification r = new ResponseSpecBuilder().expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		
		String resp = res.when().post("/maps/api/place/add/json")
		.then().spec(r).extract().response().asString();
		
		
		System.out.println(resp);
	}

}
