package Payments_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.TestInitialise;
import pageObjects.Payments.RecentPayees;

public class recentPayees_tests extends TestInitialise {

    private RecentPayees recentPayees;

    @BeforeMethod
    public void beforeMethod() {
        recentPayees= new RecentPayees(driver);
    }

    @Test(priority = 1 , description = "Display recent payees" )
    public void CUICE6358 () {
        Assert.assertTrue(recentPayees.recentSectionVerification());
    }

    @Test(priority = 2 , description = "Do not display recent section" )
    public void CUICE6359 () {
        Assert.assertTrue(recentPayees.noRecentPayee());
    }

    @Test(priority = 3 , description = "Select payee from recent section and view payment details" )
    public void CUICE6361 () {
        Assert.assertTrue(recentPayees.selectPayee());
    }

    @Test(priority = 4 , description = "Prepopulate reference field" )
    public void CUICE6362 () {
        Assert.assertTrue(recentPayees.referenceFieldCheck());
    }

    @Test(priority = 5 , description = "View payment summary page ")
    public void CUICE6363 () { Assert.assertTrue(recentPayees.summaryPageVerification());}

}
