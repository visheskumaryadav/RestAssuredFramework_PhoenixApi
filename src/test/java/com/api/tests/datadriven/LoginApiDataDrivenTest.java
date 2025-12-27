package com.api.tests.datadriven;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiDataDrivenTest {

/*
 * When we link a test with data provider then that test becomes parameterized test method
 * @DataProvider is only applicable with @Test method we can't use it with other annotations
 * If we create dataprovider in the same test class then use non-static method and if it is different class then use
 * static method
 */
	@Test(description = "Verifying if login api is working for FD user",
			groups = {"datadriven","api","regression"},
	        dataProviderClass = com.dataproviders.DataProviderUtils.class,
	        dataProvider = "LoginAPIDataProvider"
			)
	public void loginApiTest(UserBean userBean) {
		
		given()
		.spec(SpecUtil.RequestSpec(userBean))
		.and()
		.post("login")
		.then().spec(SpecUtil.responseSpec_Ok())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		}
	
}
