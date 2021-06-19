package Transactions_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Transactions.TransactionsObjects;
import testUtils.TestInitialise;

public class transactionsTests extends TestInitialise {

    private TransactionsObjects transactionsObjects;

    @BeforeMethod
    public void beforemethod(){
        transactionsObjects = new TransactionsObjects(driver);
    }

    @Test(priority = 1, description = "View Transaction Details")
    public void CUICE5464(){
        Assert.assertTrue(transactionsObjects.viewTransactionDetails());
    }

    @Test(priority = 2, description = "View Account Balance")
    public void CUICE4941(){
        Assert.assertTrue(transactionsObjects.accountBalance());
    }

    @Test(priority = 3, description = "View Available Balance")
    public void CUICE4943(){
        Assert.assertTrue(transactionsObjects.availableBalance());
    }

    @Test(priority = 4, description = "Current Account Details (Account Number and Sort Code)")
    public  void  CUICE4944(){
        Assert.assertTrue(transactionsObjects.sortCodeAndAccountNumber());
    }

    @Test(priority = 5, description = "View Older Transactions")
    public void CUICE4813(){
        Assert.assertTrue(transactionsObjects.viewOlderTransactionsByMonth());
    }

    @Test(priority = 6, description = "")
    public void CUICE4811(){
        Assert.assertTrue(transactionsObjects.viewTransactionDetails());
    }

    @Test()
    public void CUICE(){
        transactionsObjects.transactionsScreenshot();
    }
}