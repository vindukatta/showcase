package pageObjects.More;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import pageObjects.Overview.Overview;
import testData.RandomDataGenerator;
import testData.cardsTestData.GetCardsTestData;
import pageObjects.Base.BasePage;

public class ActivateCard extends BasePage {

    private CardsManagement cardsManagement = new CardsManagement(driver);
    private RandomDataGenerator generator = new RandomDataGenerator();
    private Overview overview = new Overview(driver);

    @iOSFindBy(accessibility = "Activate card")
    private MobileElement activateCardTitle;

    @iOSFindBy(accessibility = "activate_card.textfield.card_number")
    private MobileElement cardNumberTextbox;

    @iOSFindBy(accessibility = "activate_card.textfield.expiry_date")
    private MobileElement expiryDateTextbox;

    @iOSFindBy(accessibility = "activate_card.button.primary_cta")
    private MobileElement activateCardButton;

    @iOSFindBy(accessibility = "cards.button.contact_support")
    private MobileElement contactSupportButton;

    @iOSFindBy(accessibility = "Cancel")
    private MobileElement cancelButton;

    @iOSFindBy(accessibility = "activate_card.label.footer")
    private MobileElement contactlessMessage;

    @iOSFindBy(accessibility = "This number doesn't look right, please try again")
    private MobileElement errorMessage;

    @iOSFindBy(accessibility = "activate_card_progress_status.success.card_successfully_activated")
    private MobileElement cardActivatedSuccessMessage;

    @iOSFindBy(accessibility = "activate_card_progress_status.success.card_already_activated")
    private MobileElement cardAlreadyActivated;

    @iOSFindBy(accessibility = "activate_card_progress_status.error.security")
    private MobileElement cardErrorSecurity;

    @iOSFindBy(accessibility = "activate_card_progress_status.error.generic_error")
    private MobileElement timeoutError;

    @iOSFindBy(accessibility = "activate_card_progress_status.button.done")
    private MobileElement activatedDoneButton;

    public ActivateCard(AppiumDriver driver) {
        super(driver);
    }

    public boolean activateCardTitleDisplayed(){
        overview.clickBanner();
        return activateCardTitle.isDisplayed();
    }

    public boolean activateACardScreen(){
        cardsManagement.navigateToActivateCard();
        boolean one  = cardNumberTextbox.isDisplayed();
        boolean two = expiryDateTextbox.isDisplayed();
        boolean three  = contactSupportButton.isDisplayed();
        boolean four = contactlessMessage.isDisplayed();
        boolean five = cancelButton.isDisplayed();
        boolean six = activateCardButton.isDisplayed();
        return one && two && three && four && five && six;
    }

    public boolean positiveEntryIntoCardNumberField(){
        cardsManagement.navigateToActivateCard();
        click(cardNumberTextbox);
        boolean one = doesKeyboardExist();
        boolean two;
        writeText(cardNumberTextbox, "4532507073978721");
        try {
            two = !errorMessage.isDisplayed();
        } catch (NoSuchElementException e){
            two = true;
        }
        return one && two;
    }

    public boolean negativeEntryIntoCardTextField(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, generator.setRandomValue(15, "NUMERIC"));
        click(expiryDateTextbox);
        boolean one = errorMessage.isDisplayed();
        clearText(cardNumberTextbox);

        writeText(cardNumberTextbox, "1111111111111111");
        click(expiryDateTextbox);
        click(cardNumberTextbox);
        click(expiryDateTextbox);
        boolean two = errorMessage.isDisplayed();

        clearText(cardNumberTextbox);
        String num = generator.setRandomValue(15, "NUMERIC");
        writeText(cardNumberTextbox,  num + "01");
        boolean three = readText(cardNumberTextbox).equalsIgnoreCase(num + "0");

