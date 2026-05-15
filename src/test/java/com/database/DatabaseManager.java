package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManager {
	private static final String DB_URL= ConfigManager.getProperty("DB_URL");
	private static final String DB_USER=ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASS=ConfigManager.getProperty("DB_PASS");

	public static void createConnection() throws SQLException {
		Connection connection=DriverManager
				.getConnection(DB_URL,DB_USER,DB_PASS);
		System.out.println(connection);
	}
	
	public static void main(String[]args) throws SQLException {
		DatabaseManager.createConnection();
	}
	
}
