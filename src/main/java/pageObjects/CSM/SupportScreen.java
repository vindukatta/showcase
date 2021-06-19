package pageObjects.CSM;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import pageObjects.Login.Login;
import pageObjects.Base.BasePage;


public class SupportScreen extends BasePage {

    Login login = new Login(driver);

    @iOSFindBy(accessibility = "customer_support.chat_button_title")
    private MobileElement chatWithUsButton;

    @iOSFindBy(accessibility = "Support")
    private MobileElement supportButton;

    @iOSFindBy(xpath = "//XCUIElementTypeAlert[@name=\"“Iceberg” Would Like to Send You Notifications\"]")
    private MobileElement notificationBox;

    @iOSFindBy(accessibility = "0800 327 075")
    private MobileElement cardsLostOrStolenNumber;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther"+
            "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther"+
            "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView")
    private MobileElement newMessage;


    public SupportScreen(AppiumDriver driver) {
        super(driver);
    }

    public boolean isSupportButtonDisplayed() {
        return supportButton.isDisplayed();
    }

    // Element Enabled
    public boolean ischatWithUsButtonEnabled() {
        return chatWithUsButton.isEnabled();
    }

    public boolean isSupportButtonEnabled() {
        return supportButton.isEnabled();
    }

    // Clicking Elements
    public void clickchatWithUsButton() {
        click(chatWithUsButton);
    }

    public void clickSupportButton() {
        click(supportButton);
    }


    // Custom Methods
    public boolean navigateToSupportScreen() {
        login.navigateToLogin();
        clickSupportButton();
        return chatWithUsButton.isDisplayed();
    }

    public void passToMessageScreen () {
        login.loginAsUser("TESTUSER","TESTPASSWORD");
        clickSupportButton();
        clickchatWithUsButton();
    }

    public boolean callCardsLostOrStolenNumber(){
        login.passThroughLogin();
        clickSupportButton();
        boolean one = newMessage.isDisplayed();
        boolean two = cardsLostOrStolenNumber.isDisplayed();
        click(cardsLostOrStolenNumber);
        return one && two;
    }

}


