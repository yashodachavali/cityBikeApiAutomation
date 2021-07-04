package com.collinsongroup.travel.stepdefinition;

import com.collinsongroup.travel.response.model.CityBikeApiResponse;
import com.collinsongroup.travel.util.FileUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CityBikeApiStepDefinition {

	private String url;
	private CityBikeApiResponse apiResponse;
	private int httpResponseStatusCode;

	@Given("I access the city bikes api service")
	public void iAccessTheApiService() {
		url = FileUtils.getBaseUri();
		assertNotNull("Api url is null", url);
	}

	@When("I send GET request for {string}")
	public void iSendGetRequestForPath(String path) {
		Response response = RestAssured.given().contentType(ContentType.JSON).when().get(url + path);
		if (ContentType.JSON.toString().equalsIgnoreCase(response.getContentType())) {
			apiResponse = response.getBody().as(CityBikeApiResponse.class);
		}
		httpResponseStatusCode=response.getStatusCode();
	}

	@Then("I validate cycle brand {string} city {string} belongs to Country {string}")
	public void validateCityAndCountry(String company, String city, String country) {
		assertEquals("Company name didn't match", company, apiResponse.getNetwork().getCompany().get(0));
		assertEquals("Cityname name didn't match", city, apiResponse.getNetwork().getLocation().getCity());
		assertEquals("Country name didn't match", country, apiResponse.getNetwork().getLocation().getCountry());
	}

	@And("response should contain the {double} and {double}")
	public void responseShouldContainLatitudeAndLongitude(double latitude, double longitude) {
		assertEquals("Latitude value didn't match", latitude, apiResponse.getNetwork().getLocation().getLatitude(),
				0.00);
		assertEquals("Longitude value didn't match", longitude, apiResponse.getNetwork().getLocation().getLongitude(),
				0.00);
	}

	@And("I validate the response with status code {int}")
	public void iValidateHttpResonseStatusCode(int httpstatuscode) {
		assertEquals("Http response status code didn't match", httpstatuscode, httpResponseStatusCode);
	}

	@And("I validate the {string}")
	public void iValidateTheErrorMessage(String errormessage) {
		System.out.println("Hello");
	}
}
