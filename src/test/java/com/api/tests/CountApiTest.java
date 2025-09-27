package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CountApiTest {

	@Test
	public void verifyCountApiResponse() {
		Header authHeader=new Header("Authorization",getToken(FD));
		given()
		.baseUri(ConfigManager.getProperty("BASE_URL"))
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.header(authHeader)
		.log().uri().log().headers().log().body()
		.when()
		.get("dashboard/count")
		.then()
		.log().body()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message",equalTo("Success"))
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema_FD.json"));
	}
	
	@Test
	public void countApiTestMissingAuthToken() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URL"))
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}
}
