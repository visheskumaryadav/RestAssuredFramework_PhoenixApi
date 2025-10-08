package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

	private DateTimeUtil() {
	}

	public static String getTimeWithDaysAgo(int days) {
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}
//	public static void main(String[] args) {
//		System.out.println(Instant.now());
//		System.out.println(Instant.now().minus(10,ChronoUnit.DAYS));
//		
//	}

}
