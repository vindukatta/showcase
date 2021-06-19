package Onboarding_tests.DemoTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.*;
import pageObjects.Onboarding.Release4.BusinessDetails;
import pageObjects.Onboarding.Release4.BusinessFinancialDetails;
import pageObjects.Onboarding.Release4.PersonalDetails;
import testUtils.TestInitialise;
import utils.Constants;
import utils.ExcelDataDriver;

import java.util.HashMap;

public class OnBoardNewUserCRNMoreThan3Y extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetails businessDetails;
    private PersonalDetails personalDetails;
    private BusinessFinancialDetails businessFinancialDetails;
    private TransactionDetails transactionDetails;

    @BeforeMethod
    public void before() {
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetails = new BusinessDetails(driver);
        personalDetails = new PersonalDetails(driver);
        businessFinancialDetails = new BusinessFinancialDetails(driver);
        transactionDetails = new TransactionDetails(driver);
    }

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardingDemo1").createDataProvider();
    }


    @Test(dataProvider = "dataSet")
    public void OnBoardNewUserCRNMoreThan3Y(HashMap<String, String> dataSet) {
        setUp.setUpWithData(dataSet.get("Mobile"), dataSet.get("Email"));
        businessSearch.populateBusinessSearch(dataSet.get("Company"));
        businessDetails.populateBusinessDetailsForm(false,dataSet.get("TradingName"),false,
                "Domestic Exports",false,"",
                Integer.parseInt(dataSet.get("NoOfEmployess")));

        personalDetails.populatePersonalDetailsForm(2,true,"BN1 2RL");

        if (dataSet.get("Scenario").equalsIgnoreCase("CompanyLessThan3YOld")) {
            businessFinancialDetails.populateInitialSourceOfWealth(dataSet.get("InitialAmount"), dataSet.get("InitialFunds"));
        }

        businessFinancialDetails.populateOngoingSourceOfWealth(dataSet.get("AnnualTurnover"), dataSet.get("AnnualTurnoverFunds"));
        businessFinancialDetails.populateSourceOfFunds(dataSet.get("DepositAmount"), dataSet.get("DepositFunds"));
        businessFinancialDetails.populateTransactionDetails(dataSet.get("TransactionAccountUsage"), dataSet.get("TransactionTypes"),dataSet.get("TotalDepositAmount"));

        transactionDetails.processSummaryPage();

    }
}