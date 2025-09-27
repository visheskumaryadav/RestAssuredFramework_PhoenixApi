package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

public class ConfigManager {
	private static Properties properties = new Properties();
	private static String filePath;
	private static String env;

	private ConfigManager() {
		// private constructor !!!!
	}

	static {
		env = System.getProperty("env", "qa").toLowerCase().trim();
		System.out.println("Running on environment: " + env);
		switch (env) {
		case "dev" -> filePath = "configs/config.dev.properties";

		case "qa" -> filePath = "configs/config.qa.properties";

		case "uat" -> filePath = "configs/config.uat.properties";

		default -> filePath = "configs/config.qa.properties";

		}

		InputStream configInput = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		if (configInput == null) {
			throw new RuntimeException("File path not found:" + filePath);
		}
		try {

			properties.load(configInput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);

	}

	public static void main(String[] args) {

	}
}
