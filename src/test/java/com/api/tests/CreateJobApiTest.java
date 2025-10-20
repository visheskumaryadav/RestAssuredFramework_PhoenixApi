package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

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
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "creating createjob api request payload")
	public void setup() {
		// Create the CreateJobPayload object and it will be same record too
				Customer customer=new Customer("rahul", "ssharma", "123456789012", "", "rahul@yopmail.com", ""); 
				CustomerAddress customerAddress=new CustomerAddress("C 304", "Jupiter", "MG Road", "Bangur Nagar", "Goregaon West","411039", "India", "Maharashtra");
				CustomerProduct customerProduct=new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(5), "SN243422733055", "356789072245378", "333785579225679", DateTimeUtil.getTimeWithDaysAgo(5),
						Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode());
				Problems problems=new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
				List<Problems> problemsList=new ArrayList<>();
				problemsList.add(problems);
				createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
				
	}
	
	@Test(description = "Verify if createjob api is able to create inwarranty job",groups = {"api","smoke","regression"})
	public void createJobApiTest() {
		
		
		
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
