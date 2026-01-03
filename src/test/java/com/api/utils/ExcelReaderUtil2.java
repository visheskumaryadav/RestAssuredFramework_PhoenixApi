package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2() {

	}

	public static Iterator<UserCredentials> loadExcelDataUsingPOI(String fileName) {
		// Apache POI OOXML LIB
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// focus on sheet

		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
//		XSSFRow myRow = mySheet.getRow(1);
//		XSSFCell myCell = myRow.getCell(0);
//		System.out.println(myCell);

		XSSFRow myRow;
		XSSFCell myCell;

		XSSFRow headerRow = mySheet.getRow(0);

		int userNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRow) {
			if (cell.toString().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}
			if (cell.toString().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}
		}
//		System.out.println(userNameIndex + " " + passwordIndex);

		int lastRowIndex = mySheet.getLastRowNum();
		XSSFRow rowData;
		UserCredentials userCredentials;
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();

		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = mySheet.getRow(rowIndex);
			userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(userCredentials);
		}
		return userList.iterator();

	}

	public static <T> Iterator<T> loadExcelDataUsingPOIJI(String fileName,String sheetName,Class<T> clazz) {
		// Below code is based on POIJI

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// focus on sheet

		XSSFSheet mySheet = myWorkbook.getSheet(sheetName);

		// Below code is based on POIJI

		List<T> dataList = Poiji.fromExcel(mySheet, clazz);
		return dataList.iterator();
	}

}
