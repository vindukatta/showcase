package Payments_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.TestInitialise;
import pageObjects.Payments.DirectDebit;

public class directDebit_tests extends TestInitialise {

    private DirectDebit directDebit;
    @BeforeMethod
    public void beforeMethod() {
        directDebit = new DirectDebit(driver);
    }

    @Test(priority = 1, description = "Direct debit list empty state")
    public void CUICE6874() {
        Assert.assertTrue(directDebit.emptyState());
    }

    @Test(priority = 2, description = "Back Button from manage scheduled payments")
    public void CUICE6875() {
        Assert.assertTrue(directDebit.BBValidation());
    }

    @Test(priority = 3, description = "Informative text is shown")
    public void CUICE6876() {
        Assert.assertTrue(directDebit.isInformativeTextShown());
    }

    @Test(priority = 4, description = "Direct debit list display")
    public void CUICE6862() {Assert.assertTrue(directDebit.displayDD());}

    @Test(priority = 5, description = "Failure - display warning in snackbar")
    public void CUICE7038() {Assert.assertTrue(directDebit.isFailureErrMsgDisplayed());}

    @Test(priority = 6, description = "Network error message")
    public void CUICE7040() {Assert.assertTrue(directDebit.isNetworkErrMsgDisplayed());}

    @Test(priority = 6, description = "Retry - Display warning in snackbar")
    public void CUICE7039() {Assert.assertTrue(directDebit.isRetryErrMsgDisplayed());}

    @Test(priority = 7, description = "Navigate to direct debit details page")
    public void CUICE7051() {Assert.assertTrue(directDebit.loadDDDetails());}

    @Test(priority = 8, description = "Direct debit details page view")
    public void CUICE7052() {Assert.assertTrue(directDebit.viewDDPage());}

    @Test(priority = 9, description = "Back button from direct debits details")
    public void CUICE7053() {Assert.assertTrue(directDebit.backFromDetailsPage());}

    @Test(priority = 10, description = "Cancel Direct Debit is shown")
    public void CUICE7054() {Assert.assertTrue(directDebit.isCancelDisplayed());}

    @Test(priority = 11, description = "Cancel Direct Debit Pop-Up")
    public void CUICE7181() {Assert.assertTrue(directDebit.cancelPopupVerification());}

    @Test(priority = 12, description = "Do not delete Direct Debit")
    public void CUICE7182() {Assert.assertTrue(directDebit.doNotCancelDD());}

    @Test(priority = 13, description = "Success direct debit deletion")
    public void CUICE7183() {Assert.assertTrue(directDebit.successfulDDDelete());}

    @Test(priority = 14, description = "Partial Success direct debit deletion")
    public void CUICE7184() {Assert.assertTrue(directDebit.partialSuccessDelete());}

    @Test(priority = 15, description = "failure direct debit deletion")
    public void CUICE7185() {Assert.assertTrue(directDebit.failureUnableToDelete());}

    @Test(priority = 16, description = "Already cancelled direct debit deletion")
    public void CUICE7186() {Assert.assertTrue(directDebit.alreadyDeletedDD());}

    @Test(priority = 17, description = "View new direct debit details")
    public void CUICE7235() {Assert.assertTrue(directDebit.newDDDetailsScreen());}

    @Test(priority = 18, description = "Cancel new direct debits")
    public void CUICE7236() {Assert.assertTrue(directDebit.cancelNewDD());}

    @Test(priority = 19, description = "View new dd")
    public void CUICE7234() {Assert.assertTrue(directDebit.isNewDDVisible());}




}

