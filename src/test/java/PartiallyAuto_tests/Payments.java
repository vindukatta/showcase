package PartiallyAuto_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Payments.AvailableBalance;
import pageObjects.Payments.DirectDebit;
import testUtils.TestInitialise;

public class Payments extends TestInitialise {

    private AvailableBalance availableBalance;
    private DirectDebit directDebit;

    @BeforeMethod
    public void beforeMethod() {
        directDebit = new DirectDebit(driver);
        availableBalance= new AvailableBalance(driver);

    }


    @Test(priority = 1, description = "Disable faster payments Functionality")
    public void CUICE7830() {
        Assert.assertTrue(availableBalance.fastPaymentDisabled());
    }

    @Test(priority = 1, description = "Disable Faster Payments and Direct Debit functionality")
    public void CUICE7831() {
        Assert.assertTrue(availableBalance.fastPaymentandDDISDisabled());
    }

    @Test(priority = 1, description = "Disable Transaction List & Graph functionality")
    public void CUICE7911() {
        Assert.assertTrue(availableBalance.transactionListDisabled());
    }

    @Test(priority = 1, description = "Disable Direct Debit functionality")
    public void CUICE7823() {
        Assert.assertTrue(availableBalance.directDebitsDisabled());
    }

    @Test(priority = 1, description = "Disable Transaction List & Graph functionality and Faster Payments")
    public void CUICE7914() {
        Assert.assertTrue(availableBalance.transactionListandFPDisabled());
    }

    @Test(priority = 1, description = "Disable Direct Debit & Standing Order")
    public void CUICE8096() { Assert.assertTrue(directDebit.directDebitAndSODisabled()); }

    @Test(priority = 1, description = "Disable Standing order & Enable Direct Debits")
    public void CUICE8097() {
        Assert.assertTrue(directDebit.dDEnabledSODisabled());
    }

    @Test(priority = 1, description = "Disable direct debit & Enable Standing Order")
    public void CUICE8098() {
        Assert.assertTrue(directDebit.sOEnabledDDDisabled());
    }

    @Test(priority = 1, description = "Disable Standing order payments functionality")
    public void CUICE8314() { Assert.assertTrue(availableBalance.disableSO()); }

    @Test(priority = 1, description = "Disable Faster Payments & Enable Standing order payments functionality")
    public void CUICE8817() { Assert.assertTrue(availableBalance.fasterPayDisabledSOEnabled()); }

    @Test(priority = 19, description = "Toggle display - 3 Tabs")
    public void CUICE9175() {Assert.assertTrue(directDebit.viewPaymentsTabs());}

    @Test(priority = 19, description = "switch between payment types")
    public void CUICE9176() {Assert.assertTrue(directDebit.checkPaymentsTabs());}


}
