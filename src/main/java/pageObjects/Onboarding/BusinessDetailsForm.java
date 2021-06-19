package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import testData.RandomDataGenerator;
import pageObjects.Base.BasePage;



public class BusinessDetailsForm extends BasePage {

    RandomDataGenerator generator = new RandomDataGenerator();
    SetUp setUp = new SetUp(driver);
    BusinessSearch businessSearch = new BusinessSearch(driver);


    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement nextButton;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"You and your business\"]")//FIXME get accessibility id
    private MobileElement backNavigation;

    @iOSFindBy(accessibility = "business_details.beneficial_owner")
    private MobileElement beneficialOwner;

    @iOSFindBy(accessibility = "business_details.name")
    private MobileElement businessName;

    @iOSFindBy(accessibility = "business_details.address")
    private MobileElement businessAddress;

    @iOSFindBy(accessibility = "business_details.trading_name")
    private MobileElement tradingName;

    @iOSFindBy(accessibility = "radio_buttons_view_default.affirmative")
    private MobileElement tradingAddressDifferentToBusiness;

    @iOSFindBy(accessibility = "radio_buttons_view_default.negative")
    private MobileElement tradingAddressSameAsBusiness;

    @iOSFindBy(accessibility = "business_details.trading_address")
    private MobileElement tradingAddressTextbox;

    @iOSFindBy(accessibility = "business_details.activity")
    private MobileElement businessActivity;

    @iOSFindBy(accessibility = "business_details.additional_details")
    private MobileElement additionalDetails;

    @iOSFindBy(accessibility = "business_details.noOfEmployee")
    private MobileElement numberofemployee;

    @iOSFindBy(accessibility = "Address (number / postcode)")
    private MobileElement addressLookupTitle;

    @iOSFindBy(accessibility = "address_search.search_field_title")
    private MobileElement addressSearchTexbox;

    @iOSFindBy(accessibility = "search exit button")
    private MobileElement countrySearchExitButton;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Business Details\"]")
    private MobileElement btnBacktoCompanyDetails;

    public BusinessDetailsForm(AppiumDriver driver) {
        super(driver);
    }

    public boolean tradingAddressDifferentToResidentialDisplayed(){
        scrollToElement(tradingAddressSameAsBusiness);
        return tradingAddressDifferentToBusiness.isDisplayed();
    }

    //Methods for elements enabled
    public boolean isOwnersNameTextboxEnabled(){
        return Boolean.valueOf(beneficialOwner.getAttribute("visible"));
    }

    public boolean isBusinessNameTextboxEnabled(){
        return Boolean.valueOf(businessName.getAttribute("visible"));
        //return businessName.isEnabled();
    }

    public boolean isBusinessAddressTextboxEnabled(){
        return businessAddress.isEnabled();
    }

    public boolean isSicCodeEnabled(){
        return businessActivity.isEnabled();
    }

    public boolean isTradingAddressEnabled(){
        return tradingAddressTextbox.isEnabled();
    }

    public boolean isNextButtonEnabled(){
        return nextButton.isEnabled();
    }

    //Checkbox default selection
    public boolean noDefaultCheckboxSelection(){
        navigateToBusinessDetails();
        return CheckboxSelected(tradingAddressDifferentToBusiness) != 1 && CheckboxSelected(tradingAddressSameAsBusiness) != 1;
    }

    //WRITE METHODS
    public void writeTradingName(String name){

        writeText(tradingName, name);
    }

    public void writeAdditionalDetails(String details){
        writeText(additionalDetails, details);
    }

    //CUSTOM METHODS
    public boolean verifyTextboxIsEmpty() {
        try {
            MobileElement element = (MobileElement) driver.findElement(By.name("business_details.trading_name"));
            String s = element.getText();
            if (s != null) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean isKeyboardVisible(){
        return doesKeyboardExist();
    }

    public void navigateToBusinessDetails(){
        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();
    }

    public boolean tradingAddressSameAsRegisteredBusinessAddress(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressSameAsBusiness);
        click(tradingAddressSameAsBusiness);
        return readText(businessAddress).equalsIgnoreCase(readText(tradingAddressTextbox));
    }

    public boolean tradingAddressDifferentFromRegisteredBusinessAddress(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressDifferentToBusiness);
        click(tradingAddressDifferentToBusiness);
        writeText(addressSearchTexbox, "BT42FS");
        clickGenericIostableCell();
        return readText(tradingAddressTextbox).equalsIgnoreCase("1 Carolhill Gardens\nBelfast\n" +
                "BT4 2FS\nUnited Kingdom");
    }

    public boolean addressNotFound(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressDifferentToBusiness);
        click(tradingAddressDifferentToBusiness);
        writeText(addressSearchTexbox, "BT9012F");
        return false; //FIXME Needs error message
    }

    public void populateFieldsExceptAdditionalDetails(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressSameAsBusiness);
        click(tradingAddressSameAsBusiness);
    }

    public boolean additionalDetailsDisplayed(){
        navigateToBusinessDetails();
        scrollToElement(additionalDetails);
        return additionalDetails.isDisplayed();
    }

    public boolean additionalDetailsKeyboard(){
        navigateToBusinessDetails();
        scrollToElement(additionalDetails);
        click(additionalDetails);
        return isKeyboardVisible();
    }

    public boolean additionalDetailsNoCharacters(){
        populateFieldsExceptAdditionalDetails();
        scrollToElement(numberofemployee);
        writeNumber(numberofemployee, 1);
        clearText(additionalDetails);
        return !isNextButtonEnabled();
    }

    public boolean additionalDetailsLessThanTwoCharacters(){
        writeText(additionalDetails, "A");
        boolean addtionalDetailsText = readText(additionalDetails).equals("A");
        boolean button = !isNextButtonEnabled();
        clearText(additionalDetails);
        return addtionalDetailsText && button;
    }

    public boolean additionalDetailsMoreThan255Characters(){
        writeText(additionalDetails, generator.setRandomValue(254, "ALPHANUMERIC"));
        String one = readText(additionalDetails);
        writeText(additionalDetails, "01");
        boolean addtionalDetailsText = readText(additionalDetails).equals(one + "0");
        boolean button = isNextButtonEnabled();
        clearText(additionalDetails);
        return addtionalDetailsText && button;
    }

    public boolean additionalDetailsValidEntry(){
        writeText(additionalDetails, "01");
        boolean buttonOne = isNextButtonEnabled();
        clearText(additionalDetails);
        writeText(additionalDetails, generator.setRandomValue(255, "ALPHANUMERIC"));
        boolean buttonTwo = isNextButtonEnabled();
        return buttonOne && buttonTwo;
    }

    public void passThroughBusinessDetailsForm(){
        click(tradingAddressSameAsBusiness);
        scrollToElement(additionalDetails);
        writeText(additionalDetails, "Additional Details");
        scrollToElement(numberofemployee);
        writeNumber(numberofemployee, 1);
        click(nextButton);
    }

    public void populateBusinessDetailsForm(String name, int employeeCount){
        writeText(tradingName, name);
        click(tradingAddressSameAsBusiness);
        scrollToElement(additionalDetails);
        writeText(additionalDetails, "Additional Details");
        scrollToElement(numberofemployee);
        writeNumber(numberofemployee, employeeCount);

        click(nextButton);
    }

    public void clickNext(){
        click(nextButton);
    }

    public void clickBackToCompanyDetails(){
        click(btnBacktoCompanyDetails);
    }

    public void businessDetailsMandatoryFields(boolean isTradingAddressSameAsBusiness, String additionalInfo,
                                               int employeeCount){
//        if(isTradingAddressSameAsBusiness){
//            //click(tradingAddressDifferentToBusiness);
//            //writeText("");
//        }else
        //To do

        if(isTradingAddressSameAsBusiness){
            scrollToElement(tradingAddressSameAsBusiness);
            click(tradingAddressSameAsBusiness);
        }

        scrollToElement(additionalDetails);
        writeText(additionalDetails, additionalInfo);
        scrollToElement(numberofemployee);
        writeNumber(numberofemployee, employeeCount);
    }

    public void setNumberofemployee(String number){
        clearText(numberofemployee);
        writeText(numberofemployee, number);
    }

    public boolean findAddressPostcode(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressDifferentToBusiness);
        click(tradingAddressDifferentToBusiness);
        writeText(addressSearchTexbox, "BT");
        boolean one = false;
        try {
            clickGenericIostableCell();
        }catch (NoSuchElementException e){
            one = true;
        }
        writeText(addressSearchTexbox, "42FS");
        clickGenericIostableCell();
        boolean two = readText(tradingAddressTextbox).equalsIgnoreCase("1 Carolhill Gardens\nBelfast\n" +
                "BT4 2FS\nUnited Kingdom");
        return one && two;
    }
    public boolean findAddress(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressDifferentToBusiness);
        click(tradingAddressDifferentToBusiness);
        writeText(addressSearchTexbox, "1 ");
        boolean one = false;
        try {
            clickGenericIostableCell();
        }catch (NoSuchElementException e){
            one = true;
        }
        writeText(addressSearchTexbox, "Carolhill Gardens");
        clickGenericIostableCell();
        boolean two = readText(tradingAddressTextbox).equalsIgnoreCase("1 Carolhill Gardens\nBelfast\n" +
                "BT4 2FS\nUnited Kingdom");
        return one && two;
    }
    public boolean findInvalidAddress(){
        navigateToBusinessDetails();
        scrollToElement(tradingAddressDifferentToBusiness);
        click(tradingAddressDifferentToBusiness);
        writeText(addressSearchTexbox, "!!!!!!!!!");
        boolean one = false;
        try {
            clickGenericIostableCell();
        }catch (NoSuchElementException e){
            one = true;
        }
        click(countrySearchExitButton);
        boolean two = readText(tradingAddressTextbox).equals("");
        return one && two;
    }

    public void clickBackNavigation(){
        click(backNavigation);
    }
}
