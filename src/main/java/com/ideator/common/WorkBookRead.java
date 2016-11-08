package com.ideator.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkBookRead {

	public String[] ReadsheetLogin() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		String userName = sheet1.getRow(0).getCell(0).getStringCellValue();
		String passWord = sheet1.getRow(0).getCell(1).getStringCellValue();
		wb.close();
		return new String[] { userName, passWord };
	}

	public String[][] ReadsheetTCM() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(1);
		int rowCount = sheet1.getPhysicalNumberOfRows();
		int ColumnCount = sheet1.getRow(0).getPhysicalNumberOfCells();
		int row = 0;
		int column = 0;
		wb.close();
		String data[][] = new String[rowCount][ColumnCount];
		for (row = 1; row < rowCount; row++) {
			for (column = 1; column < ColumnCount; column++) {

				try {
					data[row][column] = sheet1.getRow(row).getCell(column).getStringCellValue();
				} catch (Exception e) {
				}
			}
		}
		return data;

	}
	
	
	public String[][] ReadsheetTMM1(int rowIndex) throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet3 = wb.getSheetAt(3);
		int rowCount = sheet3.getPhysicalNumberOfRows();
		int columnCount = sheet3.getRow(0).getPhysicalNumberOfCells();
		int row = 0;
		int column = 0;
		wb.close();
		String data[][] = new String[rowCount][columnCount];
		for (row = rowIndex; row < rowCount; row++) {
			for (column = 1; column < columnCount; column++) {
				try {
					data[row][column] = sheet3.getRow(row).getCell(column).getStringCellValue();
					System.out.println(data[row][column]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;

	}
	

	public String[][] ReadsheetTMM() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet3 = wb.getSheetAt(3);
		int rowCount = sheet3.getPhysicalNumberOfRows();
		int columnCount = sheet3.getRow(0).getPhysicalNumberOfCells();
		int row = 0;
		int column = 0;
		wb.close();
		String data[][] = new String[rowCount][columnCount];
		for (row = 1; row < rowCount; row++) {
			for (column = 1; column < columnCount; column++) {
				try {
					data[row][column] = sheet3.getRow(row).getCell(column).getStringCellValue();
					System.out.println(data[row][column]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;

	}

	

	public ArrayList<Integer> findRow(String cellContent) throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(3);
		ArrayList<Integer> classRowNums =  new ArrayList<Integer>();
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						int num=row.getRowNum();
						classRowNums.add(num);
					}
				}
				wb.close();
			}
		}
		return classRowNums;
	}

	private int findMergedregion(XSSFSheet sheet, int rowIndex) {
		int sheetmergedCount = sheet.getNumMergedRegions();
		for (int i = rowIndex; i < sheetmergedCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (rowIndex == firstRow) {
				return lastRow;
			}
		}
		return 0;
	}

	public int[] getRow_Column_Count(int sheetNumber) throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/IdeatorData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(sheetNumber);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int ColumnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		wb.close();
		return new int[] { rowCount, ColumnCount };
	}

	
}
