package CSM_Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.CSM.MessagingScreen;
import pageObjects.CSM.SupportScreen;
import pageObjects.Login.Login;
import testUtils.TestInitialise;

public class messagingScreenTests extends TestInitialise {

    private Login login;
    private SupportScreen supportScreen;
    private MessagingScreen messagingScreen;

    @BeforeMethod
    public void beforeMethod() {
        login = new Login(driver);
        supportScreen = new SupportScreen(driver);
        messagingScreen = new MessagingScreen(driver);

    }

    @Test(priority = 1, description = "Start Chat - Chat launches - prior messaging user")
    public void CUICE6265() {
        Assert.assertTrue(messagingScreen.navigateToMessagingScreen());
    }

    @Test(priority = 2, description = "Submit query - User submits a query in the chat window - Mobile")
    public void CUICE5849() { Assert.assertTrue(messagingScreen.writeToMessagingScreen()); }

    @Test(priority = 3, description = "Welcome Message")
    public void CUICE6534() {
        Assert.assertTrue(messagingScreen.checkWelcomeMessage());
    }

    @Test(priority = 4, description = "Contact support from Card management Screen")
    public void CUICE6142() {
        Assert.assertTrue(messagingScreen.contactSupportThroughCardManagement());
    }

    @Test(priority = 5, description = "Contextual Chat - Activate Card")
    public void CUICE6751() {
        Assert.assertTrue(messagingScreen.contactSupportThroughActivateCard());
    }

    @Test(priority = 6, description = "Contextual Chat - Report lost or stolen card")
    public void CUICE6750() {
        Assert.assertTrue(messagingScreen.contactSupportThroughReportLostOrStolen());
    }

    @Test(priority = 7, description = "capture push consent - information on the push consent - allow")
    public void CUICE7227a() {
        Assert.assertTrue(messagingScreen.allowNotification());
    }

    @Test(priority = 8, description = "capture push consent - information on the push consent - don't allow")
    public void CUICE7227b() {
        Assert.assertTrue(messagingScreen.dontAllowNotification());
    }

    @Test(priority = 9, description = "capture push consent - App launches - First time")
    public void CUICE7226() {
        Assert.assertTrue(messagingScreen.capturePushConsent());
    }

    @Test(priority = 10, description = "Clear the chat history - User clears their chat history")
    public void CUICE6191() {
        Assert.assertTrue(messagingScreen.clearHistoryMethod());
    }

    @Test(priority = 11, description = "Clear Chat History - User Not Completed Conversation ")
    public void CUICE6192() { Assert.assertTrue(messagingScreen.clearChatHistoryResolveFirst()); }

    @Test(priority = 12, description = "five lines ")
    public void CUICE1() { Assert.assertTrue(messagingScreen.fiveLines()); }

    @Test(priority = 13, description = "Straight to chat - Leave chat window ")
    public void CUICE7430() { Assert.assertTrue(messagingScreen.straightToMessagingScreen()); }

}
