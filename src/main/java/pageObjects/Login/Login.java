package pageObjects.Login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Base.BasePage;
import testData.getUserData.GetUserData;

public class Login extends BasePage {

    @iOSFindBy(accessibility = "welcome.title")
    private MobileElement welcomeTitle;

    @iOSFindBy(accessibility = "welcome.existing_customer_button_title")
    private MobileElement existingCustomerButton;

    @iOSFindBy(accessibility = "Set up")
    private MobileElement backArrowUsername;

    @iOSFindBy(accessibility = "login.login_username_textfield_header")
    private MobileElement usernameFieldBox;

    @iOSFindBy(accessibility = "checkbox_button")
    private MobileElement rememberMeCheckbox;

    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement usernameNextButton;

    @iOSFindBy(accessibility = "login.login_password_textfield_header")
    private MobileElement passwordFieldBox;

    @iOSFindBy(accessibility = "checkbox_button")
    private MobileElement showPasswordCheckBox;

    @iOSFindBy(accessibility = "login.login_button_title")
    private MobileElement passwordLoginButton;

    @iOSFindBy(accessibility = "Logout")
    private MobileElement logoutButton;

    @iOSFindBy(accessibility = "welcome.existing_customer_button_title")
    private MobileElement accountTitle;

    @iOSFindBy(accessibility = "A 150-year-old bank, updated 30 seconds ago")
    private MobileElement logoutScreen;

    @iOSFindBy(accessibility = "Try Again")
    private MobileElement errorMessage;

    @iOSFindBy(accessibility = "Allow")
    private MobileElement allowNotifacation;

    @iOSFindBy(accessibility = "Don’t Allow")
    private MobileElement dontAllowNotification;

    @iOSFindBy(accessibility = "tab_bar.more_title")
    private MobileElement moreButton;

    @iOSFindBy(accessibility = "Entered an incorrect username or password")
    private MobileElement incorrectPasswordError;

    @iOSFindBy(accessibility = "tab_bar.overview_title")
    private MobileElement overviewButton;

    public Login(AppiumDriver driver) {
        super(driver);
    }

    //Elements Displayed
    public boolean iserrorMessageDisplayed() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public boolean islogoutScreenDisplayed() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(logoutScreen));
        return logoutScreen.isDisplayed();
    }

    public boolean ispasswordFieldBoxDisplayed() {
        return passwordFieldBox.isDisplayed();
    }

    public boolean isusernameFieldBoxDiplay() {
        return usernameFieldBox.isDisplayed();
    }

    public boolean isincorrectPasswordErrorDiplay() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(incorrectPasswordError));
        return incorrectPasswordError.isDisplayed();
    }

    //Click Methods
    public void clickexistingCustomerButton() { click(existingCustomerButton); }

    public void clickallowNotifacation() { click(allowNotifacation); }

    public void clickdontAllowNotifacation() { click(dontAllowNotification); }

    public void clickusernameNextButton() { click(usernameNextButton); }

    public void clickpasswordLoginButton() { click(passwordLoginButton); }

    public void clicklogoutButton() { click(logoutButton); }

    public void clickshowPasswordCheckBox() { click(showPasswordCheckBox); }

    public void clickpasswordFieldBox() { click(passwordFieldBox); }

    public void clickusernameFieldBox() { click(usernameFieldBox); }

    public void clickmoreButton() { click(moreButton); }

    public void clickoverviewButton() { click(overviewButton); }

    //Write Method
    public void writeUsername(String username) {
        writeText(usernameFieldBox, username);
    }

    public void writePassword(String password) {
        writeText(passwordFieldBox, password);
    }


    public boolean loginLogoutTest() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
        clickallowNotifacation();
        clickmoreButton();
        clicklogoutButton();
        return islogoutScreenDisplayed();
    }

    public boolean incorrectLogin() {
        clickexistingCustomerButton();
        writeUsername("TESTUSE");
        clickusernameNextButton();
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
        return iserrorMessageDisplayed();
    }

    public boolean incorrectPassword() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        clickshowPasswordCheckBox();
        clickpasswordFieldBox();
        writePassword("TESTPASSWORDD");
        clickpasswordLoginButton();
        return isincorrectPasswordErrorDiplay();
    }

    public boolean unmaskPassword() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        clickshowPasswordCheckBox();
        return ispasswordFieldBoxDisplayed();
    }

    public boolean navigateToPasswordField() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        clickshowPasswordCheckBox();
        clickpasswordFieldBox();
        return ispasswordFieldBoxDisplayed();
    }

    public boolean navigateToPasswordScreen() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        return ispasswordFieldBoxDisplayed();
    }

    public boolean navigateToUsernameFieldBox() {
        clickexistingCustomerButton();
        clickusernameFieldBox();
        return isusernameFieldBoxDiplay();
    }

    public boolean navigateToUsernamePage() {
        clickexistingCustomerButton();
        return isusernameFieldBoxDiplay();
    }

    public boolean navigateToBalanceLogout() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
        clickallowNotifacation();
        clickoverviewButton();
        clickmoreButton();
        clicklogoutButton();
        return islogoutScreenDisplayed();
    }
    public void navigateToLogin() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        clearText(passwordFieldBox);
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
        clickallowNotifacation();
    }

    public void passThroughLogin() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        clearText(passwordFieldBox);
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
        clickallowNotifacation();
    }

    public void loginAsUser(String username, String password) {
        clickexistingCustomerButton();
        writeUsername(username);
        clickusernameNextButton();
        clearText(passwordFieldBox);
        writePassword(password);
        clickpasswordLoginButton();
        click(dontAllowNotification);
    }

    public void passThroughLoginBeforePushConsent() {
        clickexistingCustomerButton();
        writeUsername("TESTUSER");
        clickusernameNextButton();
        writePassword("TESTPASSWORD");
        clickpasswordLoginButton();
    }

    public void setLogin(String user){

        switch (user){
            case "FIRST TIME":
                loginAsUser((String) GetUserData.getFirstTimeUser().get("Username"), (String) GetUserData.getFirstTimeUser().get("Password"));
                break;
            case "NO TRANSACTION":
                loginAsUser((String) GetUserData.getNoTransactionUser().get("Username"), (String) GetUserData.getNoTransactionUser().get("Password"));

        }
    }


}
