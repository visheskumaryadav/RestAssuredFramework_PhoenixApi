package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	// Whenever we define a data provider, it should be static method
	
	
    @DataProvider(name = "LoginAPIDataProvider",parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() { 
    	// If we don't provide the name of @DataProvider then name of the method becomes the name of data provider 
    	// Return type of data provider should return something
    	return CSVReaderUtil.loadCSV("testData/LoginCred.csv");
		
	}
    
    // Data Provider needs to return something
    // 1. [] -> 1D array
    // 2. [][] -> 2D array
    // 3. Iterator<> 
}
