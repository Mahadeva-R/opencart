package org.opencart.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ExcelHelper {

    private XSSFWorkbook workbook;
    private String path;

    public ExcelHelper(String path) {
        this.path = path;
        loadWorkbook();
    }

    private void loadWorkbook() {
        try {
            File file = new File(path);

            if (!file.exists()) {
                workbook = new XSSFWorkbook();
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            }

            FileInputStream fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            fis.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load Excel file", e);
        }
    }

    private XSSFSheet getSheet(String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    public int getRowCount(String sheetName) {
        XSSFSheet sheet = getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    // Get Cell Count (based on header row)
    public int getCellCount(String sheetName, int rowNum) {
        XSSFSheet sheet = getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNum);

        if (row == null) return 0;

        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {

        XSSFSheet sheet = getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNum);

        if (row == null) return "";

        XSSFCell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) {

        try {
            XSSFSheet sheet = getSheet(sheetName);

            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            XSSFCell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(data);

            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to write data", e);
        }
    }

    public boolean isSheetExists(String sheetName) {
        return workbook.getSheet(sheetName) != null;
    }

    public boolean isRowExists(String sheetName, int rowNum) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        return sheet != null && sheet.getRow(rowNum) != null;
    }

    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
