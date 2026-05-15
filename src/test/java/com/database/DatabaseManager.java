package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;
import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

public class DatabaseManager {
	private static final String DB_URL= ConfigManager.getProperty("DB_URL");
	private static final String DB_USER=ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASS=ConfigManager.getProperty("DB_PASS");
	private static volatile HikariDataSource hikariDataSource;
	// volatile means any update to this variable then all threads will be aware of it

	public static void createConnection() throws SQLException {
		Connection connection=DriverManager
				.getConnection(DB_URL,DB_USER,DB_PASS);
		System.out.println(connection);
	}
	
	public static void initilizePool() {
		if(hikariDataSource==null) {
			synchronized (DatabaseManager.class) {
				if(hikariDataSource==null) {
					HikariConfig hikariConfig=new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER);
					hikariConfig.setPassword(DB_PASS);
					// Any value  we get from property file it is string even integers are treated as string
					hikariConfig.setMaximumPoolSize(Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE")));// hikari will create 10 connections in a pool
					hikariConfig.setMinimumIdle(Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE")));
					hikariConfig.setConnectionTimeout(Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"))*1000);
					hikariConfig.setIdleTimeout(Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT")));
					hikariConfig.setMaxLifetime(Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME")));//30 mins 30x60x1000
					
					hikariDataSource=new HikariDataSource(hikariConfig);
				}
				
			}
		}
		
	}
	public static Connection getConnection() throws SQLException {
		Connection connection=null;
		if(hikariDataSource==null) {
			initilizePool();
		}else if(hikariDataSource.isClosed()) {
			throw new SQLException("HIKARI DATA SOURCE IS CLOSED");
		}
		try {
			connection=hikariDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[]args) throws SQLException {
		Connection connection=DatabaseManager.getConnection();
		System.out.println(connection);
	}
	
}
