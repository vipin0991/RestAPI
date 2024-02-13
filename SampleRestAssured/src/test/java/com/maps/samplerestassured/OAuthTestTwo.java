package com.maps.samplerestassured;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuthTestTwo {
	
	@Test
	public void authentication() throws InterruptedException {
		
//		RestAssured.baseURI="";
		//Use to  Get AccessToken Request 
		String resp = given().log().all().urlEncodingEnabled(false)
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(resp);
		
		JsonPath js = new JsonPath(resp);
		String accessToken = js.getString("access_token");
		System.out.println("@@@@@@"+accessToken);

		//Shortcut to extract response based on the extracted accesstoken from above request
		//And how to use Pojo classes
		GetCourse gc= given().queryParams("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourse.class);
//		System.out.println(res2);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		//But if its dynamic
		List<pojo.Api> ap = gc.getCourses().getApi();
		List<WebAutomation> wa = gc.getCourses().getWebAutomation();
		String str = "SoapUI Webservices testing";
//		String exp[] = {"Selenium Webdriver Java","SoapUI Webservices testing","Appium-Mobile Automation using Java"};
//		for(pojo.Api e : ap) {
//			if(e.getCourseTitle().equalsIgnoreCase(str)) {
//				System.out.println(e.getPrice());
//			}
//		}
		
		for(int i=0;i<ap.size();i++) {
			if(ap.get(i).getCourseTitle().equalsIgnoreCase(str)) {
				System.out.println(ap.get(i).getPrice());
			}
		}
		//Get the course names of webAutomation
		for(WebAutomation e : wa) {
			System.out.println(e.getCourseTitle());
		}
		
	}

}
