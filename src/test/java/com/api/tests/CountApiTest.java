package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CountApiTest {

	@Test(description = "Verify if count api response is giving correctly",groups = {"api","smoke","regression"})
	public void verifyCountApiResponse() {
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_Ok())
		.body("message",equalTo("Success"))
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema_FD.json"));
	}
	
	@Test(description = "Verify if count  api gives correct status code for invalid token",groups = {"api","negative","smoke","regression"})
	public void countApiTestMissingAuthToken() {
		given()
		.spec(SpecUtil.RequestSpec())
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
