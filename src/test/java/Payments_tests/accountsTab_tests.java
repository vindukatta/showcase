package Payments_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.TestInitialise;
import pageObjects.Payments.AccountsTab;

public class accountsTab_tests extends TestInitialise {

    private AccountsTab accountsTab;

    @BeforeMethod
    public void beforeMethod() {
        accountsTab = new AccountsTab(driver);
    }

    @Test(priority = 1 , description = "Empty state - user has no transactions on their account" )
    public void CUICE6603 () {
        Assert.assertTrue(accountsTab.emptyStateVerification());
    }

    @Test(priority = 2 , description = "Empty state - user has no transactions on their account" )
    public void CUICE6601 () {
        Assert.assertTrue(accountsTab.titleDisplayVerification());
    }

    @Test(priority = 3, description = "Months is in active state on months bar (more than 1 month displayed )")
    public void CUICE6724 (){Assert.assertTrue(accountsTab.moreThanOneMonthsBar());}

    @Test(priority = 4, description = "Months is in active state on months bar (1st month of account) ")
    public void CUICE6723 (){Assert.assertTrue(accountsTab.oneMonthBar());}

}
