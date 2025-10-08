package com.api.utils;

import static org.hamcrest.Matchers.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {
		UserCredentials userCredentials = null;

		if (role == FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		} else if (role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} else if (role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		} else if (role == QC) {
			userCredentials = new UserCredentials("iamsqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URL")).accept(ContentType.JSON)
				.contentType(ContentType.JSON).body(userCredentials).when().post("login").then().log()
				.ifValidationFails().statusCode(200).body("message", equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");
		return token;
	}

}
