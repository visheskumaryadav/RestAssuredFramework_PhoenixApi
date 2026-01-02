package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/phoenixTestData.xlsx");

		XSSFWorkbook myWorkbook = new XSSFWorkbook(is);

		// focus on sheet

		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
//		XSSFRow myRow = mySheet.getRow(1);
//		XSSFCell myCell = myRow.getCell(0);
//		System.out.println(myCell);
		
		XSSFRow myRow;
		XSSFCell myCell;
		
		int lastRowIndex=mySheet.getLastRowNum();
		
		XSSFRow rowHeader =mySheet.getRow(0);
		int lastIndexOfCol=rowHeader.getLastCellNum()-1;
		
		for(int rowIndex=0;rowIndex<=lastRowIndex;rowIndex++) {
			
			for(int colIndex=0;colIndex<=lastIndexOfCol;colIndex++) {
				
				myRow =mySheet.getRow(rowIndex);
				myCell=myRow.getCell(colIndex);
				System.out.print(myCell+" ");
			}
			System.out.println("");
		}

	}
}
