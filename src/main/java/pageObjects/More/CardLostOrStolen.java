package pageObjects.More;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import pageObjects.Login.Login;
import pageObjects.Base.BasePage;

public class CardLostOrStolen extends BasePage {

    private CardsManagement cardsManagement = new CardsManagement(driver);
    private Login login = new Login(driver);

    @iOSFindBy(accessibility = "CardPlaceholder")
    private MobileElement cardImage;

    @iOSFindBy(accessibility = "You're about to report the above card as lost or stolen")
    private MobileElement reportLostStolenTitle;

    @iOSFindBy(accessibility = "Once confirmed your old card will be cancelled and a new card will be sent to your correspondence address.")
    private MobileElement reportLostStolenInstruction;

    @iOSFindBy(accessibility = "cards.button.continue")
    private MobileElement cardsContinueButton;

    @iOSFindBy(accessibility = "cards.button.contact_support")
    private MobileElement contactSupportButton;

    @iOSFindBy(accessibility = "Card management")
    private MobileElement cancelReoprtLostStolen;

    public CardLostOrStolen(AppiumDriver driver) {
        super(driver);
    }

    public void clickContinue(){
        click(cardsContinueButton);
    }

    public boolean isCardImageDisplayed(){
        return cardImage.isEnabled();
    }

    public boolean navigateToReportLostOrStolenScreen(){
        cardsManagement.navigateToCardLostOrStolen();
        return reportLostStolenTitle.isDisplayed();
    }

    public boolean reportLostStolenScreen(){
        cardsManagement.navigateToCardLostOrStolen();
        boolean one = isCardImageDisplayed();
        boolean two = reportLostStolenTitle.isDisplayed();
        boolean three = reportLostStolenInstruction.isDisplayed();
        boolean four = cardsContinueButton.isDisplayed();
        boolean five = contactSupportButton.isDisplayed();
        boolean six = cancelReoprtLostStolen.isDisplayed();
        click(cancelReoprtLostStolen);
        boolean seven = cardsManagement.isTitleDisplayed();
        System.out.println(one && two && three && four && five && six && seven );
        return one && two && three && four && five && six && seven;
    }

    public boolean continueButton(){
        cardsManagement.navigateToCardLostOrStolen();
        boolean one = cardsContinueButton.isDisplayed();
        boolean two = cardsContinueButton.isEnabled();

        return one && two;
    }

    public void navigateToCardLostStolenFurtherInfo(){
        cardsManagement.navigateToCardLostOrStolen();
        click(cardsContinueButton);
    }

    public void contactSupportThroughCardLostOrstolen(){
        login.setLogin("FIRST TIME");
        cardsManagement.navigateToCardLostOrStolen();
        click(contactSupportButton);
    }

}
