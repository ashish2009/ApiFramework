package com.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ExcelHelper {

    File file = new File("/Users/ashishkumar/Downloads/bday.xlsx");
    FileInputStream fis;
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;
    XSSFRow row;

    public void readFromExcel(String sheetName){
        try {
            fis = new FileInputStream(file);
            xssfWorkbook = new XSSFWorkbook(fis);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            System.out.println(xssfSheet.getLastRowNum());
//            System.out.println(xssfSheet.getRow(0));
            Iterator<Row> rowIterator = xssfSheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    switch(cell.getCellType()){
                        case STRING:
                            System.out.println(cell.getStringCellValue());
                            break;

                        case NUMERIC:
                            System.out.println(cell.getDateCellValue());
                            break;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExcelHelper helper = new ExcelHelper();
        helper.readFromExcel("Bdays");
    }
}
