package pageObjects.More;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Base.BasePage;

public class CardsManagement extends BasePage {

    private More more = new More(driver);

    @FindBy(name = "Card management")
    private MobileElement cardManagementTitle;

    @iOSFindBy(accessibility = "card_management.rows.report_lost_or_stolen")
    private MobileElement reportLostAndStolenButton;

    @iOSFindBy(accessibility = "CardPlaceholder")
    private MobileElement cardImage;

    @iOSFindBy(accessibility = "card_management.rows.replace_damage_or_broken")
    private MobileElement replaceDamagedOrStolenButton;

    @iOSFindBy(accessibility = "activate_card.button.primary_cta")
    private MobileElement activateCardButton;

    @iOSFindBy(accessibility = "More")
    private MobileElement clickBack;

    @iOSFindBy(accessibility = "cards.button.contact_support")
    private MobileElement contactSupportButton;

    public CardsManagement(AppiumDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed(){
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(cardManagementTitle));
        return cardManagementTitle.isDisplayed();
    }

    public void clickBack(){
        click(clickBack);
    }

    public ActivateCard clickActivateCardButton(){
        click(activateCardButton);
        return new ActivateCard(driver);
    }

    public void clickReportLostOrStolenButton() {
        click(reportLostAndStolenButton);
    }

    public void clickContactSupportButton() {
        click(contactSupportButton);
    }

    public boolean cardManagementScreenHasReportLostStolen() {
        more.navigateToCardManagement();
        try {
            if (activateCardButton.isDisplayed()){
                clickActivateCardButton().passThroughSuccessfulCardActivation();
            }
        } catch (NoSuchElementException e){
            boolean one = isTitleDisplayed();
            boolean two = reportLostAndStolenButton.isDisplayed();
            return one && two;
        }

        boolean one = isTitleDisplayed();
        boolean two = reportLostAndStolenButton.isDisplayed();
        System.out.println(one && two);
        return one && two;
    }

    public void navigateToCardLostOrStolen(){
        more.navigateToCardManagement();
        try {
            if (activateCardButton.isDisplayed()){
                clickActivateCardButton().passThroughSuccessfulCardActivation();
            }
        } catch (NoSuchElementException e){
            click(reportLostAndStolenButton);
        }

        click(reportLostAndStolenButton);
    }

    public void navigateToActivateCard(){
        more.navigateToCardManagement();
        click(activateCardButton);
    }

   public boolean cardManagementScreenHasActivateCard(){
       more.navigateToCardManagement();
       boolean one = isTitleDisplayed();
       boolean two = activateCardButton.isEnabled();
       click(activateCardButton);
       return one && two;
   }


}

