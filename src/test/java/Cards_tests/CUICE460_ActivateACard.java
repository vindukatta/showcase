package Cards_tests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Login.Login;
import pageObjects.More.ActivateCard;
import pageObjects.More.CardLostOrStolen;
import pageObjects.More.CardLostOrStolenFurtherInfo;
import pageObjects.More.CardsManagement;
import pageObjects.Overview.Overview;
import testUtils.TestInitialise;

public class CUICE460_ActivateACard extends TestInitialise {

    private Login login;
    private ActivateCard card;
    private CardsManagement management;
    private Overview overview;
    private CardLostOrStolenFurtherInfo deactivate;

    @BeforeMethod
    public void beforeMethod() {
        card = new ActivateCard(driver);
        management = new CardsManagement(driver);
        overview = new Overview(driver);
        deactivate = new CardLostOrStolenFurtherInfo(driver);

        login = new Login(driver);
    }

    @Test(priority = 1, description = "Activate a card screen")
    public void CUICE6628(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.activateACardScreen());
    }

    @Test(priority = 2, description = "Card Number screen, positive")
    public void CUICE6629(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.positiveEntryIntoCardNumberField());
    }

    @Test(priority = 3, description = "Card Number screen, negative")
    public void CUICE6678(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.negativeEntryIntoCardTextField());
    }

    @Test(priority = 4, description = "Expiry Date Field, positive")
    public void CUICE6630(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.expiryDatePositive());
    }

    @Test(priority = 5, description = "Expiry Date Field, negative")
    public void CUICE6679(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.expiryDateNegative());
    }

    @Test(priority = 6, description = "Continue Button")
    public void CUICE6631(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.continueButton());
    }

    @Test(priority = 7, description = "Cancel Button")
    public void CUICE6632(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.cancelButton());
    }

    @Test(priority = 8, description = "Card Activation Success")
    public void CUICE6602(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.successfulCardActivation());
        deactivate.deactivateCard();
    }

    @Test(priority = 9, description = "Incorrect Card Details")
    public void CUICE6604(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.incorrectCardDetails());
    }

    @Test(priority = 10, description = "Card Already Activated")
    public void CUICE6605(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.cardAlreadyActivated());
    }

    @Test(priority = 11, description = "Non Live Card Activation Attempt")
    public void CUICE6606(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.cardNotLive());
    }

    @Test(priority = 12, description = "Card Activation Fail (Timeout)")
    public void CUICE6607(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.cardActivationTimeout());
    }

    @Test(priority = 13, description = "Card Activation Prompt")
    public void CUICE6512(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(overview.isActivateCardBannerDisplayed());
        Assert.assertTrue(card.activateCardTitleDisplayed());
    }

//    @Test(priority = 14, description = "Remove Card Activation Prompt - activate from banner")
//    public void CUICE6562(){
//        login.setLogin("FIRST TIME");
//        Assert.assertTrue(overview.activateCardBannerRemovedAfterActivation());
//    }

    @Test(priority = 15, description = "Card Activation Menu Option")
    public void CUICE6564() {
        login.setLogin("FIRST TIME");
        Assert.assertTrue(management.cardManagementScreenHasActivateCard());
        Assert.assertTrue(card.activateCardTitleDisplayed());
    }

    @Test(priority = 16, description = "Remove Card Activation Prompt - activate from card management")
    public void CUICE6936(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.activateCardBannerRemovedAfterCardManagementActivation());
    }

    @Test(priority = 17, description = "Remove Card Activation Prompt - Incorrect Card Number")
    public void CUICE6938(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(card.activateCardBannerRemains());
    }
}
