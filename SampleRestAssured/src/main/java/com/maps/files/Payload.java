package com.maps.files;

public class Payload {
	
	public static String addMapsBody() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house 4001\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String courseData() {
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String addBookBody(String isb, String ais) {
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isb+"\",\r\n"
				+ "\"aisle\":\""+ais+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}";
	}
	
	public static String deleteBook(String id) {
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "}";
	}
	
	public static String getCourse() {
		return "{\r\n"
				+ "	\"instructor\" : \"RahulShetty\",\r\n"
				+ "	\"url\" : \"rahulshettyacademy.com\",\r\n"
				+ "	\"services\" : \"projectSupport\",\r\n"
				+ "	\"expertise\" : \"Automation\",\r\n"
				+ "	\"linkedin\" : \"https://www.linkedin.com/in/rahul-shetty-trainer/\",\r\n"
				+ "	\"courses\" : {\r\n"
				+ "	\"webAutomation\" : [\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"Selenium Webdriver Java\",\r\n"
				+ "			\"price\" : \"50\"\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"Cypress\",\r\n"
				+ "			\"price\" : \"40\"\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"Protractor\",\r\n"
				+ "			\"price\" : \"40\"\r\n"
				+ "		}\r\n"
				+ "	],\r\n"
				+ "	\"api\" : [\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"RestAssuredAutomation using java\",\r\n"
				+ "			\"price\" : \"50\"\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"SoapUIWebservices testing\",\r\n"
				+ "			\"price\" : \"40\"\r\n"
				+ "		}\r\n"
				+ "	],\r\n"
				+ "	\"mobile\" : [\r\n"
				+ "		{\r\n"
				+ "			\"courseTitle\" : \"Appium-mobile Automation using java\",\r\n"
				+ "			\"price\" : \"50\"\r\n"
				+ "		}\r\n"
				+ "	]\r\n"
				+ "	}\r\n"
				+ "}\r\n"
				+ "";
	}
	

}
