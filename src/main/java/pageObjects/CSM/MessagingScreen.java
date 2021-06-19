package pageObjects.CSM;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.JavascriptExecutor;
import pageObjects.Base.BasePage;
import pageObjects.Login.Login;
import pageObjects.More.ActivateCard;
import pageObjects.More.CardLostOrStolen;
import pageObjects.More.CardsManagement;
import pageObjects.More.More;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class MessagingScreen extends BasePage {

    SupportScreen supportScreen = new SupportScreen(driver);
    ActivateCard activateCard = new ActivateCard(driver);
    CardLostOrStolen cardLostOrStolen = new CardLostOrStolen(driver);
    CardsManagement cardsManagement= new CardsManagement(driver);
    Login login = new Login(driver);
    More more = new More(driver);

    @iOSFindBy(accessibility = "Write a message")
    private MobileElement writeAMessage;

    @iOSFindBy(accessibility = "cards.button.contact_support")
    private MobileElement contactSupportButton;

    @iOSFindBy(accessibility = "Close")
    private MobileElement closeScreenButton;

    @iOSFindBy(accessibility = "Menu")
    private MobileElement menu;

    @iOSFindBy(accessibility = "Now")
    private MobileElement timeOfFirstMessage;

    @iOSFindBy(accessibility = "Send")
    private MobileElement sendButton;

    @iOSFindBy(accessibility = "Allow")
    private MobileElement allowNotification;

    @iOSFindBy(accessibility = "Don’t Allow")
    private MobileElement dontAllowNotification;

    @iOSFindBy(accessibility = "Support chat")
    private MobileElement supportChatTitle;

    @iOSFindBy(accessibility = "cards.button.contact_support")
    private MobileElement cardManagementContactSupportButton;

    @iOSFindBy(accessibility = "tab_bar.support_title")
    private MobileElement supportButton;

    @iOSFindBy(xpath = "//XCUIElementTypeAlert[@name=\"“Iceberg” Would Like to Send You Notifications\"]") //FIXME get unique accessibility id
    private MobileElement pushConsentBox;

    @iOSFindBy(accessibility = "Menu")
    private MobileElement menuButton;

    @iOSFindBy(accessibility = "Close")
    private MobileElement closeButton;

    @iOSFindBy(accessibility = "Resolve the conversation")
    private MobileElement resolveTheConversation;

    @iOSFindBy(accessibility = "Clear history")
    private MobileElement clearHistory;

    @iOSFindBy(accessibility = "Resolve")
    private MobileElement resolveButton;

    @iOSFindBy(accessibility = "Clear")
    private MobileElement clearButton;

    @iOSFindBy(accessibility = "Please resolve the conversation first.")
    private MobileElement pleaseResolveConversationFirst;

    @iOSFindBy(accessibility = "OK")
    private MobileElement okButton;

    @iOSFindBy(accessibility = "I have lost my card")
    private MobileElement iHaveLostMyCardMessage;

    @iOSFindBy(accessibility = "Scroll indicator")
    private MobileElement scrollIndicator;

    @iOSFindBy(accessibility = "tab_bar.more_title")
    private MobileElement moreButton;

    @iOSFindBy(accessibility = "More")
    private MobileElement moreScreenTitle;


    public MessagingScreen(AppiumDriver driver) {
        super(driver);
    }


    // Click Methods

    public void clickSendButton() {
        click(sendButton);
    }

    public void clickMenuButton() { click(menuButton); }

    public void clickScrollIndicator() { click(scrollIndicator); }

    public void clickCloseButton() { click(closeButton); }

    public void writeMessage(String message) {
        writeText(writeAMessage, message);
    }

    // Custom Methods
    public void resolveTheConversationMethod() {
        navigateToMessagingScreen();
        writeMessage("I have lost my card");
        clickSendButton();
        clickMenuButton();
        click(resolveTheConversation);
        click(resolveButton);
    }

    public void clearChatHistory() {
        clickMenuButton();
        click(clearHistory);
        click(clearButton);
    }

    protected void inAppScrollUp(){
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
    }

    protected void inAppScrollDown(){
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
    }

    // Test Methods
    public boolean navigateToMessagingScreen() {
        supportScreen.passToMessageScreen();
        return supportChatTitle.isDisplayed();
    }

    public boolean writeToMessagingScreen() {
        resolveTheConversationMethod();
        clearChatHistory();
        writeMessage("I have lost my card");
        boolean one = writeAMessage.getText().equals("I have lost my card");
        clickSendButton();
        boolean two = driver.findElementByAccessibilityId("I have lost my card").isDisplayed();
        return one && two;
    }

    public boolean checkWelcomeMessage() {
        resolveTheConversationMethod();
        clearChatHistory();
        boolean one = driver.findElementByXPath("//*[contains(@name, 'How can I help you today?')]").isDisplayed();
        return one;
    }

    public boolean contactSupportFromCards() {
        activateCard.contactSupport();
        return supportChatTitle.isDisplayed();
    }

    public boolean contactSupportThroughCardManagement() {
        login.navigateToLogin();
        more.navigateToCardManagement();
        click(cardManagementContactSupportButton);
        return supportChatTitle.isDisplayed();
    }

    public boolean contactSupportThroughActivateCard() {
        login.navigateToLogin();
        activateCard.contactSupport();
        return supportChatTitle.isDisplayed();
    }

    public boolean contactSupportThroughReportLostOrStolen() {
        login.navigateToLogin();
        activateCard.passThroughSuccessfulCardActivation();
        cardsManagement.clickReportLostOrStolenButton();
        cardLostOrStolen.click(contactSupportButton);
        return supportChatTitle.isDisplayed();
    }


    public boolean allowNotification() {
        login.passThroughLogin();
        return supportButton.isDisplayed();
    }

    public boolean dontAllowNotification() {
        login.loginAsUser("TESTUSER","TESTPASSWORD");
        return supportButton.isDisplayed();
    }

    public boolean capturePushConsent() {
        login.passThroughLoginBeforePushConsent();
        return pushConsentBox.isDisplayed();
    }


    public boolean clearHistoryMethod() {
        resolveTheConversationMethod();
        clickMenuButton();
        click(clearHistory);
        click(clearButton);
        boolean one;

        try {
            one = !iHaveLostMyCardMessage.isDisplayed();
        } catch (NoSuchElementException e){
            one = true;
        }
        boolean two = driver.findElementByXPath("//*[contains(@name, 'How can I help you today?')]").isDisplayed();
        return one && two;
    }

    public boolean clearChatHistoryResolveFirst() {
        navigateToMessagingScreen();
        writeMessage("help help help help");
        clickSendButton();
        clearChatHistory();
        boolean one = pleaseResolveConversationFirst.isDisplayed();
        click(okButton);
        boolean two = driver.findElementByXPath("//*[contains(@name, 'help help help help')]").isDisplayed();
        return one && two;
    }

    public boolean straightToMessagingScreen() {
        navigateToMessagingScreen();
        clickCloseButton();
        click(moreButton);
        boolean one = moreScreenTitle.isDisplayed();
        click(supportButton);
        boolean two = supportChatTitle.isDisplayed();
        return one && two;
    }

    public boolean fiveLines() {
        navigateToMessagingScreen();
        writeMessage("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        int width = writeAMessage.getSize().getWidth();
        int height = writeAMessage.getSize().getHeight();
        return width == 277 && height == 84;
    }


}

