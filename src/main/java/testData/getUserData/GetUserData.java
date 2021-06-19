package testData.getUserData;

import utils.Constants;
import utils.ExcelUtils;

import java.util.HashMap;

public class GetUserData {

    public static HashMap getFirstTimeUser(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_User_Data, "Users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int row = 2;

        HashMap<String, String> map = new HashMap<>();
        map.put("Username", ExcelUtils.getCellData(row,2));
        map.put("Password", ExcelUtils.getCellData(row,3));

        return map;
    }

    public static HashMap getNoTransactionUser(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_User_Data, "Users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int row = 3;

        HashMap<String, String> map = new HashMap<>();
        map.put("Username", ExcelUtils.getCellData(row,2));
        map.put("Password", ExcelUtils.getCellData(row,3));

        return map;
    }
}
