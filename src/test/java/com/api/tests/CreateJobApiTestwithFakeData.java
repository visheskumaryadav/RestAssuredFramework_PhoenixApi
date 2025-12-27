package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTestwithFakeData {
	private CreateJobPayload createJobPayload;

	@BeforeMethod(description = "creating createjob api request payload")
	public void setup() {
		// Create the CreateJobPayload object and it will be same record too

	
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify if createjob api is able to create inwarranty job", groups = { "api", "smoke",
			"regression" })
	public void createJobApiTest() {

		given().spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload)).when().post("job/create").then()
				.spec(SpecUtil.responseSpec_Ok()).body("message", equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"));
	}
}
