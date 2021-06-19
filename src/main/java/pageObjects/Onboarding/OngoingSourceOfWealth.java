package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;
import testData.RandomDataGenerator;
import java.util.concurrent.TimeUnit;

public class OngoingSourceOfWealth  extends BasePage {

    SetUp setUp = new SetUp(driver);
    BusinessSearch businessSearch = new BusinessSearch(driver);
    BusinessDetailsForm businessDetailsForm = new BusinessDetailsForm(driver);
    PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm(driver);
    RandomDataGenerator generator = new RandomDataGenerator();
    CheckBoxElements checkBoxElements = new CheckBoxElements(driver);

    @iOSFindBy(accessibility = "ongoing_source_of_wealth.turnover.heading")
    private MobileElement ongoingQues;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name=\"ongoing_source_of_wealth.turnover.heading\"]") //FIXME get unique accessibility id
    private MobileElement annualTurnoverTextField;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth.fundingSource.heading")
    private MobileElement fundingTextField;

    @iOSFindBy(accessibility = "search exit button")
    private MobileElement fundingSearchExitButton;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth_selection.confirm")
    private MobileElement searchConfirmButton;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth.fundingCountry.heading")
    private MobileElement fundingCountryTextField;

    @iOSFindBy(accessibility = "search exit button")
    private MobileElement countrySearchExitButton;

    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement nextButton;

    @iOSFindBy(accessibility = "GBP")
    private MobileElement gbpTitle;

    @iOSFindBy(accessibility = "ongoing_source_of_wealth.optional_other")
    private MobileElement otherTextField;

    @iOSFindBy(accessibility = "Personal Details")
    private MobileElement wealthBackButton;

    @iOSFindBy(accessibility = "Business Details")
    private MobileElement businessDetailsBackButton;

    @iOSFindBy(accessibility = "business_search.search_field_title.initial")
    private MobileElement businessSearchField;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Business Details\"]")
    private MobileElement businessSearchBackButton;

    //check box elements
    @iOSFindBy(accessibility = "Gift")
    private MobileElement gift;

    @iOSFindBy(accessibility = "Other")
    private MobileElement other;

    public OngoingSourceOfWealth(AppiumDriver driver) {
        super(driver);
    }

    //Elements Displayed

    public boolean isOngoingPageDisplayed() { return ongoingQues.isDisplayed(); }

    // Elements Enabled

    public boolean isNextButtonEnabled(){
         nextButton.isEnabled();
        return nextButton.isEnabled();
    }

    // Click Methods

    public void clickBuisinessFunding() { click(fundingTextField);}

    public void clickFundingOption(String funding){
        MobileElement element = (MobileElement) driver.findElement(By.name(funding));
        click(element);
    }

    public void clickSearchConfirmButton(){click((searchConfirmButton));}

    public void clickFundingSearchExitButton (){ click(fundingSearchExitButton);}

    public void clickCountry(){ click(fundingCountryTextField); }

    public void clickCountrySearchExitButton(){ click(countrySearchExitButton); }

    public void clickNextButton() { click(nextButton); }

    // Write Methods
    public void writeAnnualTurnover (int amount){writeNumber(annualTurnoverTextField,amount);}

    // Clear Methods
    public void clearAnnualTurnover (){clearText(annualTurnoverTextField);}

    //Custom Methods

    public void navigateToOngoingSourceOfWealth (){
        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();
        businessDetailsForm.passThroughBusinessDetailsForm();
        personalDetailsForm.passThroughPersonalDetailsForm();

    }

    public boolean checkAllFieldsAreFilled(){

        navigateToOngoingSourceOfWealth();
        boolean two = !isNextButtonEnabled();
        writeAnnualTurnover(50000);
        boolean three =!isNextButtonEnabled();
        clickBuisinessFunding();
        checkBoxElements.clickRandomElement("Ongoing");
        clickSearchConfirmButton();
        boolean one = isNextButtonEnabled();

        return   one && two && three;
    }

    public boolean incorrectAmounts(){
        navigateToOngoingSourceOfWealth();
        clickBuisinessFunding();
        checkBoxElements.clickRandomElement("Ongoing");
        clickSearchConfirmButton();

        int[] incorrectValues = {0,110000000};
        int[] correctValues = {1,25000,100000000};

        boolean one = true;
        boolean two = true;
        boolean three = true;

        for (int i :incorrectValues){

            writeNumber(annualTurnoverTextField,i);
            String s = annualTurnoverTextField.getText();
            int num;
            if (s.isEmpty()){
                num = 0;
            }
            else {
                num = Integer.parseInt(annualTurnoverTextField.getText());
            }
            if(num == i){
                one = isNextButtonEnabled();
                clearAnnualTurnover();
            }
            else{
                one= true;
                clearAnnualTurnover();
            }
        }
        for (int i :correctValues){

            writeNumber(annualTurnoverTextField,i);
            if(isNextButtonEnabled()){;
                clearAnnualTurnover();
            }
            else{
                clearAnnualTurnover();
                two = false;}
        }
        writeDecimal(annualTurnoverTextField,10.21);

        if(isNextButtonEnabled()){;
            clearAnnualTurnover();
        }
        else{
            clearAnnualTurnover();
            three = false;}
        return  one && two && three;
    }

    public boolean isGbpDisplayed (){
        return gbpTitle.isDisplayed();
    }


    public boolean doFieldsClearWithNewSearch(){

        navigateToOngoingSourceOfWealth();
        writeAnnualTurnover(20);
        clickBuisinessFunding();
        checkBoxElements.clickRandomElement("Ongoing");
        clickSearchConfirmButton();
        click(wealthBackButton);
        click(businessDetailsBackButton);
        click(businessSearchBackButton);

        writeText(businessSearchField,"GLA");
        clickGenericIostableCell();
        businessDetailsForm.passThroughBusinessDetailsForm();
        personalDetailsForm.passThroughPersonalDetailsForm();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String field1 = annualTurnoverTextField.getText();

        if (field1.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean canAllFundingOptionsBeChosen(){

        navigateToOngoingSourceOfWealth();
        writeAnnualTurnover(20);
        clickBuisinessFunding();
        checkBoxElements.clickAllCheckboxElements("Ongoing");
        click(other);
        clickSearchConfirmButton();
        writeText(otherTextField,"Other Element");
        clickNextButton();

        return !isOngoingPageDisplayed();
    }


    public void passThroughOngoingSourceOfWealth(){
        navigateToOngoingSourceOfWealth();
        processOngoingSourceOfWealth();
    }

    public void processOngoingSourceOfWealth(){
        writeAnnualTurnover(20);
        clickBuisinessFunding();
        checkBoxElements.clickRandomElement("Ongoing");
        clickSearchConfirmButton();
        clickNextButton();
    }

    public void populateSourceOfWealth(String turnover, String funding){
        writeText(annualTurnoverTextField, turnover);
        clickBuisinessFunding();
        clickFundingOption(funding);
        clickSearchConfirmButton();
        clickNextButton();
    }

}
