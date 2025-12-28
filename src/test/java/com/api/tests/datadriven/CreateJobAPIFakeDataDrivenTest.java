package com.api.tests.datadriven;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIFakeDataDrivenTest {


	@Test(description = "Verify if createjob api is able to create inwarranty job", groups = { "api", "smoke",
			"regression",
			"dataDriven","faker" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobFakerDataProvider"

	)
	public void createJobApiTestwithFaker(CreateJobPayload createJobPayload) {

		given().spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload)).when().post("job/create").then()
				.spec(SpecUtil.responseSpec_Ok()).body("message", equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"));

	}
}
