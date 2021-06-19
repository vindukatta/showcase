package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;
import testData.RandomDataGenerator;

public class InitialWealth extends BasePage {

    SetUp setUp = new SetUp(driver);
    BusinessSearch businessSearch = new BusinessSearch(driver);
    BusinessDetailsForm businessDetailsForm = new BusinessDetailsForm(driver);
    PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm(driver);
    OngoingSourceOfWealth ongoingSourceOfWealth = new OngoingSourceOfWealth(driver);
    RandomDataGenerator generator = new RandomDataGenerator();
    CheckBoxElements checkBoxElements = new CheckBoxElements(driver);


    @iOSFindBy (accessibility = "Initial Source of Wealth")
    private MobileElement initialWealthTitle;

    @iOSFindBy(accessibility = "initial_source_of_wealth.amount.heading")
    private MobileElement initialAmountTextField;

    @iOSFindBy(accessibility = "initial_source_of_wealth.number_of_investors.heading")
    private MobileElement numOfInvestorsTextField;

    @iOSFindBy(accessibility = "initial_source_of_wealth.add_investor")
    private MobileElement investorDetailsButton;

    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement nextButton;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Business financials\"]")
    private MobileElement initialWealthBB;

    @iOSFindBy(accessibility = "Investor Details")
    private MobileElement investorsDetailsTitle;

    @iOSFindBy(accessibility = "investor_details.name.heading")
    private MobileElement investorNameField;

    @iOSFindBy(accessibility = "investor_details.amount.heading")
    private MobileElement investorAmount;

    @iOSFindBy(accessibility = "investor_details.country.heading")
    private MobileElement investorCountry;

    @iOSFindBy(accessibility = "initial_source_of_wealth.funding_source.heading")
    private MobileElement howFundsGenerated;

    @iOSFindBy(accessibility = "search exit button")
    private MobileElement exitButton;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth_selection.confirm")
    private MobileElement confirmButton;

    @iOSFindBy(accessibility = "Gift")
    private  MobileElement gift;

    @iOSFindBy(accessibility = "country_search.search_field_title")
    private MobileElement countrySearchfield;

    @iOSFindBy(accessibility = "toolbar_done")
    private MobileElement pickerWheelDone;

    public InitialWealth(AppiumDriver driver) {
        super(driver);
    }

    //Elements Displayed

    public boolean isInitialWealthPageDisplayed() { return initialWealthTitle.isDisplayed(); }

    public boolean isInvestorsDetailsPageDisplayed() { return investorsDetailsTitle.isDisplayed(); }


    // Elements Enabled

    public boolean isNextButtonEnabled(){return nextButton.isEnabled(); }

    // Click Methods

    public void clickNext(){ click(nextButton);}

    public void clickConfirm() {click(confirmButton);}

    public void clickExit(){click(exitButton);}

    //Custom Methods

    public void navigateToInitialWealth(){
        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessUnderThreeYearsSearch();
        businessDetailsForm.passThroughBusinessDetailsForm();
        personalDetailsForm.passThroughPersonalDetailsForm();
        ongoingSourceOfWealth.passThroughOngoingSourceOfWealth();

    }

    public void passThroughInitalWealth(){
        if(driver.findElements( By.name("initial_source_of_wealth.amount.heading")).size() != 0) {
            writeNumber(initialAmountTextField, 10800);
            click(howFundsGenerated);
            checkBoxElements.clickRandomElement("Initial");
            clickConfirm();
            clickNext();
        }
    }

    public void clickFundingOption(String funding){
        MobileElement element = (MobileElement) driver.findElement(By.name(funding));
        click(element);
    }

    public void populateInitialWealth(String initialAmount, String fundingOption){
        if(driver.findElements( By.name("initial_source_of_wealth.amount.heading")).size() != 0) {
            writeText(initialAmountTextField, initialAmount);
            click(howFundsGenerated);
            clickFundingOption(fundingOption);
            clickConfirm();
            clickNext();
        }
    }

}