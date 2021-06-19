package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;

public class TransactionDetails extends BasePage {

    CheckBoxElements checkBoxElements = new CheckBoxElements(driver);

    public TransactionDetails(IOSDriver driver) {
        super(driver);
    }

    @iOSFindBy(accessibility = "Transaction details")
    private MobileElement transactionDetailsTitle;

    @iOSFindBy(accessibility = "transaction_details.account_usage")
    private MobileElement accountUsageTextField;

    @iOSFindBy(accessibility = "transaction_details.expected_payments")
    private MobileElement transactionTypeTextField;

    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement nextButton;

    // Summary page elements
    @iOSFindBy(accessibility = "submit")
    private MobileElement submitButton;

    @iOSFindBy (accessibility = "All Done!")
    private MobileElement allDoneMessage;

    @iOSFindBy(accessibility = "transaction_details_selection.confirm")
    private MobileElement confirmButton;

    public TransactionDetails(AppiumDriver driver) {
        super(driver);
    }

    public boolean isAllDoneDisplayed() { return allDoneMessage.isDisplayed();}

    //Elements Enabled
    public boolean isNextButtonEnabled () { return nextButton.isEnabled(); }

    // Click Methods
    public void clickAccountUsageField(){ click(accountUsageTextField); }

    public void clickTransactionTypes(){ click(transactionTypeTextField); }

    public void clickNextButton() { click(nextButton);}

    public  void clickSubmit() {click(submitButton);}

    public void clickConfirm() {click(confirmButton);}

    //Custom Methods
    public void passThroughTransactionDetails(){
        clickAccountUsageField();
        checkBoxElements.clickRandomElement("AccountUsage");
        clickConfirm();
        clickTransactionTypes();
        checkBoxElements.clickRandomElement("TransactionType");
        clickConfirm();
        clickNextButton();
    }

    public void populateTransactionDetails(String accountUsage, String transactionType){
        clickAccountUsageField();
        clickFundingOption(accountUsage);
        clickConfirm();
        clickTransactionTypes();
        clickTransactionTypeOption(transactionType);
        clickConfirm();
        clickNextButton();
    }

    public void clickFundingOption(String accountUsage){
        MobileElement element = (MobileElement) driver.findElement(By.name(accountUsage));
        click(element);
    }

    public void clickTransactionTypeOption(String transactionType){
        MobileElement element = (MobileElement) driver.findElement(By.name(transactionType));
        click(element);
    }

    public boolean processSummaryPage(){
        clickSubmit();
        return isAllDoneDisplayed();
    }
}