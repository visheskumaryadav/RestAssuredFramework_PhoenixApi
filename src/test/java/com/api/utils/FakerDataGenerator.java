package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;

public class FakerDataGenerator {
	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private final static Random random = new Random();
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;
	private static final int VALID_PROBLEMS_ID[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemsList = generateFakeProblemsListData();
		CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		return createJobPayload;
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload> createJobPayloadList = new ArrayList<CreateJobPayload>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakeCustomerProductData();
			List<Problems> problemsList = generateFakeProblemsListData();
			CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
			createJobPayloadList.add(createJobPayload);
		}

		return createJobPayloadList.iterator();
	}

	private static List<Problems> generateFakeProblemsListData() {
		int id = random.nextInt(VALID_PROBLEMS_ID.length);
		String remark = faker.lorem().sentence(10);
		Problems problem = new Problems(VALID_PROBLEMS_ID[id], remark);
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);
		return problemsList;
	}

	private static CustomerProduct generateFakeCustomerProductData() {
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerial_number = faker.numerify("###############");
		String popurl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerial_number, imeiSerial_number,
				imeiSerial_number, popurl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddressData() {
		String flat_number = faker.numerify("###");
		String apartment_name = faker.address().streetName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area,
				pincode, COUNTRY, state);
		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		// TODO Auto-generated method stub
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("94########");
		String altMobileNumber = faker.numerify("80########");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();
		Customer customer = new Customer(fName, lName, mobileNumber, altMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		return customer;
	}
}
