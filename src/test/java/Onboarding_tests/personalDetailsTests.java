package Onboarding_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Onboarding.PersonalDetailsForm;
import testUtils.TestInitialise;

public class personalDetailsTests extends TestInitialise {

    private PersonalDetailsForm details;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        details = new PersonalDetailsForm(driver);
    }

    @Test(priority = 1, description = "Information Prepopulated and Displayed to User")
    public void CUICE4142(){
        Assert.assertTrue(details.personalDetailsDisplayed());
    }

//    @Test(priority = 2, description = "Non-Editing of Pre-populated Information")
//    public void CUICE4141(){
//        Assert.assertTrue(details.personalDetailsNotEditible());
//    }

    @Test(priority = 3, description = "Navigate Back To Company Search Screen")
    public void CUICE4144(){
        Assert.assertTrue(details.navigateBackToBusinessSearch());
    }

}
