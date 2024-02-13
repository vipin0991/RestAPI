package com.maps.samplerestassured;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.maps.files.Payload;
import com.sun.tools.javac.util.DefinedBy.Api;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuthTestDeSerialization {
	
	@Test
	public void authentication() throws InterruptedException {

		
		//Shortcut to extract response based on the extracted accesstoken from above request
		String resp = Payload.getCourse();
		
//		 GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
//		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
//		.as(GetCourse.class)
		
		Gson g = new Gson();  
		//gc is object/reference variable here
		GetCourse gc = g.fromJson(resp, GetCourse.class);

		
		
		System.out.println(gc.getLinkedin());
		System.out.println(gc.getInstructor());
		
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		//if index is dynamic
		List<pojo.Api> ap = gc.getCourses().getApi();
//		System.out.println(ap.size());
		String str = "SoapUIWebservices testing";
		String[] expectedArray = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		for(pojo.Api e : ap) {
			if(e.getCourseTitle().equalsIgnoreCase(str)) {
				System.out.println(e.getPrice());
			}
		}
		
		List<WebAutomation> wa = gc.getCourses().getWebAutomation();
//		for(pojo.WebAutomation e : wa) {
//			System.out.println(e.getCourseTitle());
//		}
		
		ArrayList<String> ar = new ArrayList<String>();
		//Used arrayList as we are not sure about size of array and its dynamic
		for(int i=0;i<wa.size();i++) {
			ar.add(wa.get(i).getCourseTitle());
//			System.out.println(wa.get(i).getCourseTitle());
		}
		for(int i=0;i<ar.size();i++) {
			if(ar.contains(expectedArray[i])){
				System.out.println("present : "+expectedArray[i]);
			}
		}
		
		List<String> expArr = Arrays.asList(expectedArray);
		Assert.assertTrue(ar.equals(expArr));
		
		
	}

}
