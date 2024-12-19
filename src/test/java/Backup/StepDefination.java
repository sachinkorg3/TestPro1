package Backup;


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
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	// RequestSpecification req;
	ResponseSpecification responsesepc1;
	RequestSpecification res;
	Response response;
	TestDataBuild td = new TestDataBuild();

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

	@When("Use call {string} with Post Http requst")
	public void use_call_with_post_http_requst(String string) {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();
		responsesepc1 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post("maps/api/place/add/json").then().spec(responsesepc1).extract().response();

	}

	@Then("APi got call success with status code {int}")
	public void a_pi_got_call_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Keyvalue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);

		assertEquals(js.get(Keyvalue).toString(), Expectedvalue);

	}

}
