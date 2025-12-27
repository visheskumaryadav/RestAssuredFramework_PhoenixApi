package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Faker faker = new Faker(new Locale("en-IND"));
		String fname = faker.name().firstName();
		System.out.println(fname);
		System.out.println(faker.number().digits(3));
		System.out.println(faker.address().buildingNumber());
		System.out.println(faker.phoneNumber().cellPhone());
		System.out.println(faker.numerify("97########"));
	
	
	}

}
