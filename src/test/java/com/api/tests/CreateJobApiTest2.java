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
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest2 {
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY = "India";

	@BeforeMethod(description = "creating createjob api request payload")
	public void setup() {
		// Create the CreateJobPayload object and it will be same record too

		Faker faker = new Faker(new Locale("en-IND"));

		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("94########");
		String altMobileNumber = faker.numerify("80########");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();
		Customer customer = new Customer(fName, lName, mobileNumber, altMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		System.out.println(customer.toString());

		String flat_number = faker.numerify("###");
		String apartment_name = faker.address().streetName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area,
				pincode, COUNTRY, state);
		System.out.println(customerAddress.toString());

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerial_number = faker.numerify("###############");
		String popurl = faker.internet().url();
//		int product_id,
//		int mst_model_id
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerial_number, imeiSerial_number,
				imeiSerial_number, popurl, 1, 1);
		Random random = new Random();
		int id = random.nextInt(26) + 1;
		String remark = faker.lorem().sentence(3);
		Problems problem = new Problems(id, remark);
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);

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
