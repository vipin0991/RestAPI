package com.map.config;

import io.restassured.path.json.JsonPath;

public class Utility {

	public static String getData(String resp, String key) {

		JsonPath js = new JsonPath(resp);

		return js.getString(key);

	}

}
