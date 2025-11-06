package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {
		// Code to read the CSV file in java
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCred.csv");
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
