package Onboarding_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Onboarding.BusinessDetailsForm;
import pageObjects.Onboarding.BusinessSearch;
import pageObjects.Onboarding.SetUp;
import testUtils.TestInitialise;

public class businessFormTests extends TestInitialise {

    private SetUp setUp;
    private BusinessSearch businessSearch;
    private BusinessDetailsForm business;

    @BeforeMethod
    public void beforeMethod() {
        setUp = new SetUp(driver);
        businessSearch = new BusinessSearch(driver);
        business = new BusinessDetailsForm(driver);
    }

    @Test(priority = 1, description = "Display of Trading Address/Business Address Checkboxes" )
    public void CUICE4581(){
        Assert.assertTrue(business.noDefaultCheckboxSelection());
    }

    @Test(priority = 2, description = "Trading Address Same as business address" )
    public void CUICE4583(){
        Assert.assertTrue(business.tradingAddressSameAsRegisteredBusinessAddress());
    }

    @Test(priority = 3, description = "Trading Address Different as business address" )
    public void CUICE4584(){
        Assert.assertTrue(business.tradingAddressDifferentFromRegisteredBusinessAddress());
    }

    @Test(priority = 4, description = "Trading Address Different as business address, no address found" )
    public void CUICE4585(){
        Assert.assertTrue(business.addressNotFound());//FIXME: Will fail because of wiremock
    }

    @Test(priority = 5, description = "Display Additional Details")
    public void CUICE5158(){
        Assert.assertTrue(business.additionalDetailsDisplayed());
    }

    @Test(priority = 6, description = "Additional Details Keyboard")
    public void CUICE5159(){
        Assert.assertTrue(business.additionalDetailsKeyboard());
    }

    @Test(priority = 7, description = "Additional Details Validation")
    public void CUICE5160(){
        Assert.assertTrue(business.additionalDetailsNoCharacters());
        Assert.assertTrue(business.additionalDetailsLessThanTwoCharacters());
        Assert.assertTrue(business.additionalDetailsMoreThan255Characters());
        Assert.assertTrue(business.additionalDetailsValidEntry());
    }

    @Test(priority = 8, description = "Find full address through postcode entry")
    public void CUICE4395(){ Assert.assertTrue(business.findAddressPostcode()); }

    @Test(priority = 9, description = "Find full address  through first line of address")
    public void CUICE4394(){ Assert.assertTrue(business.findAddress()); }

    @Test(priority = 10, description = "Invalid address lookup")
    public void CUICE4391(){ Assert.assertTrue(business.findInvalidAddress()); }

}
