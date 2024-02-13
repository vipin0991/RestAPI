package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefinition m = new StepDefinition();
		if(StepDefinition.placeId == null) {
			
			m.add_Place_Payload_with("sample0031", "SWISS", "New COURT");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("sample0031", "GetPlaceAPI");
			
		}
		
	}

}
