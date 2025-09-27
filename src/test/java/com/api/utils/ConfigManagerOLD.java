package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	private static Properties properties = new Properties();

	private ConfigManagerOLD() {
		// private constructor !!!!
	}

	static {
		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "configs" + File.separator + "config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			properties.load(fileReader);
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
