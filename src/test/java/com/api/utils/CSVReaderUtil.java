package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	/*
	 * Constructor should be private
	 * static methods
	 * Job: Help me Read the CSV file and Map it a Bean
	 */

	private CSVReaderUtil() {
		// No one can create Object of CSVReaderUtil Outside the class
	}
	
	public static void loadCSV(String pathOfCSVFile) {
		// Code to read the CSV file in java
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
//		File csvFile=new File("C:\\Users\\vishes.kumar.yadav\\eclipse-workspace\\PhoenixApiAutomationFramework\\src\\main\\resources\\testData\\LoginCred.csv");
//		FileReader fr=new FileReader(csvFile);
		InputStreamReader isr=new InputStreamReader(is);
		CSVReader csvReader= new CSVReader(isr);
		
		CsvToBean<UserBean> csvToBean=new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		List<UserBean> userList=csvToBean.parse();
		System.out.println(userList);
		System.out.println(userList.get(0).getUsername());
	}
}
