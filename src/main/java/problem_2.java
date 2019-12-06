
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class problem_2 {

    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;

    public void readExcel(String sheetName){
        String fileName = "";
        try {
            FileInputStream fis = new FileInputStream(new File(fileName));
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch(cell.getCellType()){
                        case STRING:
                            System.out.println(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue());
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }
}
