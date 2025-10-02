package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {
	
	
	
	@Test
	public void createJobApiTest() {
		// Create the CreateJobPayload object and it will be same record too
		Customer customer=new Customer("rahul", "ssharma", "123456789012", "", "rahul@yopmail.com", ""); 
		CustomerAddress customerAddress=new CustomerAddress("C 304", "Jupiter", "MG Road", "Bangur Nagar", "Goregaon West","411039", "India", "Maharashtra");
		CustomerProduct customerProduct=new CustomerProduct("2025-04-06T18:30:00.000Z", "SN223422733055", "356789072255378", "333785579225679", "2025-04-06T18:30:00.000Z",1,1);
		Problems problems=new Problems(1, "Battery Issue");
		List<Problems> problemsList=new ArrayList<>();
		problemsList.add(problems);
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload))
		.when()
		.post("job/create")
		.then().spec(SpecUtil.responseSpec_Ok())
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number",startsWith("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"));
	}
}
