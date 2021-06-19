package CSM_Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CSM.SupportScreen;
import pageObjects.Login.Login;
import testUtils.TestInitialise;

public class supportScreenTests extends TestInitialise {

    private Login login;
    private SupportScreen supportScreen;

    @BeforeMethod
    public void beforeMethod() {
        login = new Login(driver);
        supportScreen = new SupportScreen(driver);
    }

    @Test(priority = 1, description = "Navigate to support screen")
    public void CUICE1() { Assert.assertTrue(supportScreen.navigateToSupportScreen()); }

    @Test(priority = 2, description = "In App Chat. Update Welcome message")
    public void CUICE9131() { Assert.assertTrue(supportScreen.callCardsLostOrStolenNumber()); }

}

