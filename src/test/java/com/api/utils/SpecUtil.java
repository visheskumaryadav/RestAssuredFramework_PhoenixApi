package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/*
 * SpecUtil class helps in defining the static methods for RequestSpecification and ResponseSpecification
 * RequestSpecification - We use RequestSpecBuilder class for building RequestSpecification
 * Its working is like builder design pattern where we create an object by providing the configuration and 
 * in the end we use build() method so that we can return the object
 * 
 * 
 * 
 * 									
 */
public class SpecUtil {

	// GET--DEL
	public static RequestSpecification RequestSpec() {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}

	//POST
	public static RequestSpecification RequestSpec(Object userCredentials) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(userCredentials)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();

		return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification =new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		return requestSpecification;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role,Object payload) {
		RequestSpecification requestSpecification =new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		return requestSpecification;
	}
	public static ResponseSpecification responseSpec_Ok() {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	
	
}
