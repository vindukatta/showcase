package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;

public class SourceOfFunds extends BasePage {

    CheckBoxElements checkBoxElements = new CheckBoxElements(driver);

    @iOSFindBy(accessibility = "Source of Funds")
    private MobileElement sourceOfFundstitle;

    @iOSFindBy (xpath = "//XCUIElementTypeTextField[@name=\"depositors.last_twelve_months_amount\"]")  //FIXME get unique accessibility id
    private MobileElement valueOfCashDepositorField;

    @iOSFindBy (xpath = "//XCUIElementTypeTextField[@name=\"depositors.depositors_total_amount.header\"]")  //FIXME get unique accessibility id
    private MobileElement totalDepositMoneyField;

    @iOSFindBy (accessibility = "depositors.depositors_count_textfield.header")
    private MobileElement numberOfDepositors;

    @iOSFindBy (accessibility = "depositors.add_depositor_details.buttonTitle")
    private MobileElement depositorDetailButton;

    @iOSFindBy (accessibility = "Ff Gg")
    private MobileElement depositorDetail;

    @iOSFindBy (accessibility = "next_button_default_title")
    private  MobileElement nextButton;

    @iOSFindBy (accessibility = "depositor.funds_generated.header")
    private  MobileElement howFundsGenerated;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth_selection.confirm")
    private MobileElement confirmButton;

    @iOSFindBy ( accessibility = "search exit button")
    private MobileElement howFundsGeneratedExitButton;

    @iOSFindBy ( accessibility = "next_button_default_title")
    private MobileElement depositorNextButon;

    public SourceOfFunds(AppiumDriver driver) {
        super(driver);
    }

    // Element Displayed
    public boolean isSourceOfFundsPageDisplayed() { return sourceOfFundstitle.isDisplayed(); }

    public void clickNextButton() { click(nextButton); }

    public void clickHowFundsGenerated() { click(howFundsGenerated); }

    public void clickDepositorNextButton() { click(depositorNextButon); }

    //Write Methods
    public void writeValueOfCashDeposit(int amount) { writeNumber(valueOfCashDepositorField, amount);}

    public void wrireTotalDepositMoney(int amount) { writeNumber(totalDepositMoneyField, amount);}

   // Custom Methods
    public void clickFundingOption(String funding){
        MobileElement element = (MobileElement) driver.findElement(By.name(funding));
        click(element);
    }

    public void processSourceOfFunds(){

        wrireTotalDepositMoney(200);
        clickHowFundsGenerated();

        checkBoxElements.clickRandomElement("SourceOfFunds");
        click(confirmButton);

        writeValueOfCashDeposit(500);

        clickDepositorNextButton();
    }

    public void populateSourceOfFunds(String cash, String howFundsGenerated, String depositTotal) {
        writeText(valueOfCashDepositorField, cash);
        clickHowFundsGenerated();
        clickFundingOption(howFundsGenerated);
        click(confirmButton);
        writeText(totalDepositMoneyField, depositTotal);
        clickNextButton();
    }
}