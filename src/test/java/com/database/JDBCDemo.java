package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	private final static String DB_URL= "jdbc:mysql://64.227.160.186:3306/SR_DEV";
	private final static String DB_USER="srdev_ro_automation";
	private final static String DB_PASS="Srdev@123";

	public static void main(String[]args) throws SQLException {
		
		// Step1: Establish the connection to the phoenix database
		// DriverManager uses Factory design pattern
	
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		Statement statement = conn.createStatement();
		ResultSet resultSet=statement.executeQuery("Select first_name,last_name,mobile_number from tr_customer tc;");
		
		while(resultSet.next()) {
			String first_name=resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			String mobile_number=resultSet.getString("mobile_number");
			System.out.println(first_name + "|"+ last_name+ "|"+mobile_number);
		}
		
		
		
	}
}
