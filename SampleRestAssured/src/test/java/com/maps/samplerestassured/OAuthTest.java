package com.maps.samplerestassured;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthTest {
	
	@Test
	public void authentication() throws InterruptedException {
		
//		RestAssured.baseURI="";
		
		//To extract code once we enter password and click on login(SSO login). we have to get code
		//from immediate changed url
		WebDriverManager.edgedriver().setup();
		WebDriver driver =  new EdgeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?access_type=offline&client_id=587594460880-u53ikl5ast2sup28098ofsm9iku8vvm6.apps.googleusercontent.com&code_challenge=lKUz5FwjXmA5g8iuzCOMWni7JiO5pF6aVrPOeTZHllk&code_challenge_method=S256&include_granted_scopes=true&prompt=select_account%20consent&redirect_uri=https%3A%2F%2Fsso.teachable.com%2Fidentity%2Fcallbacks%2Fgoogle%2Fcallback&response_type=code&scope=email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile%20openid%20profile&state=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJnb29nbGUiLCJpYXQiOjE3MDEwMDQ0ODgsImV4cCI6MTcwMTAwNjI4OCwianRpIjoiMTEyMTIyMzktMjljYS00NzExLWExMGMtYzQxNmUzNTFiNmIwIiwiaXNzIjoic2tfeno4dHc2ZGciLCJzdWIiOiJyQ1RuWHhKUVctMnVrSzUtUHV4VnpCUDVSZjIxZS1lVkRIck8zS2lrMTdHSTJGdS1mU2xsaEhMOUl4SzBFclkzSVBPVXduZEhFSXVSSVZndnY3bG9mZyJ9.i0Vy2Fbd1JiGW7bzVP8riKyKXW3nHhdAbbL7mPSbudw&service=lso&o2v=2&theme=glif&flowName=GeneralOAuthFlow");
		driver.findElement(By.id("identifierId")).sendKeys("vipin.dhiman21");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("password")).sendKeys("");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();
//		String url = "https://sso.teachable.com/identity/callbacks/google/callback?state=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJnb29nbGUiLCJpYXQiOjE3MDA5MTUxNjUsImV4cCI6MTcwMDkxNjk2NSwianRpIjoiOGRhMWViNTItOGI0Mi00MzdjLWEzMTUtZGE4NGE1ZGJhMThkIiwiaXNzIjoic2tfeno4dHc2ZGciLCJzdWIiOiJialFkb1p2bk54dU1YbFJPZ3dMWjBtTGNISC1BRS1HYlJkT3YyVzFMa0p6WHdhMzE4MndyWjA5VFktMXlBaXEyM3FaaVdGZU9wTFVmWmpDa3hoMkhxdyJ9.UEEgA9MgT-dX2J6NvlISHFTBK2HEWj1Y5fzlbXdYbVU&code=4%2F0AfJohXmUuTFriPcoz7gGca7dNW0vjXuRXuEQQsG7FCiCo3rkZvW6sgUyWg-Y2onQf4BqpQ&scope=email+profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+openid&authuser=0&prompt=consent";
		String partial = url.split("code=")[1];
		String code = partial.split("&scope")[0];
		System.out.println(code);
		//rest assured perform encoding automatically
		//use urlEncodingEnabled(false) to restrict it
		
		//ya29.GlxgB56UzSPvk69weWofcw6Rk822oKmjVO9Oo0jzW0NprzPlwQ79Hw-2zM9k8r-SztgijCo19viyluASLly_LWgVfvrVk77iwlme6Qh3HAuJQGLh4esgjgTkhu80wg
		
		//Use above code to GetAccessToken Request 
		String accessTokenResp = given().log().all().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResp);
		String accessToken = js.getString("access_token");
		System.out.println("@@@@@@"+accessToken);
		
		//Shortcut to extract response based on the extracted accesstoken from above request
		String resp = given().queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		System.out.println(resp);
	}

}
