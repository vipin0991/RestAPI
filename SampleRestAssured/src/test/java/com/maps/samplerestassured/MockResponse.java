package com.maps.samplerestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maps.files.Payload;

import io.restassured.path.json.JsonPath;

public class MockResponse {

	@Test
	public static void validate() {
		// TODO Auto-generated method stub
		
		String resp = Payload.courseData();
		
		JsonPath js = new JsonPath(resp);
		
		//To print number of courses
		int courseSize = js.getInt("courses.size()");
		System.out.println(courseSize);

		//To print purchase amount
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		
		//To print title of first course
		System.out.println(js.get("courses[0].title"));
		System.out.println("*****************");
		
		//To print course titles and their respective prices
		for(int i=0;i<courseSize;i++) {
			System.out.println(js.getString("courses["+i+"].title")+" : "+js.get("courses["+i+"].price"));
		}
		
		//To print number of copies sold by RPA
		System.out.println(js.get("courses[2].copies"));
		
		//If RPA index is dynamic
		for(int i=0;i<courseSize;i++) {
			String title = js.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA")) {
				System.out.println(js.getString("courses["+i+"].copies"));
				break;
			}
			
		}
		
		//To verify if sum of all courses matches with Purchase amount
		int pamount = js.getInt("dashboard.purchaseAmount");
		int sum = 0;
		
		for(int j=0;j<courseSize;j++) {
			sum += js.getInt("courses["+j+"].price")*js.getInt("courses["+j+"].copies");
		}
		System.out.println(sum);
		Assert.assertEquals(pamount, sum);
		
	
	}

}