        return one && two && three;
    }

    public boolean expiryDatePositive(){
        cardsManagement.navigateToActivateCard();
        writeText(expiryDateTextbox, "1225");
        click(cardNumberTextbox);
        try {
            return !errorMessage.isDisplayed();
        } catch (NoSuchElementException e){
            return true;
        }
    }

    public boolean expiryDateNegative(){
        cardsManagement.navigateToActivateCard();
        writeText(expiryDateTextbox, "1525");
        click(cardNumberTextbox);
        //check month cannot be greater than 12
        boolean monthLong;
        try {
            monthLong = errorMessage.isDisplayed();
        } catch (NoSuchElementException e){
            monthLong = false;
        }

        clearText(expiryDateTextbox);
        writeText(expiryDateTextbox, "0025");
        click(cardNumberTextbox);
        // check month cannot be zero
        boolean monthShort;
        try {
            monthShort = errorMessage.isDisplayed();
        } catch (NoSuchElementException e){
            monthShort = false;
        }

        clearText(expiryDateTextbox);
        writeText(expiryDateTextbox, "1215");
        click(cardNumberTextbox);
        //check year cannot be past
        boolean year;
        try {
            year = errorMessage.isDisplayed();
        } catch (NoSuchElementException e){
            year = false;
        }

        return monthShort && monthLong && year;
    }

    public boolean continueButton(){
        cardsManagement.navigateToActivateCard();
        click(cardNumberTextbox);
        boolean one = !activateCardButton.isEnabled();

        writeText(cardNumberTextbox, "4532507073978721");
        boolean two = !activateCardButton.isEnabled();
        clearText(cardNumberTextbox);

        writeText(expiryDateTextbox, "1225");
        boolean three = !activateCardButton.isEnabled();
        clearText(cardNumberTextbox);

        writeText(cardNumberTextbox, "4532507073978721");
        writeText(expiryDateTextbox, "1225");
        boolean four = activateCardButton.isEnabled();

        return one && two && three && four;

    }

    public boolean cancelButton(){
        cardsManagement.navigateToActivateCard();
        click(cancelButton);
        return cardsManagement.isTitleDisplayed();
    }

    public boolean successfulCardActivation(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getSuccssfullCardNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        boolean one = cardActivatedSuccessMessage.isDisplayed();
        click(activatedDoneButton);
        return one;
    }

    public boolean cardAlreadyActivated(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getAlreadyActivatedCardNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        return cardAlreadyActivated.isDisplayed();
    }

    public boolean cardNotLive(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getCardNotLiveCardNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        return cardErrorSecurity.isDisplayed();
    }

    public boolean incorrectCardDetails(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getCardIncorrectNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        boolean one = errorMessage.isDisplayed();
        boolean two = readText(cardNumberTextbox).equals("");
        boolean three = readText(expiryDateTextbox).equals("");
        return one && two && three;
    }

    public boolean cardActivationTimeout(){
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getCardTimeoutNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        return timeoutError.isDisplayed();
    }

    public void passThroughSuccessfulCardActivation(){
        writeText(cardNumberTextbox, GetCardsTestData.getSuccssfullCardNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        click(activatedDoneButton);
    }

    public boolean activateCardBannerRemovedAfterCardManagementActivation() {
        overview.navigateToOverviewPage();
        boolean one = overview.isActivateCardBannerDisplayed();
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getSuccssfullCardNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        click(activatedDoneButton);
        cardsManagement.clickBack();
        boolean two = overview.activateCardBannerRemovedAfterActivation();
        return one && two;
    }

    public boolean activateCardBannerRemains() {
        overview.navigateToOverviewPage();
        boolean one = overview.isActivateCardBannerDisplayed();
        cardsManagement.navigateToActivateCard();
        writeText(cardNumberTextbox, GetCardsTestData.getCardTimeoutNumber());
        writeText(expiryDateTextbox, "1225");
        click(activateCardButton);
        click(cancelButton);
        cardsManagement.clickBack();
        boolean two = overview.activateCardBannerRemovedAfterActivation();
        return one && two;
    }

    public void contactSupport(){
        cardsManagement.navigateToActivateCard();
        click(contactSupportButton);
    }
}
