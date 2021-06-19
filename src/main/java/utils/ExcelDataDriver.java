package utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Handles data driving of the tests from xlsx
 */

public class ExcelDataDriver {

    private Workbook workbook;
    private Sheet sheet;
    private int columnHeaders = 0;
    private DataFormatter df;

    //
    //Constructor input parameters : Datafile path & Excel data sheet index number
    //
    public ExcelDataDriver(String dataFile, int dataSheet) {
        try {
            //Using workbookFactory to setup our workbook - assumes calling method has checked for existence
            workbook = WorkbookFactory.create(new FileInputStream(dataFile));

            //Get given datasheet from the workbook
            sheet = workbook.getSheetAt(dataSheet);

            //Data formatter for reading the cells
            this.df = new DataFormatter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    //Constructor input parameters : Datafile path & Excel data sheet iname
    //
    public ExcelDataDriver(String dataFile, String dataSheetName) {
        try {
            //Using workbookFactory to setup our workbook - assumes calling method has checked for existence
            workbook = WorkbookFactory.create(new FileInputStream(dataFile));

            //Get given datasheet from the workbook
            sheet = workbook.getSheet(dataSheetName);

            //Data formatter for reading the cells
            this.df = new DataFormatter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    //Check that we do not have a blank row
    //
    private boolean isRowEmpty(Row row) {
        if (row != null) {
            for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c);
                if (cell != null && df.formatCellValue(cell) != "")
                    return false;
            }
        }
        return true;
    }

    // 
    // Returns a count of the tests/ used rows
    //
    private int testCount() {
        int lastRowIndex = -1;
        if (sheet.getPhysicalNumberOfRows() > 0) {
            //getLastRowNum() actually returns an index, not a row number
            lastRowIndex = sheet.getLastRowNum();

            //Start at end of spreadsheet and work our way backwards until we find a row having data
            for (; lastRowIndex >= 0; lastRowIndex--) {
                Row row = sheet.getRow(lastRowIndex);
                if (!isRowEmpty(row)) {
                    break;
                }
            }
        }
        return lastRowIndex;
    }

    //
    //Return the data as a TestNG object
    //
    public Object[][] createDataProvider() {
        HashMap<String, String> map = new HashMap<>();
        Object[][] dataSet = new Object[testCount()][1];
        for (int i = 1; i <= testCount(); i++) {
            HashMap<String, String> dataRow = returnDataRow(i);
            map = new HashMap<>();
            for (String key : dataRow.keySet()) {
                map.put(key, dataRow.get(key));
            }
            dataSet[i - 1][0] = map;
        }
        return dataSet;
    }

    private HashMap returnDataRow(int rowNumber) {
        HashMap<String, String> dataSet = new HashMap<>();

        //Check if the row number contains any data
        if (isRowEmpty(sheet.getRow(rowNumber))) {
            System.out.println("Looks like we are at the end of the data file, row or found a blank row at row number : [" +
                    rowNumber + "]");
            dataSet.put("EndOfData", "EndOfData");
            return dataSet;
        }

        //Use the column header row to iterate through to ensure we capture all the column titles
        Row row = sheet.getRow(columnHeaders);
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell headerCell = cellIterator.next();
            Cell cell = sheet.getRow(rowNumber).getCell(headerCell.getColumnIndex());

            //If the cell is null then we have no data
            if (cell == null) {
                dataSet.put(headerCell.getStringCellValue(), "");
            } else {
                if (cell.getCellTypeEnum() == CellType.NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
                    //We need to fix the dates coming from Excel - assumes required format is dd/MM/yyyy
                    LocalDateTime dateTime = LocalDateTime.parse(String.valueOf(cell.getDateCellValue()),
                            DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy"));
                    dataSet.put(headerCell.getStringCellValue(), dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                } else {
                    //Use data formatter to read any cell type into string
                    dataSet.put(headerCell.getStringCellValue(), df.formatCellValue(cell));
                }
            }
        }
        return dataSet;
    }
}
