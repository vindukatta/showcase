package pageObjects.More;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import pageObjects.Base.BasePage;

public class More extends BasePage {

    @iOSFindBy(accessibility = "More")
    private MobileElement moreButton;

    @iOSFindBy(accessibility = "more.cell_title.details")
    private MobileElement detailsButton;

    @iOSFindBy(accessibility = "more.cell_title.accounts")
    private MobileElement accountsButton;

    @iOSFindBy(accessibility = "more.cell_title.card_management")
    private MobileElement cardManagmentButton;

    @iOSFindBy(accessibility = "more.cell_title.credit_and_lending")
    private MobileElement creditAndLendingButton;


    public More(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToCardManagement(){
        click(moreButton);
        click(cardManagmentButton);
    }


}

