package com.maps.samplerestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.map.config.Utility;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	@Test
	public void addComment() {
		
		RestAssured.baseURI="http://localhost:8080";
		
		//To login into the application
		SessionFilter session = new SessionFilter();
		given().log().all().header("content-type","application/json")
		.body("{ \"username\": \"vipin.dhiman21\", \"password\": \"Datacert1!\" }")
		.filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		

		//To add comment in the issue using issue id
		String expectedComment = "Comments via Rest Assurd Fourth time";
		String commentResponse = given().log().all().header("Content-Type","application/json").pathParam("id", "10003")
		.body("{\r\n"
				+ "    \"body\": \""+expectedComment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		.filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		JsonPath jse = new JsonPath(commentResponse);
		int commentId = jse.getInt("id");
		System.out.println("##### "+commentId);
		
		//To add a file in the issue - Below commented code is working. run only if want to upload file
		given().log().all().header("X-Atlassian-Token", "no-check")
		.filter(session)
		.pathParam("id", "10003")
		.header("Content-Type", "multipart/form-data")
		.multiPart("file",new File("jira - Template.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//Get issue details
		String issueDetails = given().log().all()
		.filter(session)
		.pathParam("id","10003")
		.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all()
		.extract().response().asString();
		
		System.out.println(issueDetails);
		
		JsonPath js = new JsonPath(issueDetails);
		int commentsCount = js.getInt("fields.comment.comments.size()");
		String str = null;
		for(int x=0;x<commentsCount;x++) {
			int ide = js.getInt("fields.comment.comments["+x+"].id");
			if(ide == commentId) {
				System.out.println("$$$$ "+ide);
				str = js.getString("fields.comment.comments["+x+"].body");
				System.out.println("!!!!!!! "+str);
				break;
			}
		}
		Assert.assertEquals(str, expectedComment);
		
	}
}
