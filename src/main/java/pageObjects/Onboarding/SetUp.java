package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Base.BasePage;
import testData.RandomDataGenerator;

public class SetUp extends BasePage {

    private RandomDataGenerator generator = new RandomDataGenerator();

    @iOSFindBy(accessibility = "eligibility.confirm_title")
    private MobileElement eligibilityConfirm;

    @iOSFindBy(accessibility = "eligibility.decline_title")
    private MobileElement eligibilityDecline;

    @iOSFindBy(accessibility = "Personal Details")
    private MobileElement phoneNumberTitle;

    @iOSFindBy(accessibility = "phone_number_entry.phone_number_textfield_header")
    private MobileElement phoneNumberTextbox;

    @iOSFindBy(accessibility = "Your e-mail address")
    private MobileElement emailTitle;

    @iOSFindBy(accessibility = "email_entry.textfield_header")
    private MobileElement emailTextbox;

    @iOSFindBy(accessibility = "Next")
    private MobileElement nextButton;

    @iOSFindBy(accessibility = "welcome.new_customer_button_title")
    private MobileElement startButton;

    @iOSFindBy(accessibility = "Great, you're ready to get started!")
    private MobileElement titleGetStarted;

    @iOSFindBy(accessibility = "We're about to ask you about your")
    private MobileElement landingPageDescriptionOne;

    @iOSFindBy(className = "• Business details\n • Personal details\n • Business Financials")//FIXME get accessibility locator without bullet points
    private MobileElement landingPageDescriptionTwo;

    @iOSFindBy(accessibility = "Be aware that once you confirm your details your information can't be changed")
    private MobileElement landingPageDescriptionThree;

    public SetUp(AppiumDriver driver) {
        super(driver);
    }

    public boolean nextButtonEnabled(){
        return nextButton.isEnabled();
    }

    //WRITE METHODS
    public void writePhoneNumber(String number){
        writeText(phoneNumberTextbox, number);
    }

    public void writeEmail(String email){
        writeText(emailTextbox, email);
    }

    public void clickPhoneNumberTextbox(){
        click(phoneNumberTextbox);
    }

    public void clickEmailTextbox(){
        click(emailTextbox);
    }

    public void clickNext(){
        click(nextButton);
    }

    public void clickStart(){
        click(startButton);
    }

    public void clearEmail(){
        clearText(emailTextbox);
    }


    public void navigateToEmail(){
        clickStart();
        writePhoneNumber(generator.setPhoneNumber());
        clickNext();
    }

    public void navigateToLandingPage(){
        clickStart();
        writePhoneNumber(generator.setPhoneNumber());
        clickNext();
        writeEmail(generator.setEmail("", 0));
        clickNext();
    }

    public boolean isPhoneNumberFieldDisplayed(){
        clickStart();
        return phoneNumberTextbox.isDisplayed();
    }

    public boolean isEmailFieldDisplayed(){
        navigateToEmail();
        return emailTextbox.isDisplayed();
    }

    public boolean isPhoneKeyboardDisplayed(){
        clickStart();
        clickPhoneNumberTextbox();
        return doesKeyboardExist();
    }

    public boolean isEmailKeyboardDisplayed(){
        navigateToEmail();
//        emailTextbox.click();
        return doesKeyboardExist();
    }

    public boolean invalidPhoneNumber(){
        clickStart();

        //invalid phone entry
        writePhoneNumber("01234567890");
        boolean noSeven = !nextButtonEnabled();

        clearText(phoneNumberTextbox);
        writePhoneNumber("+441234567890");
        boolean noSevenAfterPrefix = !nextButtonEnabled();

        clearText(phoneNumberTextbox);
        writePhoneNumber("0723456789");
        boolean shortNumber = !nextButtonEnabled();

        clearText(phoneNumberTextbox);
        writePhoneNumber("071345678901");
        boolean longNumber = !nextButtonEnabled();

        return noSeven && noSevenAfterPrefix && shortNumber && longNumber;
    }

    public boolean validPhonenumber(){
        clickStart();
        writePhoneNumber(generator.setPhoneNumber());
        return nextButtonEnabled();
    }

    public boolean invalidEmail(){
        navigateToEmail();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(emailTextbox));
        boolean initialButtonEnabled = !nextButtonEnabled();

        clearEmail();
        writeEmail("random.email");
        boolean noAtSymbol = !nextButtonEnabled();

        clearEmail();
        writeEmail("random@email");
        boolean noDotInTheDomain = !nextButtonEnabled();

        clearEmail();
        writeEmail("random@email.co#");
        boolean specialCharacterInDomain = !nextButtonEnabled();

        clearEmail();
        writeEmail("@email.com");
        boolean nothingBeforeAtSymbol = !nextButtonEnabled();

        clearEmail();
        writeEmail("email@");
        boolean noDomain = !nextButtonEnabled();

        clearEmail();
        writeEmail("email@"+generator.setRandomValue(313, "ALPHANUMERIC")+".com");
        boolean characterLengthLong = !nextButtonEnabled();

        clearEmail();
        writeEmail("e@.c");
        boolean characterLengthShort = !nextButtonEnabled();

        return initialButtonEnabled && noAtSymbol && noDotInTheDomain && specialCharacterInDomain
                && nothingBeforeAtSymbol && noDomain && characterLengthLong && characterLengthShort;
    }

    public boolean validEmail(){
        navigateToEmail();
        clearEmail();
        writeEmail("email1@email.com");
        boolean numberBeforeDomain = nextButtonEnabled();

        clearEmail();
        writeEmail("email@em-ail.com");
        boolean hyphen = nextButtonEnabled();

        clearEmail();
        writeEmail("email$!@email.com");
        boolean specialCharacters = nextButtonEnabled();

        clearEmail();
        writeEmail("email@"+ generator.setRandomValue(312, "ALPHANUMERIC")+".com");
        boolean maxLength = nextButtonEnabled();

        return numberBeforeDomain && hyphen && specialCharacters && maxLength;
    }

    public boolean landingPageDisplayed(){
        navigateToLandingPage();
        return titleGetStarted.isDisplayed();
    }

    public boolean landingPageDescription(){
        navigateToLandingPage();
        boolean one = readText(landingPageDescriptionOne).equalsIgnoreCase("We're about to ask you about your");
        // boolean two = readText(landingPageDescriptionTwo).equalsIgnoreCase("• Business details\n • Personal details\n • Business Financials");
        boolean three = readText(landingPageDescriptionThree).equalsIgnoreCase("Be aware that once you confirm your details your information can't be changed");
        return one && three;
    }

    public void passThroughSetUp(){
        clickStart();
        writePhoneNumber("07123456789");
        clickNext();
        writeEmail(generator.setEmail("", 0));
        clickNext();
    }

    public void setUpWithData(String phoneNumber, String Email){
        clickStart();
        writePhoneNumber(phoneNumber);
        takeScreenshot();
        clickNext();
        writeEmail(Email);
        takeScreenshot();
        clickNext();
    }
}
