package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	// Whenever we define a data provider, it should be static method

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		// If we don't provide the name of @DataProvider then name of the method becomes
		// the name of data provider
		// Return type of data provider should return something
		return CSVReaderUtil.loadCSV("testData/LoginCred.csv", UserBean.class);

	}

	// Data Provider needs to return something
	// 1. [] -> 1D array
	// 2. [][] -> 2D array
	// 3. Iterator<>

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {
		Iterator<CreateJobBean> creatorJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean = null;
		CreateJobPayload tempPayload = null;

		while (creatorJobBeanIterator.hasNext()) {
			tempBean = creatorJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();
	}

	@DataProvider(name = "CreateJobFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobFakerDataProvider() {
		String fakerCountString = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCountString);
		Iterator<CreateJobPayload> createJobPayloadIterator = FakerDataGenerator
				.generateFakeCreateJobData(fakerCountInt);
		return createJobPayloadIterator;
	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
		// If we don't provide the name of @DataProvider then name of the method becomes
		// the name of data provider
		// Return type of data provider should return something
		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredentials[].class);

	}

}
