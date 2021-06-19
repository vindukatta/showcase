package Onboarding_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.*;
import utils.ExcelDataDriver;
import testUtils.TestInitialise;
import utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanySicCodes extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetailsForm businessDetailsForm;
    private PersonalDetailsForm personalDetailsForm;
    private OngoingSourceOfWealth ongoingSourceOfWealth;
    private SourceOfFunds sourceOfFunds;
    private TransactionDetails transactionDetails;
    private InitialWealth initialWealth;

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "CompanySicCodes").createDataProvider();
    }

    @BeforeMethod
    public void before(){
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetailsForm = new BusinessDetailsForm(driver);
        personalDetailsForm = new PersonalDetailsForm(driver);
        ongoingSourceOfWealth = new OngoingSourceOfWealth(driver);
        sourceOfFunds = new SourceOfFunds(driver);
        transactionDetails = new TransactionDetails(driver);
        initialWealth = new InitialWealth(driver);
    }

    @Test(dataProvider = "dataSet")
    public void CUICE4106(HashMap<String, String> dataSet){

        setUp.passThroughSetUp();
        businessSearch.populateBusinessSearch(dataSet.get("CompanyCode"));

        List<String> expected = new ArrayList<>();
        for(int i = 1; i <= 4; i++){
            if(dataSet.get("SicCode" + i) != "")
                expected.add(dataSet.get("SicCode" + i));
        }

        businessSearch.verifyCompanySicCodesAndDescription(expected);
        businessDetailsForm.passThroughBusinessDetailsForm();
        personalDetailsForm.passThroughPersonalDetailsForm();
        ongoingSourceOfWealth.processOngoingSourceOfWealth();
        initialWealth.passThroughInitalWealth();
        sourceOfFunds.processSourceOfFunds();
        transactionDetails.passThroughTransactionDetails();
        transactionDetails.processSummaryPage();
    }
}