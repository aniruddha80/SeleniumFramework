package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	

	public static List<ArrayList<String>> readExcel(String path, int sheetid) throws IOException {
		
		List<ArrayList<String>> completedata = new ArrayList<ArrayList<String>>();

		try {
			// String format[] = path.split("\\.");
			File filepath = new File(path);
			// FileInputStream file = new FileInputStream(new File(path));
			FileInputStream file = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(sheetid);
			Path excelpath = Paths.get(path);
			int getColumnCount=sheet.getRow(0).getLastCellNum();
			String excelfileString = excelpath.getFileName().toString();

			
			if (excelfileString.endsWith("xlsx")) {
				
				Iterator<Row> rowIterator = sheet.iterator();
				
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					ArrayList<String> data = new ArrayList<String>();

					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						 if(cell.getStringCellValue().equals("")) {
							 data.add(" ");
						 }
						 else {
							data.add(cell.getStringCellValue());
						 }
							
						}
					completedata.add(data);
					}
					file.close();
				}
			} 
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return completedata;

	}
	
	public static int getColumnCount(String path, int sheetid) throws IOException {
		File filepath = new File(path);
		// FileInputStream file = new FileInputStream(new File(path));
		FileInputStream file = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(sheetid);
		Path excelpath = Paths.get(path);
		int ColumnCount=sheet.getRow(0).getLastCellNum();
		
		return ColumnCount;
		
	}

}
