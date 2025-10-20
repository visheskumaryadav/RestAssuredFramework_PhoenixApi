package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	private UserCredentials userCredentials;
	
	@BeforeMethod(description = "Create the payload for login api")
	public void setUp() {
		userCredentials =new UserCredentials("iamfd","password");
	}

	@Test(description = "Verifying if login api is working for FD user",groups = {"smoke","api","regression"})
	public void loginApiTest() {
		
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
