package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {

	@Test
	public void userDetailsApiTest() {

		Header authHeader =new Header("Authorization", getToken(FD));
		given()
		.baseUri(getProperty("BASE_URL"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.header(authHeader)
		.log().uri()
		.log().headers()
		.log().body()
		.and()
		.get("userdetails")
		.then().log().body()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}
