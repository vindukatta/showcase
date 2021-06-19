package pageObjects.Payments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Login.Login;
import pageObjects.Base.BasePage;

public class DirectDebit extends BasePage {

    Login login = new Login(driver);


    @iOSFindBy(accessibility = "standing_orders.table.header")
    private MobileElement standingOrders;

    @iOSFindBy(accessibility = "View scheduled payments")
    private MobileElement viewSPayments;

    @iOSFindBy(accessibility = "Payments")
    private MobileElement directDebitBB;

    @iOSFindBy(accessibility = "direct_debits.tab_bar_title")
    private MobileElement directDebitTitle;

    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"RECENT\"])[2]")
    private MobileElement recentDirectDebits;

    @iOSFindBy(accessibility = "You have no direct debits.")
    private MobileElement noDDTitle;

    @iOSFindBy(accessibility = "A Direct Debit is an agreement between your bank and a third party " +
            "to take money from your account on a recurring series of dates, usually to pay for bills.")
    private MobileElement noDDMessage;

    @iOSFindBy(accessibility = "direct_debits.table.load_more")
    private MobileElement loadMoreBtn;

    @iOSFindBy(accessibility = "direct_debits.table.footer")
    private MobileElement infomativeText;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[199]")
    private MobileElement lastDD;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[5]")
    private MobileElement firstRecentDD;

    @iOSFindBy(accessibility = "tab_bar.payments_title")
    private MobileElement paymentsTab;

    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Payments\"]")
    private MobileElement paymentsTitle;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[2]")
    private MobileElement merchantName;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"bottomLeftLabel\"])[1]")
    private MobileElement paymentCategrory;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])[1]")
    private MobileElement lastPaymentField;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topRightLabel\"])[1]")
    private MobileElement dDPaymentAmount;

    @iOSFindBy(accessibility = "There may be some direct debits missing. Please try again later.")
    private MobileElement networkErrMessage;

    @iOSFindBy(accessibility = "alertWarning")
    private MobileElement alertImage;

    @iOSFindBy(accessibility = "There may be some direct debits missing.")
    private MobileElement failureMessage;

    @iOSFindBy(accessibility = "Support chat")
    private MobileElement supportChatTitle;

    @iOSFindBy(accessibility = "Close")
    private MobileElement closeSupportChat;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/" +
            "XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther[1]/XCUIElementTypeButton")
    private MobileElement contactSupportBtn;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/" +
            "XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton")
    private MobileElement threeCSB;

    @iOSFindBy(accessibility = "direct_debit_details.cancel")
    private MobileElement cancelDDButton;

    @iOSFindBy(accessibility = "direct_debit_details.header")
    private MobileElement dDDetailsTitle;

    @iOSFindBy(accessibility = "Please make sure to contact your payee if you would like to make changes to " +
            "this direct debit.")
    private MobileElement detailsInformativeText;

    @iOSFindBy(accessibility = "Scheduled payments")
    private MobileElement detailsBB;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[1]")
    private MobileElement lastPayAmount;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[2]")
    private MobileElement lastPayDate;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[3]")
    private MobileElement reference;

    @iOSFindBy(accessibility = "Go back")
    private MobileElement goBackButton;

    @iOSFindBy(accessibility = "Cancel direct debit")
    private MobileElement cancelDDButton2;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Are you sure you want to cancel Eon direct debit?\"]")
    private MobileElement areYouSureText;

    @iOSFindBy(accessibility = "You will need to contact the third party to set this direct debit up again. Make sure" +
            " to contact the payee to confirm that you don’t have a balance outstanding and that you are no longer" +
            " being charged.")
    private MobileElement cancelDisclaimerText;

    @iOSFindBy(accessibility = "direct_debit_details.alert.success.title")
    private MobileElement cancelSuccessTitle;

    @iOSFindBy(accessibility = "direct_debit_details.alert.partial_success.title")
    private MobileElement partialSuccessTitle;

    @iOSFindBy(accessibility = "direct_debit_details.alert.failure.title")
    private MobileElement cancelFailureTitle;

    @iOSFindBy(accessibility = "direct_debit_details.alert.failure.primaryCTA")
    private MobileElement failContactSupport;

    @iOSFindBy(accessibility = "direct_debit_details.alert.failure.secondaryCTA")
    private MobileElement failDoneButton;

    @iOSFindBy(accessibility = "direct_debit_details.alert.partial_success.primaryCTA")
    private MobileElement pSContactSupport;

    @iOSFindBy(accessibility = "direct_debit_details.alert.partial_success.secondaryCTA")
    private MobileElement pSDoneButton;

    @iOSFindBy(accessibility = "direct_debit_details.alert.success.primaryCTA")
    private MobileElement cancelSuccessReturnButton;

    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"NEW\"])[1]")
    private MobileElement newTitle;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]")
    private MobileElement firstNewDD;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[2]")
    private MobileElement secondNewDD;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[9]")
    private MobileElement alreadyDD;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[10]")
    private MobileElement partialSuccessDD;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[16]")
    private MobileElement failureDD;

    @iOSFindBy(accessibility = "direct_debit_details.alert.already_cancelled.title")
    private MobileElement alreadyTitle;

    @iOSFindBy(accessibility = "direct_debit_details.alert.already_cancelled.primaryCTA")
    private MobileElement alreadyCloseBtn;

    @iOSFindBy(accessibility = "standing_orders.tab_bar_title")
    private MobileElement sOTab;

    @iOSFindBy(accessibility = "one_off_payments.tab_bar_title")
    private MobileElement oOPTab;


    String warningLocation = "(16, 124)";

    public DirectDebit(AppiumDriver driver) {
        super(driver);
    }

    // Custom Methods



    public boolean isUpComingDisplayed() {
        try {
            return standingOrders.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void navigateToDDScreenAsUser(String user, String pass) {
        login.loginAsUser(user, pass);
        click(paymentsTab);
        clickViewSPayements();
    }

    public void navigateToDDScreen() {
        login.navigateToLogin();
        click(paymentsTab);
        clickViewSPayements();
    }
    // Display Methods

    public boolean isRecentDisplayed() {
        try {
            return recentDirectDebits.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPaymentPageDisplayed() {

        try {
            return paymentsTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isViewSPaymentsDisplayed() {

        try {
            return viewSPayments.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSOTabDisplayed() {
        try {
            return sOTab.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDirectDebitDisplayed() {
        try {
            return directDebitTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Click Methods
    public void clickFRecentDD() {
        click(firstRecentDD);
    }

    public void clickDetailsBB() {
        click(detailsBB);
    }

    public void clickViewSPayements() {
        click(viewSPayments);
    }

    public void clickDirectDebitsBB() {
        click(directDebitBB);
    }

    public void clickSOTab() {
        click(sOTab);
    }

    public void clickOOPTab() {
        click(oOPTab);
    }

    // Test Methods (//XCUIElementTypeOther[@name="RECENT"])[2]

    public boolean emptyState() {
        navigateToDDScreenAsUser("NOTRANSACTIONUSER", "NOTRANSACTIONPASSWORD");
        return noDDMessage.isDisplayed();
    }

    public boolean BBValidation() {
        navigateToDDScreen();
        clickDirectDebitsBB();
        return isPaymentPageDisplayed();
    }

    public boolean isInformativeTextShown() {
        navigateToDDScreen();
        return infomativeText.isDisplayed();
    }

    public boolean displayDD() {
        navigateToDDScreen();
        boolean one = recentDirectDebits.isDisplayed();
        boolean two = dDPaymentAmount.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean three = merchantName.getText().matches(".*");
        boolean four = lastPaymentField.getText().matches("^Last payment on\\s+[a-zA-Z]{3}\\s+\\d{2}$");
        boolean five = paymentCategrory.getText().equals("Direct Debit");

        return one && two && three && four && five;
    }

    public boolean isFailureErrMsgDisplayed() {
        navigateToDDScreenAsUser("THREETRANUSER", "TESTPASSWORD");
        boolean one = failureMessage.isEnabled() && noDDMessage.isDisplayed();
        click(threeCSB);
        boolean two = supportChatTitle.isDisplayed();

        return one && two;
    }

    public boolean isNetworkErrMsgDisplayed() {
        navigateToDDScreenAsUser("TWOTRANUSER", "TESTPASSWORD");
        boolean one = networkErrMessage.isEnabled() && alertImage.isEnabled() && noDDMessage.isDisplayed();
        boolean two = alertImage.getLocation().toString().equals(warningLocation);

        return one && two;
    }

    public boolean isRetryErrMsgDisplayed() {
        navigateToDDScreenAsUser("ONETRANUSER", "TESTPASSWORD");
        boolean one = networkErrMessage.isEnabled() && alertImage.isEnabled() && recentDirectDebits.isDisplayed();
        boolean two = alertImage.getLocation().toString().equals(warningLocation);

        return one && two;
    }

    public boolean loadDDDetails() {
        navigateToDDScreen();
        String str1 = firstRecentDD.getText();
        click(firstRecentDD);
        boolean one = driver.findElementByName(str1).getLocation().toString().equals("(0, 20)");
        boolean two = detailsInformativeText.isEnabled() && dDDetailsTitle.isDisplayed();

        return one && two;
    }

    public boolean backFromDetailsPage() {
        navigateToDDScreen();
        clickFRecentDD();
        clickDetailsBB();
        return directDebitTitle.isDisplayed();
    }

    public boolean viewDDPage() {
        navigateToDDScreen();
        clickFRecentDD();
        boolean one = dDDetailsTitle.isDisplayed();
        boolean two = lastPayAmount.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean three = lastPayDate.getText().matches("^\\d{2}\\s[a-zA-z]{3,9},\\s\\d{4}$");
        boolean four = reference.getText().matches(".*");

        return one && two && three && four;
    }

    public boolean isCancelDisplayed() {
        navigateToDDScreen();
        clickFRecentDD();
        return cancelDDButton.isEnabled();
    }

    public boolean cancelPopupVerification() {
        isCancelDisplayed();
        click(cancelDDButton);
        return cancelDisclaimerText.isDisplayed() && goBackButton.isDisplayed() && cancelDDButton2.isDisplayed();
    }

    public boolean doNotCancelDD() {
        cancelPopupVerification();
        click(goBackButton);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(dDDetailsTitle));

        return dDDetailsTitle.isDisplayed();
    }

    public boolean successfulDDDelete() {
        cancelPopupVerification();
        click(cancelDDButton2);
        boolean one = cancelSuccessTitle.isDisplayed() && cancelSuccessReturnButton.isDisplayed();
        click(cancelSuccessReturnButton);
        boolean two = directDebitTitle.isEnabled();

        return one && two;
    }

    public boolean partialSuccessDelete() {
        navigateToDDScreenAsUser("ONETRANUSER", "TESTPASSWORD");
        click(partialSuccessDD);
        click(cancelDDButton);
        click(cancelDDButton2);
        boolean one = pSContactSupport.isDisplayed() && partialSuccessTitle.isDisplayed() && pSDoneButton.isDisplayed();
        click(pSContactSupport);
        boolean two = supportChatTitle.isDisplayed();
        click(closeSupportChat);
        click(cancelDDButton);
        click(cancelDDButton2);
        click(pSDoneButton);
        boolean three = directDebitTitle.isDisplayed();

        return one && two && three;
    }

    public boolean failureUnableToDelete() {

        navigateToDDScreenAsUser("ONETRANUSER", "TESTPASSWORD");
        click(failureDD);
        click(cancelDDButton);
        click(cancelDDButton2);
        boolean one = cancelFailureTitle.isDisplayed() && failContactSupport.isDisplayed() && failDoneButton.isDisplayed();
        click(failContactSupport);
        boolean two = supportChatTitle.isDisplayed();
        click(closeSupportChat);
        click(cancelDDButton);
        click(cancelDDButton2);
        click(failDoneButton);
        boolean three = directDebitTitle.isDisplayed();

        return one && two && three;
    }

    public boolean alreadyDeletedDD() {

        navigateToDDScreenAsUser("ONETRANUSER", "TESTPASSWORD");
        click(alreadyDD);
        click(cancelDDButton);
        click(cancelDDButton2);
        boolean one = alreadyTitle.isDisplayed() && alreadyCloseBtn.isDisplayed();
        click(alreadyCloseBtn);
        boolean two = directDebitTitle.isDisplayed();

        return one && two;
    }

    public boolean isNewDDVisible() {
        navigateToDDScreen();
        char str1 = firstNewDD.getText().charAt(0);
        char str2 = secondNewDD.getText().charAt(0);

        return str1 < str2;
    }

    public boolean newDDDetailsScreen() {
        navigateToDDScreen();
        click(firstNewDD);

        return lastPayAmount.getText().equals("-") && lastPayDate.getText().equals("-");
    }

    public boolean cancelNewDD() {
        navigateToDDScreen();
        click(firstNewDD);
        click(cancelDDButton);
        click(cancelDDButton2);
        click(cancelSuccessReturnButton);

        return directDebitTitle.isDisplayed();
    }

    public boolean directDebitAndSODisabled() {
        login.navigateToLogin();
        click(paymentsTab);

        return !isViewSPaymentsDisplayed();
    }

    public boolean dDEnabledSODisabled() {
        navigateToDDScreen();
        return !isSOTabDisplayed() && recentDirectDebits.isDisplayed();
    }

    public boolean sOEnabledDDDisabled() {
        navigateToDDScreen();
        return !isDirectDebitDisplayed() && driver.findElementByAccessibilityId("Standing order").isDisplayed();
    }


    public boolean viewPaymentsTabs() {
        navigateToDDScreen();
        click(viewSPayments);
        boolean one = directDebitTitle.isDisplayed();
        boolean two = sOTab.isDisplayed();
        boolean three = oOPTab.isDisplayed();
        return one && two && three;
    }
    public boolean checkPaymentsTabs() {
        navigateToDDScreen();
        click(viewSPayments);
        boolean one = directDebitTitle.isEnabled();
        click(sOTab);
        boolean two = sOTab.isEnabled() && standingOrders.isDisplayed();
        click(oOPTab);
        boolean three = oOPTab.isEnabled() && !isUpComingDisplayed();
        return one && two && three;
    }

}

