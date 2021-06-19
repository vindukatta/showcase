package Onboarding_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.*;
import utils.ExcelDataDriver;
import testUtils.TestInitialise;
import utils.Constants;
import java.util.HashMap;

public class OnBoardNewUser extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetailsForm businessDetailsForm;
    private PersonalDetailsForm personalDetailsForm;
    private OngoingSourceOfWealth ongoingSourceOfWealth;
    private SourceOfFunds sourceOfFunds;
    private TransactionDetails transactionDetails;
    private InitialWealth initialWealth;

    @BeforeMethod
    public void before() {
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetailsForm = new BusinessDetailsForm(driver);
        personalDetailsForm = new PersonalDetailsForm(driver);
        ongoingSourceOfWealth = new OngoingSourceOfWealth(driver);
        sourceOfFunds = new SourceOfFunds(driver);
        transactionDetails = new TransactionDetails(driver);
        initialWealth = new InitialWealth(driver);
    }

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardNewUser").createDataProvider();
    }


    @Test(dataProvider = "dataSet")
    public void onBoardNewUser(HashMap<String, String> dataSet) {
        setUp.setUpWithData(dataSet.get("Mobile"), dataSet.get("Email"));
        businessSearch.populateBusinessSearch(dataSet.get("Company"));
        businessDetailsForm.populateBusinessDetailsForm(dataSet.get("TradingName"), Integer.parseInt(dataSet.get("NoOfEmployess")));
        personalDetailsForm.personalDetailsWitData(dataSet.get("ResidentialAddress"));
        ongoingSourceOfWealth.populateSourceOfWealth(dataSet.get("AnnualTurnover"), dataSet.get("AnnualTurnoverFunds"));

        if (dataSet.get("Scenario").equalsIgnoreCase("CompanyLessThan3YOld")) {
            initialWealth.populateInitialWealth(dataSet.get("InitialAmount"), dataSet.get("InitialFunds"));
        }

        sourceOfFunds.populateSourceOfFunds(dataSet.get("DepositAmount"), dataSet.get("DepositFunds"), dataSet.get("TotalDepositAmount"));
        transactionDetails.populateTransactionDetails(dataSet.get("TransactionAccountUsage"), dataSet.get("TransactionTypes"));
        transactionDetails.processSummaryPage();

    }
}