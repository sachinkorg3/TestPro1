package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

@Before("@DeletePlace")


public void BeforeScenario() throws IOException
{
	StepDefination m = new StepDefination();
	
	if (StepDefination.place_id==null)
	{
	m.user_add_playload_with("Shetty", "French", "Asia");
	m.use_call_with_http_requst("AddPlaceAPI", "POST");
    m.verify_place_id_created_maps_to_using("Shetty", "GetPlaceAPI");
	}
}

}
