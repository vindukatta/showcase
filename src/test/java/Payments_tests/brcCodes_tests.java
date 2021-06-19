package Payments_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.TestInitialise;
import pageObjects.Payments.AvailableBalance;

public class brcCodes_tests extends TestInitialise {

    private AvailableBalance availableBalance;

    @BeforeMethod
    public void beforeMethod() {
        availableBalance = new AvailableBalance(driver);
    }

    @Test(priority = 1 , description = "View payment success page" )
    public void CUICE6354 () {
        Assert.assertTrue(availableBalance.successPageDisplayed());
    }

    @Test(priority = 2 , description = "Faster payment placeholder disclaimer" )
    public void CUICE6355 () {
        Assert.assertTrue(availableBalance.disclaimerShown());
    }

    @Test(priority = 3 , description = "Done CTA on success page" )
    public void CUICE6356 () {
        Assert.assertTrue(availableBalance.doneButtonValidation());
    }// ETE

    @Test(priority = 4 , description = "Display relevant message for sent status brcs" )
    public void CUICE6829 () {
        Assert.assertTrue(availableBalance.sentStatusMessageCheck());
    }

    @Test(priority = 5 , description = "Display edit payment failure page" )
    public void CUICE7104 () { Assert.assertTrue(availableBalance.editStatusMessageCheck()); }

    @Test(priority = 6 , description = "Edit sort code, account number or name" )
    public void CUICE7105 () { Assert.assertTrue(availableBalance.editPayeeDetailsCode()); }

    @Test(priority = 7 , description = "Issue with available balance or limits" )
    public void CUICE7106 () {
        Assert.assertTrue(availableBalance.editPaymentDetailsCode());
    }

    @Test(priority = 8 , description = "Failure status brc check" )
    public void CUICE7122 () { Assert.assertTrue(availableBalance.failureStatusMessageCheck()); }

    @Test(priority = 9 , description = "Display Yes/No pop-up" )
    public void CUICE7078 () {
        Assert.assertTrue(availableBalance.duplicatePopUpDisplay());
    }

    @Test(priority = 10 , description = "Click No on yes/no pop-up" )
    public void CUICE7079 () { Assert.assertTrue(availableBalance.clickNoPopUp()); }

    @Test(priority = 11 , description = "Click Yes on yes/no pop-up" )
    public void CUICE7080 () {
        Assert.assertTrue(availableBalance.clickYesPopUp());
    }

    @Test(priority = 12 , description = "Display yes/no pop-up for bacs codes" )
    public void CUICE7211 () {
        Assert.assertTrue(availableBalance.bacsPopUpDisplay());
    }

    @Test(priority = 13 , description = "Click no on bacs yes/no pop up" )
    public void CUICE7212 () {
        Assert.assertTrue(availableBalance.bacsSelectNo());
    }

    @Test(priority = 13 , description = "Click Yes Happy Path for bacs" )
    public void CUICE7213 () {
        Assert.assertTrue(availableBalance.bacsSelectYes());
    }

    @Test(priority = 13 , description = "Display yes no pop-up for chaps" )
    public void CUICE7230 () {
        Assert.assertTrue(availableBalance.chapsPopUpDisplay());
    }

    @Test(priority = 13 , description = "chaps click yes happy path" )
    public void CUICE7232 () { Assert.assertTrue(availableBalance.chapsSelectYes()); }

    @Test(priority = 13 , description = "chaps click no on chaps pop up" )
    public void CUICE7231 () { Assert.assertTrue(availableBalance.chapsSelectNo()); }

    @Test(priority = 13 , description = "display yes/no pop-up for fraud" )
    public void CUICE7251 () {
        Assert.assertTrue(availableBalance.fruadPopUpDisplay());
    }

    @Test(priority = 13 , description = "click no on fraud pop-up" )
    public void CUICE7252 () { Assert.assertTrue(availableBalance.fruadSelectNo()); }

    @Test(priority = 13 , description = "Fraud issue after cut off display popup" )
    public void CUICE7253 () { Assert.assertTrue(availableBalance.fraudSelectYes()); }

    @Test(priority = 13 , description = "Agency bank scenario 1" )
    public void CUICE7254 () { Assert.assertTrue(availableBalance.agencyMainSelectYes()); }

    @Test(priority = 13 , description = "Agency bank scenario 2" )
    public void CUICE7255 () { Assert.assertTrue(availableBalance.agencySideSelectYes()); }

    @Test(priority = 13 , description = "Agency bank scenario 1" )
    public void CUICE7257 () { Assert.assertTrue(availableBalance.agencyOtheSelectYes()); }

    @Test(priority = 40, description = "Receive success status when attempting to send SO ")
    public void CUICE7964() { Assert.assertTrue(availableBalance.successStateBrcCheck()); }

    @Test(priority = 41, description = "Click CTA on Payment success page (SO)")
    public void CUICE7957() { Assert.assertTrue(availableBalance.clickDoneSOBrcCheck()); }

    @Test(priority = 42, description = "Receive failure status when attempting to send SO")
    public void CUICE8651() { Assert.assertTrue(availableBalance.failureStateBrcCheck()); }

    @Test(priority = 43, description = "Click CTA on Payment failure page (SO)")
    public void CUICE8653() { Assert.assertTrue(availableBalance.clickDoneFailSOBrcCheck()); }

    @Test(priority = 44, description = "FDP Payment Summary")
    public void CUICE9254() { Assert.assertTrue(availableBalance.futurePayments()); }

    @Test(priority = 45, description = "FDP Payment Summary_cancel")
    public void CUICE9255() { Assert.assertTrue(availableBalance.cancelFuturePayments()); }

    @Test(priority = 46, description = "Do not show error message when available balance exceeded")
    public void CUICE9312() { Assert.assertTrue(availableBalance.futurePaymentInsufficentFunds()); }

    @Test(priority = 47, description = "Change from FDP to immediate payment - error message is displayed")
    public void CUICE9313() { Assert.assertTrue(availableBalance.changeFromFDPToImmediate()); }

    @Test(priority = 48, description = "Display payment switch with three switches")
    public void CUICE9271() { Assert.assertTrue(availableBalance.displayPaymentSwitch()); }

    @Test(priority = 49, description = "Fields to display for'immdiate'switch")
    public void CUICE9272() { Assert.assertTrue(availableBalance.displayImmediateSwitch()); }

    @Test(priority = 50, description = "Fields to display for'Future'switch")
    public void CUICE9273() { Assert.assertTrue(availableBalance.displayFutureeSwitch()); }

    @Test(priority = 51, description = "Fields to display for'recurring'switch")
    public void CUICE9274() { Assert.assertTrue(availableBalance.displayRecurringSwitch()); }

    @Test(priority = 52, description = "Persist selected switch in PAYMENT TYPE SWITCH")
    public void CUICE9275() { Assert.assertTrue(availableBalance.PaymentTypeSwitch()); }

}
