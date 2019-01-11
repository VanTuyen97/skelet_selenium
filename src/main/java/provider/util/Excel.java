/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author vantuyen361
 */
public final class Excel {

    private Workbook workbook;
    private Sheet sheet;
    private String path;

    public Excel(String path) throws FileNotFoundException, IOException {
        this.init(path);
    }

    /**
     * Initialization object
     * @param path the path of xlsx file
     * @throws java.io.FileNotFoundException
     * @throws IOException
     */
    protected void init(String path) throws FileNotFoundException, IOException {
        this.path = path;
        InputStream input = new FileInputStream(path);
        this.workbook = WorkbookFactory.create(input);
        this.activeSheet(0);
    }

    /**
     * Get the number of spreadsheets in the Excel file
     * @return the number of sheet
     */
    public int numberSheet() {
        return this.workbook.getNumberOfSheets();
    }

    /**
     * Get the number of row in active sheet
     * @return the number of row in active sheet.
     */
    public int numberRowOFSheet() {
        if (sheet == null) {
            return 0;
        }
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * Get value of a cell to specific in active sheet.
     * @param numberRow the order of row.
     * @param numberColumns the order of column.
     * @return String value of cell to specific.
     */
    public String valueOfCell(int numberRow, int numberColumns) {
        Row row = sheet.getRow(numberRow);
        Cell cell = row.getCell(numberColumns);
        if (cell == null) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * Activate a sheet to use.
     * @param sheet the order of sheet
     * @throws IllegalArgumentException Activate a sheet to use.
     */
    public void activeSheet(int sheet) throws IllegalArgumentException {
        this.sheet = this.workbook.getSheetAt(sheet);
    }

    /**
     * Update file excel.
     * @throws java.io.FileNotFoundException
     */
    public void save() throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(this.path);
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * Write value to cell
     * @param numberRow
     * @param message
     */
    public void addValueCell(int numberRow, int columns, String message) {
        Row row = sheet.getRow(numberRow);
        Cell cell = row.createCell(columns);
        cell.setCellValue(message);

        //set style of cell
//        CellStyle style = this.workbook.createCellStyle();
//        Font font = this.workbook.createFont();
//        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
//        style.setFont(font);
//        cell.setCellStyle(style);
    }
}
