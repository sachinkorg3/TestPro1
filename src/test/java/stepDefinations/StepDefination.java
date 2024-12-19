package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertthat
//import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Locaton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	// RequestSpecification req;
	ResponseSpecification responsesepc1;
	RequestSpecification res;
	Response response;
	TestDataBuild td = new TestDataBuild();
	static String place_id;
	@Given("user Add Playload with {string} {string} {string}")
	public void user_add_playload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		res = given().spec(requestSpecification()).body(td.addPlacePayload(name, language, address));
	}

	/*
	 * @Given("user Add Playload") public void user_add_playload() throws
	 * IOException { // Write code here that turns the phrase above into concrete
	 * actions // throw new io.cucumber.java.PendingException();
	 * 
	 * 
	 * res=given().spec(requestSpecification()) .body(td.addPlacePayload());
	 * 
	 * }
	 */
//	JsonPath js ;
	

	
	
	@When("Use call {string} with {string} Http requst")
	public void use_call_with_http_requst(String Resource1, String Method) {
	    // Write code here that turns the phrase above into concrete actions
	   // new io.cucumber.java.PendingException();
		APIResources resourceAPI = APIResources.valueOf( Resource1); 
		
		
		  responsesepc1 = new
				  ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
				  JSON).build();
				  if (Method.equalsIgnoreCase("POST"))
				  {
				  response =res.when().post(resourceAPI.getResource()).then().spec(responsesepc1).extract
				  ().response();
				  }
				  else if (Method.equalsIgnoreCase("GET"))
				  {
					  response =res.when().get(resourceAPI.getResource()).then().spec(responsesepc1).extract
							  ().response();
							  
					  
				  }
		
	}


	/*
	 * @When("Use call {string} with Post Http requst") public void
	 * use_call_with_post_http_requst(String Resource1) { // Write code here that
	 * turns the phrase above into concrete actions // throw new
	 * io.cucumber.java.PendingException(); APIResources resourceAPI =
	 * APIResources.valueOf( Resource1); resourceAPI.getResource();
	 * 
	 * responsesepc1 = new
	 * ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
	 * JSON).build();
	 * 
	 * response =
	 * res.when().post("maps/api/place/add/json").then().spec(responsesepc1).extract
	 * ().response();
	 * 
	 * }
	 */
	 
	@Then("APi got call success with status code {int}")
	public void a_pi_got_call_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Keyvalue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

	//	String resp = response.asString();
	
			
		assertEquals(getJsonpath(response,Keyvalue), Expectedvalue);

	}
	@Then("verify Place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resorucename) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		 place_id=getJsonpath(response,"place_id");
		
		System.out.println("Place id is "+place_id);
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		use_call_with_http_requst(resorucename,"GET");
		String name1=getJsonpath(response,"name");
		
		assertEquals(name1,expectedname);

	}
	
	
	@Given("user DeletePlaceAPI payload")
	public void user_delete_place_api_payload() throws IOException
	{
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		
	System.out.println("Place id is "+place_id);
		
		res = given().spec(requestSpecification()).body(td.deletePlacePayload(place_id));
		
	}



	}



