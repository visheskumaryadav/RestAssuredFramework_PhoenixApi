package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginApiTest() {
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		given()
		.spec(SpecUtil.RequestSpec(userCredentials))
		.and()
		.post("login")
		.then().spec(SpecUtil.responseSpec_Ok())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		}

}
