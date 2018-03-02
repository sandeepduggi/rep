package com.wyn.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class ExcelUtils
{

	private static Logger Log = Logger.getLogger(ExcelUtils.class.getName());

	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			File myFile = new File(Path);
			FileInputStream ExcelFile = new FileInputStream(myFile);
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(SheetName);
		} catch (Exception e) {
			Log.error("Error occured while reading excel file" + e.getMessage());
		}
	}
	public static void load(String sheet) throws Exception{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		ExcelUtils.setExcelFile(currentDirectory.getCanonicalPath() + "\\inputdata\\WyndhamRewardsAutomation.xlsx", sheet);
	}
	public static int getRowNumber(String sheetName,String TestCaseName) throws Exception {
		try {
			int k=0;
			int lastRow = ExcelUtils.getLastRow(sheetName);
			for (int i = 1; i <= lastRow; i++) {
				XSSFRow row = ExcelUtils.getRowData(sheetName, i);
				String excelTestName=ExcelUtils.getStringCellValue(row, 0);
			    if(TestCaseName.equals(excelTestName)){
			    	k=i;
			    }
			}
			if(k==0){
				Assert.assertTrue(false);
			}
			return k+1;
		} catch (Exception e) {
			Log.error("Error occured while reading cell data" + e.getMessage());
			return 0;
		}
	}
	public static String getCellData(String sheetName, int RowNum, int ColNum) throws Exception {
		try {
			XSSFSheet sheet = getSheet(sheetName);
			Cell = sheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			Log.error("Error occured while reading cell data" + e.getMessage());
			return "";
		}
	}

	public static String getStringCellValue(XSSFRow row, int colNum) {
		try {

			String CellData = getCell(row, colNum).getStringCellValue();
			return CellData;
		} catch (Exception e) {
			Log.error("Error occured while reading cell data" + e.getMessage());
			return "";
		}
	}
    public static CellAddress getCellAddress(String testCaseName) {
        CellAddress address = null;
        int i=0;
        for (Iterator<org.apache.poi.ss.usermodel.Row> rit = excelWSheet.rowIterator(); rit.hasNext();) {
               org.apache.poi.ss.usermodel.Row row = rit.next();
               for (Iterator<org.apache.poi.ss.usermodel.Cell> cit = row.cellIterator(); cit.hasNext();) {
                     org.apache.poi.ss.usermodel.Cell cell = cit.next();
                     System.out.println(cell.getStringCellValue());
                     System.out.println(cell.getAddress().getRow());
                     if (cell.getStringCellValue().trim().equals(testCaseName)) {
                            address = cell.getAddress();
                             i=1;
                            break;
                     }

               }
               if(i==1){
            	   break;
               }
        }
        return address;
 }


	public static String getBooleanCellValue(XSSFRow row, int colNum) {
		try {

		   return String.valueOf(getCell(row, colNum).getBooleanCellValue());
		} catch (Exception e) {
			Log.error("Error occured while reading cell data" + e.getMessage());
			return "";
		}
	}

	public static XSSFCell getCell(XSSFRow row, int ColNum) {
		return row.getCell(ColNum);
	}

	public static String getNumericCellValue(XSSFRow row, int colNum) {
		try {
			String CellData = String.valueOf((int) getCell(row, colNum).getNumericCellValue());
			return CellData;
		} catch (Exception e) {
			Log.error("Error occured while reading cell data" + e.getMessage());
			return "";
		}
	}

	public static XSSFSheet getSheet(String sheetName) {
		return excelWBook.getSheet(sheetName);
	}

	public static int getLastRow(String sheetName) {
		return excelWBook.getSheet(sheetName).getLastRowNum();
	}


	public static XSSFRow getRowData(String sheetName, int rowNumber) {
		XSSFSheet sheet = getSheet(sheetName);
		return sheet.getRow(rowNumber);
	}
	public static int getColumnNumber(String columnName) {
		 CellAddress address = null;
	        int i=0;
	        for (Iterator<org.apache.poi.ss.usermodel.Row> rit = excelWSheet.rowIterator(); rit.hasNext();) {
	               org.apache.poi.ss.usermodel.Row row = rit.next();
	               for (Iterator<org.apache.poi.ss.usermodel.Cell> cit = row.cellIterator(); cit.hasNext();) {
	                     org.apache.poi.ss.usermodel.Cell cell = cit.next();
	                     System.out.println(cell.getStringCellValue());
	                     System.out.println(cell.getAddress().getColumn());
	                     if (cell.getStringCellValue().trim().equals(columnName)) {
	                            address = cell.getAddress();
	                             i=1;
	                            break;
	                     }

	               }
	               if(i==1){
	            	   break;
	               }
	        }
	        return address.getColumn();
		
	}

}