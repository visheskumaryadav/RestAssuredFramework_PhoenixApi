package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
//
//	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
//
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/demo.json");
//		// convert json object into java object -> Deserialization
//		// Jackson databind --> ObjectMapper
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		// If there single object in json then we can map it with model class like below
////		UserCredentials userCredentials=objectMapper.readValue(is,UserCredentials.class);
////		System.out.println(userCredentials.toString());
//
//		// If there is a list of objects in json then we can map it with List.class like
//		// below
////		List list=objectMapper.readValue(is, List.class); --> But this is not correct as we can't access the object
////		System.out.println(list);
//
//		// Below code is one of valid way
//		UserCredentials[] userCredentialsArray = objectMapper.readValue(is, UserCredentials[].class);
//		for (UserCredentials user : userCredentialsArray) {
//			System.out.println(user.username());
//		}
//
//		List<UserCredentials> userCredentialsList = Arrays.asList(userCredentialsArray);
//		userCredentialsList.iterator();
//	}
//	
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list.iterator();
	}
}
