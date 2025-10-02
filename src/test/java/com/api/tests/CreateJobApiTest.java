package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobApiTest {
	
	
	
	@Test
	public void createJobApiTest() {
		// Create the CreateJobPayload object
		Customer customer=new Customer("rahul", "ssharma", "123456789012", "", "rahul@yopmail.com", "");
		CustomerAddress customerAddress=new CustomerAddress("C 304", "Jupiter", "MG Road", "Bangur Nagar", "Goregaon West", "411039", "India", "Maharashtra");
		CustomerProduct customerProduct=new CustomerProduct("2025-04-06T18:30:00.000Z", "SN223456789011", "356789040345678", "326789020345679", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems=new Problems(1, "Battery Issue");
		Problems[] problemsArray= {problems};
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload))
		.when().log().all()
		.post("job/create")
		.then().spec(SpecUtil.responseSpec_Ok());
	}
}
