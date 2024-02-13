package com.maps.files;

import io.restassured.path.json.JsonPath;

public class Utility {
	
	public static String getData(String resp, String key) {
		JsonPath js1 = new JsonPath(resp);
		return js1.getString(key);
	}

}
