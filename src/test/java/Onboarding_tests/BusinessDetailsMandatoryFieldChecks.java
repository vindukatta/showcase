package Onboarding_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Onboarding.BusinessDetailsForm;
import pageObjects.Onboarding.BusinessSearch;
import pageObjects.Onboarding.PersonalDetailsForm;
import pageObjects.Onboarding.SetUp;
import testUtils.TestInitialise;

public class BusinessDetailsMandatoryFieldChecks extends TestInitialise {
    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetailsForm businessDetailsForm;
    private PersonalDetailsForm personalDetailsForm;

    @BeforeMethod
    public void before(){
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        businessDetailsForm = new BusinessDetailsForm(driver);
        personalDetailsForm = new PersonalDetailsForm(driver);
    }

    @Test
    public void BusinessDetailsMandatoryFieldChecks() {

        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();

        System.out.println("Check 1 : Verify Next button is disabled with trading address option is not selected");
        businessDetailsForm.businessDetailsMandatoryFields(false,
                "Additional info",1);
        Assert.assertTrue(!businessDetailsForm.isNextButtonEnabled());
        businessDetailsForm.clickBackToCompanyDetails();
        businessSearch.passThroughBusinessSearch();


        System.out.println("Check 2 : Verify Next button is disabled with additional details field empty");
        businessDetailsForm.businessDetailsMandatoryFields(true,
                "", 1);
        Assert.assertTrue(!businessDetailsForm.isNextButtonEnabled());
        businessDetailsForm.clickBackToCompanyDetails();
        businessSearch.passThroughBusinessSearch();


        System.out.println("Check 3 : Verify Next button is disabled with employee number set to 0");
        businessDetailsForm.businessDetailsMandatoryFields(true,
                "Additional info", 0);
        Assert.assertTrue(!businessDetailsForm.isNextButtonEnabled());

    }
}
