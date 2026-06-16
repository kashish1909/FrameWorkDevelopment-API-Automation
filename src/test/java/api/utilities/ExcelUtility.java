package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle cellStyle;
    String path;

    public ExcelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetname) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetname);
        int totalRows=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return totalRows;
    }

    public int getCellCount(String sheetname,int rowNum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetname);
        row=sheet.getRow(rowNum);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetname,int rowNum,int cellNum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetname);
        row=sheet.getRow(rowNum);
        cell=row.getCell(cellNum);
        DataFormatter dataFormatter=new DataFormatter();
        String data;
        try{
            data=dataFormatter.formatCellValue(cell); //returns the value as string
        }
        catch (Exception e){
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }
}

