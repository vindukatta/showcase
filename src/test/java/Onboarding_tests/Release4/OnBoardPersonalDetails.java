package Onboarding_tests.Release4;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.BusinessSearch;
import pageObjects.Onboarding.Release4.BusinessDetails;
import pageObjects.Onboarding.Release4.PersonalDetails;
import pageObjects.Onboarding.SetUp;
import testUtils.TestInitialise;
import utils.Constants;
import utils.ExcelDataDriver;

import java.util.HashMap;

public class OnBoardPersonalDetails extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetails businessDetails;
    private PersonalDetails personalDetails;

    @BeforeMethod
    public void before() {
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetails = new BusinessDetails(driver);
        personalDetails = new PersonalDetails(driver);
    }

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardingDemo1").createDataProvider();
    }


    @Test(dataProvider = "dataSet")
    public void CUICE10540(HashMap<String, String> dataSet) {
        setUp.setUpWithData(dataSet.get("Mobile"), dataSet.get("Email"));
        businessSearch.populateBusinessSearch(dataSet.get("Company"));
        businessDetails.populateBusinessDetailsForm(false,dataSet.get("TradingName"),false,
                "Nature of business",false,"",
                Integer.parseInt(dataSet.get("NoOfEmployess")));

        personalDetails.populatePersonalDetailsForm(2,false,"BN1 2RL");

    }
}