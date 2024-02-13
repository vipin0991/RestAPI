package serialize;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		GetMap p = new GetMap();
		p.setAccuracy(50);
		p.setAddress("indore MP");
		p.setName("sample name 01");
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
		
		
		
		String res = given().log().all().queryParam("key", "qaclick123")
				.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().extract().response().asString();
		
		System.out.println(res);
	}

}
