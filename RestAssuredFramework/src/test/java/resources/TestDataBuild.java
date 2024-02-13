package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GetMap;
import pojo.Location;

public class TestDataBuild {

	
	public GetMap addPayloadBody(String name, String language, String address) {
		GetMap p = new GetMap();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setName(name);
		p.setPhone_number("919807890005");
		p.setWebsite("http://www.samplesite.com");
		p.setLanguage(language);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe Park");
		myList.add("shopping");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePayloadBody(String palceID) {
		return "{\r\n"
				+ "    \"place_id\":\""+palceID+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
