package Login;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Login.Login;
import testUtils.TestInitialise;

public class loginTests extends TestInitialise {

    private Login login;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        login = new Login(driver);
    }

    @Test(priority = 1, description = "Login with username & password then logout")
    public void CUICE5474() { Assert.assertTrue(login.loginLogoutTest()); }

    @Test(priority = 2, description = "Login with incorrect username")
    public void CUICE5476() {
        Assert.assertTrue(login.incorrectLogin());
    }

    @Test(priority = 3, description = "Login with incorrect password")
    public void CUICE5477() { Assert.assertTrue(login.incorrectPassword()); }

    @Test(priority = 4, description = "Password unmasking")
    public void CUICE5473() { Assert.assertTrue(login.unmaskPassword()); }

    @Test(priority = 5, description = "Navigate to password field")
    public void CUICE5472() {
        Assert.assertTrue(login.navigateToPasswordField());
    }

    @Test(priority = 6, description = "Navigate to password progression screen")
    public void CUICE5471() {
        Assert.assertTrue(login.navigateToPasswordScreen());
    }

    @Test(priority = 7, description = "Navigate to username field")
    public void CUICE5440() {
        Assert.assertTrue(login.navigateToUsernameFieldBox());
    }

    @Test(priority = 8, description = "Navigate to username page")
    public void CUICE5437() {
        Assert.assertTrue(login.navigateToUsernamePage());
    }

    @Test(priority = 9, description = "navigate to the current balance and log out")
    public void CUICE5389() {
        Assert.assertTrue(login.navigateToBalanceLogout());
    }

}
    /*
    //Advised by Ed to wait on this test. I've left this here as a reference.
    @Test(priority = 10, description = "navigate to login and wait 10 mintues for inactivity log out")
    public void CUICE5390() {
        Assert.assertTrue(login.logoutTest());
    }
    */
