package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterApiTest {
	
	@Test
	public void masterApiTest() {
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_Ok())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("$", hasKey("data"))
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("data", hasKey("mst_action_status"))
		.body("data.mst_oem.size()",greaterThan(0))
		.body("data.mst_oem.id",everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema_FD.json"));
	}
	
	@Test
	public void masterApiInvalidToken() {
		given()
		.spec(SpecUtil.RequestSpec())
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
