package Onboarding_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Onboarding.*;
import testUtils.TestInitialise;

public class PersonalDetailsMandatoryFieldChecks extends TestInitialise {
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
    public void PersonalDetailsMandatoryFieldChecks() {

        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();
        businessDetailsForm.passThroughBusinessDetailsForm();

        //TestCase 1:
        System.out.println("Check 1 : Verify Next button is disabled with no data in the form");
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());


        //Testcase 2 : skip dob
        System.out.println("Check 2 : Verify Next button is disabled with date of birth set to blank");
        personalDetailsForm.personalDetailsMandatoryFields(false,true,false);
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());
        personalDetailsForm.clickBackToBusinessDetailsScreen();
        businessDetailsForm.clickNext();


        //TestCase 3 : skip address
        System.out.println("Check 3 : Verify Next button is disabled with address option skipped");
        personalDetailsForm.personalDetailsMandatoryFields(true,false,false);
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());
        personalDetailsForm.clickBackToBusinessDetailsScreen();
        businessDetailsForm.clickBackToCompanyDetails();

        //TestCase 4 : skip salutation field
        System.out.println("Check 4 : Verify Next button is disabled with salutation option skipped");
        businessSearch.populateBusinessSearch("05954781");
        businessDetailsForm.passThroughBusinessDetailsForm();
        personalDetailsForm.personalDetailsMandatoryFields(true,true,false);
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());
        personalDetailsForm.clickBackToBusinessDetailsScreen();
        businessDetailsForm.clickNext();

        //TestCase 5 : set salutation field and skip dob
        System.out.println("Check 5 : Verify Next button is disabled with salutation option set and dob skipped");
        personalDetailsForm.personalDetailsMandatoryFields(false,true,true);
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());
        personalDetailsForm.clickBackToBusinessDetailsScreen();
        businessDetailsForm.clickNext();

        //TestCase 6 : set salutation field and skip address
        System.out.println("Check 6 : Verify Next button is disabled with salutation option set and dob skipped");
        personalDetailsForm.personalDetailsMandatoryFields(true,false,true);
        Assert.assertTrue(!personalDetailsForm.isNextButtonEnabled());
    }
}
