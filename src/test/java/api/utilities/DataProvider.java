package api.utilities;
import org.testng.annotations.*;

import java.io.IOException;


public class DataProvider {

//    DataProvider reads the data from the excel file using Excel utilties and store it is array to pass the data to the testMethod, TestMethod will run the number of data present in it
    @org.testng.annotations.DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path=System.getProperty("user.dir")+"//TestData//UserData.xlsx";
        ExcelUtility excelUtility=new ExcelUtility(path);
        int rowCount=excelUtility.getRowCount("Sheet1");
        int colCount=excelUtility.getCellCount("Sheet1",1);
        String apiData[][]=new String[rowCount][colCount];

        for(int i=1;i<=rowCount;i++){
            for(int j=0;j<colCount;j++){
                apiData[i-1][j]=excelUtility.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }

    @org.testng.annotations.DataProvider(name = "Username")
    public String[] getUsernames() throws IOException {
        String path=System.getProperty("user.dir")+"//TestData//UserData.xlsx";
        ExcelUtility excelUtility=new ExcelUtility(path);
        int rowCount=excelUtility.getRowCount("Sheet1");
        String username[]=new String[rowCount];
        for(int i=1;i<=rowCount;i++){
            username[i-1]=excelUtility.getCellData("Sheet1",i,4);
        }
        return username;
    }

}
