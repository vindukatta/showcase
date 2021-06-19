package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.Base.BasePage;

public class PersonalDetailsForm extends BasePage {

    BusinessSearch businessSearch = new BusinessSearch(driver);
    BusinessDetailsForm businessDetailsForm = new BusinessDetailsForm(driver);
    SetUp setUp = new SetUp(driver);

    public PersonalDetailsForm(IOSDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(accessibility = "About you & your business")
    private MobileElement aboutBusiness;

    @iOSFindBy (accessibility = "A bit about you")
    private MobileElement personalDetailsPageTitle;

    @iOSFindBy (accessibility = "personal_details.title")
    private MobileElement personTitle;

    @iOSFindBy (accessibility = "personal_details.first_name")
    private MobileElement personFirstName;

    @iOSFindBy (accessibility = "personal_details.last_name")
    private MobileElement personLastName;

    @iOSFindBy (accessibility = "personal_details.dob")
    private MobileElement personDOB;

    @iOSFindBy (accessibility = "personal_details.ra_affirmative")
    private MobileElement personBusinessAndResidentialEqualYes;

    @iOSFindBy(accessibility = "personal_details.ra_negative")
    private MobileElement personBusinessAndResidentialEqualNo;

    @iOSFindBy(accessibility = "address_search.search_field_title")
    private MobileElement addressSearch;

    @iOSFindBy(accessibility = "personal_details.address")
    private MobileElement personResidentialAddress;

    @iOSFindBy(accessibility = "personal_details.email")
    private MobileElement personEmail;

    @iOSFindBy(accessibility = "personal_details.email_confirmation")
    private MobileElement personConfirmEmail;

    @iOSFindBy(accessibility = "Business Details")
    private MobileElement backToBusinessDetails;

    @iOSFindBy (xpath = "//XCUIElementTypeButton[@name=\"Business Details\"]")
    private MobileElement backToCompanyReview;

    @iOSFindBy (accessibility = "toolbar_done")
    private MobileElement pickerWheelDone;

    @iOSFindBy (accessibility = "toolbar_cancel")
    private MobileElement pickerWheelCancel;

    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker[@name=\"personal_details.dob_datepicker\"]/XCUIElementTypeOther/XCUIElementTypePickerWheel[2]")
    private MobileElement dayPickerWheel;

    @iOSFindBy (accessibility = "next_button_default_title")
    private MobileElement nextButton;


    public PersonalDetailsForm(AppiumDriver driver) {
        super(driver);
    }

    public void naviagateToPersonalDetailsForm(){
        setUp.passThroughSetUp();
        businessSearch.passThroughBusinessSearch();
        businessDetailsForm.passThroughBusinessDetailsForm();
    }

    public boolean personalDetailsDisplayed(){
        naviagateToPersonalDetailsForm();
        boolean one = personTitle.isDisplayed();
        boolean two = personFirstName.isDisplayed();
        boolean three = personLastName.isDisplayed();
        return one && two && three;
    }

    public boolean navigateBackToBusinessSearch(){
        naviagateToPersonalDetailsForm();
        click(backToBusinessDetails);
        click(backToCompanyReview);
        businessDetailsForm.clickBackNavigation();
        boolean two = businessSearch.businessSearchTitleDisplayed();
        return two;
    }

    public void passThroughPersonalDetailsForm(){
        if(driver.findElements(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']")).size() > 0){

            WebElement salutationWheel = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']"));
            salutationWheel.sendKeys("Dr.");
            click(pickerWheelDone);
            click(personDOB);
        }
        WebElement wheel = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(1);
        wheel.sendKeys("2");
        click(pickerWheelDone);
        click(personBusinessAndResidentialEqualYes);
        click(nextButton);
    }

    public void personalDetailsWitData(String address){
        if(driver.findElements(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']")).size() > 0){

            WebElement salutationWheel = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']"));
            salutationWheel.sendKeys("Dr.");
            click(pickerWheelDone);
            click(personDOB);
        }
        WebElement wheel = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(1);
        wheel.sendKeys("2");
        click(pickerWheelDone);
//        click(personBusinessAndResidentialEqualNo);
//        writeText(addressSearch, address);
//        clickGenericIostableCell();
        click(personBusinessAndResidentialEqualYes);
        click(nextButton);
    }

    public void clickBackToBusinessDetailsScreen(){
        click(backToBusinessDetails);
    }


    public boolean isNextButtonEnabled(){
        return nextButton.isEnabled();
    }

    public void personalDetailsMandatoryFields(boolean setDOB, boolean isHomeAddressSameAsBusinessAddress, boolean setSalutation){

        if(driver.findElements(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']")).size() > 0){

            if(setSalutation == true) {
                WebElement salutationWheel = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='drop_down_component0']"));
                salutationWheel.sendKeys("Dr.");
                click(pickerWheelDone);
            }else {
                click(pickerWheelCancel);
            }
            if(setDOB){
                click(personDOB);
            }
        }

        if(setDOB) {
            WebElement wheel = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(1);
            wheel.sendKeys("2");
            click(pickerWheelDone);
        }

        if(isHomeAddressSameAsBusinessAddress) {
            click(personBusinessAndResidentialEqualYes);
        }
    }
}
