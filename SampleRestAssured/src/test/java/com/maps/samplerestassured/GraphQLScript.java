package com.maps.samplerestassured;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;


public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//For graphQL Query part
		int characterId = 14;
		
		String res = given().log().all().headers("Content-Type","application/json")
		.body("{\"query\":\"query($characterId:Int!,$locationId:Int!)\\n{\\n  character(characterId:$characterId){\\n    name\\n    gender\\n  }\\n  location(locationId:$locationId){\\n    name\\n    type\\n    dimension\\n  }\\n    episode(episodeId:1752){\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Vipin\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      id\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":2562,\"locationId\":3001}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(res);
		
		JsonPath js = new JsonPath(res);
		String name = js.getString("data.character.name");
		
		assertEquals(name, "Robin Hood");
		
		//For Mutation
		String characterName = "VP0010";
		String mutationresp = given().log().all().headers("Content-Type","application/json")
				.body("{\"query\":\"mutation($locationname:String!,$characterName:String!)\\n{  \\n  createLocation(location:{name:$locationname,type:\\\"SouthZone\\\",dimension:\\\"009\\\"}){\\n    id\\n  }\\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"alive\\\",species:\\\"fantacy\\\",gender:\\\"male\\\",image:\\\".png\\\",originId:3001,locationId:3001}){\\n    id\\n  }\\n  createEpisode(episode:{name:\\\"Victim\\\",air_date:\\\"10-11-2016\\\",episode:\\\"Second\\\"}) {\\n    id\\n  }\\n  \\n}\",\"variables\":{\"locationname\":\"Spain\",\"characterName\":\""+characterName+"\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
				
				System.out.println(mutationresp);
				
//				JsonPath js = new JsonPath(mutationresp);
		
	}

}
