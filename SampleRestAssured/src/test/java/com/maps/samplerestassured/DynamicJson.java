package com.maps.samplerestassured;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maps.files.Payload;
import com.maps.files.Utility;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	//To Add book records
	@Test(dataProvider="getData")
	public void addBook(String isb, String ais) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().log().all().body(Payload.addBookBody(isb,ais)).header("Content-Type", "application/json")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		String id = Utility.getData(resp, "ID");
		System.out.println(id);
	}
	
	
	@DataProvider
	public Object[][] getData() {
		
		return new Object[][] {{"abhd","234"},{"swdtr","345"},{"erqsdw","456"}};
		
	}
	
	//To delete books
	@Test(dataProvider="getData")
	public void deleteBook(String isb, String ais) {
		
		String ID = isb+ais;
		System.out.println(ID);
		
		RestAssured.baseURI="http://216.10.245.166";
		String resp = given().log().all().body(Payload.deleteBook(ID))
				.when().post("/Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
//		System.out.println("@@@@@@@@@@@@"+resp);
		
		String msg = Utility.getData(resp, "msg");
		Assert.assertEquals(msg, "book is successfully deleted");
	}

}
