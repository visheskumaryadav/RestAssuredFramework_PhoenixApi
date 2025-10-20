package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constants.Role.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {

	@Test(description = "Verify if userdetails api response is shown correctly",groups = {"api","smoke","regression"})
	public void userDetailsApiTest() {

		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_Ok())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}
