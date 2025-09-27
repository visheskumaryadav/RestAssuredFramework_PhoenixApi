package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginApiTest() {
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		given()
		.baseUri(getProperty("BASE_URL"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(userCredentials)
		.log().uri()
		.log().headers()
		.log().body()
		.and()
		.post("login")
		.then().log().body()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		}

}
