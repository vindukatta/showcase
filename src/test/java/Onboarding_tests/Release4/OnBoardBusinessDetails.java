package Onboarding_tests.Release4;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.BusinessSearch;
import pageObjects.Onboarding.Release4.BusinessDetails;
import pageObjects.Onboarding.SetUp;
import testUtils.TestInitialise;
import utils.Constants;
import utils.ExcelDataDriver;

import java.util.HashMap;

public class OnBoardBusinessDetails extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetails businessDetails;

    @BeforeMethod
    public void before() {
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetails = new BusinessDetails(driver);
    }

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardingDemo1").createDataProvider();
    }

    @Test(dataProvider = "dataSet")
    public void CUICE7297(HashMap<String, String> dataSet) {
        setUp.setUpWithData(dataSet.get("Mobile"), dataSet.get("Email"));
        businessSearch.populateBusinessSearch(dataSet.get("Company"));
        businessDetails.populateBusinessDetailsForm(false,dataSet.get("TradingName"),false,
                "Domestic Exports",false,"",
                Integer.parseInt(dataSet.get("NoOfEmployess")));
    }
}