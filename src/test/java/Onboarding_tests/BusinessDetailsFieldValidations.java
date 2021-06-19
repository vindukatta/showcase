package Onboarding_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Onboarding.BusinessDetailsForm;
import pageObjects.Onboarding.BusinessSearch;
import pageObjects.Onboarding.SetUp;
import testUtils.TestInitialise;

public class BusinessDetailsFieldValidations extends TestInitialise {
    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetailsForm businessDetailsForm;
    String[] employeeValuesInvalid1 = {"0","009123","0000000","00!23","12.45","12-17","10.","1 2"};
    String employeeValuesInvalid2 = "!@£$%^&*()_+}{|~?><.,";
    String[] validset = {"1","9999999","1234567","1000000"};

    @BeforeMethod
    public void before(){
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetailsForm = new BusinessDetailsForm(driver);

    }

    @Test
    public void CUICE8002() {

        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();


        businessDetailsForm.businessDetailsMandatoryFields(true,
                "Additional info", 1);

        System.out.println("Set 1 : Invalid employee details values: Verify Next button is disabled with no data in the form");
        for(String employeeNo : employeeValuesInvalid1) {
            businessDetailsForm.setNumberofemployee(employeeNo);
            Assert.assertTrue(!businessDetailsForm.isNextButtonEnabled());
        }

        System.out.println("Set 2 : Invalid employee details values: Verify Next button is disabled with no data in the form");
        for(int i = 0; i < employeeValuesInvalid2.length(); i++){
            String employeeValue = String.valueOf(employeeValuesInvalid2.charAt(i));
            businessDetailsForm.setNumberofemployee("123"+ employeeValue);
            Assert.assertTrue(!businessDetailsForm.isNextButtonEnabled());
        }

        System.out.println("Set 3: Valid employee details values: Verify Next button is enabled with no data in the form");
        for(String employeeNo : validset) {
            businessDetailsForm.setNumberofemployee(employeeNo);
            Assert.assertTrue(businessDetailsForm.isNextButtonEnabled());
        }
    }
}
