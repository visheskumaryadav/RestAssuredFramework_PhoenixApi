package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

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
		Header authHeader=new Header("Authorization",getToken(FD));
		
		given()
		.baseUri(getProperty("BASE_URL"))
		.contentType("")
		.accept(ContentType.JSON)
		.header(authHeader)
		.log().uri().log().headers().log().body()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.time(lessThan(1000L))
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
		.baseUri(getProperty("BASE_URL"))
		.contentType("")
		.accept(ContentType.JSON)
		.header("","")
		.log().uri().log().headers().log().body()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(401);
	}
}
