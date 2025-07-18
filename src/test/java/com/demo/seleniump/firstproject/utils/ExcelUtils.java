package com.demo.seleniump.firstproject.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    private static String filePath;

    public static void loadExcel(String path, String sheetName) {
        try {
            InputStream file;
            if (path != null && !path.isEmpty()) {
                filePath = path;
                file = new FileInputStream(path);
            } else {
                filePath = "testdata\\TestData.xlsx";
                file = ExcelUtils.class.getClassLoader().getResourceAsStream(filePath);
                if (file == null) {
                    throw new FileNotFoundException("Excel file not found in resources folder: " + filePath);
                }
            }

            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
            file.close();  // Always close stream
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRowCount() {
        if (sheet == null) return 0;
        return sheet.getPhysicalNumberOfRows();
    }

    public static int getColCount(int rowNum) {
        if (sheet == null) return 0;
        Row row = sheet.getRow(rowNum);
        return row != null ? row.getLastCellNum() : 0;
    }

    public static String getCellData(int rowNum, int colNum) {
        if (sheet == null) return "";
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";
        return new DataFormatter().formatCellValue(cell);
    }

    public static int getResultColumnIndex() {
        if (sheet == null) return -1;
        Row header = sheet.getRow(0);
        if (header == null) return -1;

        for (Cell cell : header) {
            if (cell.getStringCellValue().equalsIgnoreCase("Result")) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }

    public static Object[][] getFilteredData(String path, String sheetName) {
        loadExcel(path, sheetName);
        int rows = getRowCount();
        int cols = getColCount(0);
        int runColumn = 0; // "Run" is first column

        // We want to include all columns except Run (first column)
        int actualCols = cols - 1;

        int validCount = 0;
        for (int i = 1; i < rows; i++) {
            if (getCellData(i, runColumn).equalsIgnoreCase("Y")) {
                validCount++;
            }
        }

        Object[][] data = new Object[validCount][actualCols];

        int index = 0;
        for (int i = 1; i < rows; i++) {
            if (getCellData(i, runColumn).equalsIgnoreCase("Y")) {
                for (int j = 1; j < cols; j++) {  // start from 1 to skip Run column
                    data[index][j - 1] = getCellData(i, j);
                }
                index++;
            }
        }
        return data;
    }
}
