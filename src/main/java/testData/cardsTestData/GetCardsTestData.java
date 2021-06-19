package testData.cardsTestData;

import utils.Constants;
import utils.ExcelUtils;

public class GetCardsTestData {

    public static String getSuccssfullCardNumber(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_E2E_Tests, "Cards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExcelUtils.getCellData(1,1);
    }

    public static String getAlreadyActivatedCardNumber(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_E2E_Tests, "Cards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExcelUtils.getCellData(2,1);
    }

    public static String getCardNotLiveCardNumber(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_E2E_Tests, "Cards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExcelUtils.getCellData(3,1);
    }

    public static String getCardIncorrectNumber(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_E2E_Tests, "Cards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int Min = 4;
        int Max = 5;
        int row = Min + (int)(Math.random() * ((Max - Min) + 1));
        return ExcelUtils.getCellData(row,1);
    }

    public static String getCardTimeoutNumber(){
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_E2E_Tests, "Cards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExcelUtils.getCellData(6, 1);
    }
}
