package com.map.tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //Static package for Given

import static org.hamcrest.Matchers.*; //Static package for equalTo

import org.testng.Assert;

import com.map.config.Payload;

import com.map.config.Utility;

public class PostRequest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

//Post request to add a new MAP record

//To extract response body in the variable

		String resp = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

//To pass post body data from another file.

				.body(Payload.addMapsBody()).when().post("/maps/api/place/add/json")

				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))

//.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))

//Above code to validate status code, response body having key SCOPE with value APP

				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

//Above code to validate if response header is having SERVER key with value Apache/2.4.52 (Ubuntu)

		System.out.println(resp);

// JsonPath js = new JsonPath(resp);

//To extract specific attribute value from response body

// System.out.println("****"+js.getString("place_id"));

// String placeID = js.getString("place_id");

		String placeID = Utility.getData(resp, "place_id");

//Put Request to update data based on the fetched place ID from above Post request

		String newAdd = "707 Summer walk, USA";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

				.body("{\r\n"

						+ "\"place_id\":\"" + placeID + "\",\r\n"

						+ "\"address\":\"" + newAdd + "\",\r\n"

						+ "\"key\":\"qaclick123\"\r\n"

						+ "}")
				.when().put("/maps/api/place/update/json")

				.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//Get request to validate if address is really updated or not.

		String getResponse = given().log().all().queryParam("key", "qaclick123")

				.queryParam("place_id", placeID)

				.when().get("/maps/api/place/get/json")

				.then().assertThat().statusCode(200)

				.extract().response().asString();

// JsonPath js1 = new JsonPath(getResponse);

// String updatedAdd = js1.getString("address");

		String updatedAdd = Utility.getData(getResponse, "address");

		System.out.println(updatedAdd);

		Assert.assertEquals(newAdd, updatedAdd);

	}

}

/*
 * 
 * {"status":"OK","place_id":"eb3edbfeeb538454f591f248375bf634","scope":"APP",
 * "reference":
 * "f3ede077a912b7fa5d8f028a48760dcbf3ede077a912b7fa5d8f028a48760dcb","id":
 * "f3ede077a912b7fa5d8f028a48760dcb"}
 **** 
 * eb3edbfeeb538454f591f248375bf634
 * 
 */