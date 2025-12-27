package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY = "India";

	public static void main(String[] args) {
		// Create Fake CreateJobApi payload
		// I want to create Fake customer object

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
		
		String dop=DateTimeUtil.getTimeWithDaysAgo(10); 
		String imeiSerial_number=faker.numerify("###############");
		String popurl=faker.internet().url();
//		int product_id,
//		int mst_model_id
		CustomerProduct customerProduct =new CustomerProduct(dop, imeiSerial_number, imeiSerial_number, imeiSerial_number, popurl, 1, 1);
		Random random =new Random();
		int id=random.nextInt(26)+1;
		String remark=faker.lorem().sentence(10);
		Problems problem = new Problems(id, remark);
		List<Problems> problemsList=new ArrayList<Problems>();
		problemsList.add(problem);
		
		CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		
	}

}
