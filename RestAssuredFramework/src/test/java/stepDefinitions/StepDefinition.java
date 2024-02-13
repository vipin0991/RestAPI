package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.GetMap;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification r; 
	Response resp;
	TestDataBuild data = new TestDataBuild();
	static String placeId;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		
		res = given().log().all().spec(requestSpecBuild())
				.body(data.addPayloadBody(name, language, address));
		
		
		
	}

	//This method will be called for GET and Post requests
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource which you pass
		 APIResources obj = APIResources.valueOf(resource);
		 System.out.println(obj.getResource());
		
		 r = new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON).build();
		 
		 if(method.equalsIgnoreCase("POST"))
			resp = res.when().post(obj.getResource());
		 else
			 resp = res.when().get(obj.getResource());
				
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(resp.getStatusCode(),200); //import manually import static org.junit.Assert.*;
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonData(resp,key),value);
		//Created utility for below code.
	    /*String rp = resp.asString();
	    JsonPath js = new JsonPath(rp);
	    assertEquals(js.get(key).toString(),value); */
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		placeId = getJsonData(resp,"place_id");
		System.out.println(placeId);
		res = given().spec(requestSpecBuild()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource,"GET"); //will return get request response
		String actualName = getJsonData(resp,"name");//to fetch name value from get response
		System.out.println(actualName);
		assertEquals(actualName,expName);
	   
	}

	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecBuild())
				.body(data.deletePayloadBody(placeId));
	}
}
