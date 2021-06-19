package pageObjects.Payments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.NoSuchElementException;
import pageObjects.Base.BasePage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class StandingOrder extends BasePage {

    DirectDebit directDebit = new DirectDebit(driver);

    @iOSFindBy(accessibility = "payments.view_scheduled_payments")
    private MobileElement viewSPayments;

    @iOSFindBy(accessibility = "direct_debits.tab_bar_title")
    private MobileElement dDTab;

    @iOSFindBy(accessibility = "standing_orders.tab_bar_title")
    private MobileElement sOTab;

    @iOSFindBy(accessibility = "Scheduled payments")
    private MobileElement scheduledPayTitle;

    @iOSFindBy(accessibility = "activity_indicator")
    private MobileElement spinner;

    @iOSFindBy(accessibility = "standing_orders.table.header")
    private MobileElement upcomingTitle;

    @iOSFindBy(accessibility = "standing_orders.no_results.detail")
    private MobileElement noResultsInformText;

    @iOSFindBy(accessibility = "standing_orders.no_results.title")
    private MobileElement noResultsTitle;

    @iOSFindBy(accessibility = "There may be some standing orders missing.")
    private MobileElement missingSOText;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]")
    private MobileElement payeeNameField;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"topRightLabel\"])[1]")
    private MobileElement payAmountField;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])[1]")
    private MobileElement payDateField;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"standing_orders.cell.standing_order\"])[1]")
    private MobileElement payTypeField;

    @iOSFindBy(accessibility = "A Standing Order is an instruction to make a regular, fixed payment from your account " +
            "to person or organisation. A Standing Order can be cancelled at any time")
    private MobileElement informativeText;

    @iOSFindBy(accessibility = "There may be some standing orders missing. Please try again later.")
    private MobileElement networkErrMessage;

    @iOSFindBy(accessibility = "alertWarning")
    private MobileElement alertImage;

    @iOSFindBy(accessibility = "There may be some standing orders missing.")
    private MobileElement failureMessage;

    @iOSFindBy(accessibility = "Support chat")
    private MobileElement supportChatTitle;

    @iOSFindBy(accessibility = "Scheduled payments")
    private MobileElement sODetailsBB;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[1]")
    private MobileElement detailsAmountfield;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[2]")
    private MobileElement detailsPrevPay;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[2]")
    private MobileElement detailsNextPayBefore;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[3]")
    private MobileElement detailsNextPay;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[4]")
    private MobileElement detailsFrequencyField;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[4]")
    private MobileElement detailsRefFieldBefore;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[3]")
    private MobileElement detailsFreqBefore;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"rightLabel\"])[5]")
    private MobileElement detailsRefField;

    @iOSFindBy(accessibility = "standing_order_details.header")
    private MobileElement detailsTitle;

    @iOSFindBy(accessibility = "standing_order_details.cancel")
    private MobileElement detailsCancelBtn;

    @iOSFindBy(accessibility = "Go back")
    private MobileElement goBackBtn;

    @iOSFindBy(accessibility = "standing_order_details.action_sheet.partial_cancel.title")
    private MobileElement cancelTitle;

    @iOSFindBy(accessibility = "standing_order_details.alert.failure.primaryCTA")
    private MobileElement failContactSupport;

    @iOSFindBy(accessibility = "standing_order_details.alert.failure.secondaryCTA")
    private MobileElement failDoneBtn;

    @iOSFindBy(accessibility = "standing_order_details.alert.failure.subtitle")
    private MobileElement failErrorMessage;

    @iOSFindBy(accessibility = "standing_order_details.alert.failure.title")
    private MobileElement failTitle;

    @iOSFindBy(accessibility = "Cancel this recurring payment")
    private MobileElement cancelSOBtn;

    @iOSFindBy(accessibility = "standing_order_details.action_sheet.cancel.message")
    private MobileElement cancelDiscaimerMsg;

    @iOSFindBy(accessibility = "standing_order_details.action_sheet.cancel.title")
    private MobileElement pleaseConirmCancel;

    @iOSFindBy(accessibility = "standing_order_details.alert.already_cancelled.title")
    private MobileElement alreadyCanTitle;

    @iOSFindBy(accessibility = "standing_order_details.alert.already_cancelled.subtitle")
    private MobileElement alrteadyCanMsg;

    @iOSFindBy(accessibility = "standing_order_details.alert.already_cancelled.primaryCTA")
    private MobileElement closeBtn;

    @iOSFindBy(accessibility = "standing_order_details.alert.partial_success.secondaryCTA")
    private MobileElement partSuccessDoneBtn;

    @iOSFindBy(accessibility = "standing_order_details.alert.partial_success.primaryCTA")
    private MobileElement partSuccessCSB;

    @iOSFindBy(accessibility = "standing_order_details.alert.partial_success.subtitle")
    private MobileElement partSuccessMsg;

    @iOSFindBy(accessibility = "standing_order_details.action_sheet.partial_cancel.title")
    private MobileElement partSuccessPopUpTitle;

    @iOSFindBy(accessibility = "standing_order_details.action_sheet.partial_cancel.message")
    private MobileElement partSuccessPopUpMsg;

    @iOSFindBy(accessibility = "standing_order_details.alert.partial_success.title")
    private MobileElement partSuccessTitle;

    @iOSFindBy(accessibility = "standing_order_details.alert.success.title")
    private MobileElement successDeleteMsg;

    @iOSFindBy(accessibility = "standing_order_details.alert.success.primaryCTA")
    private MobileElement successDelDoneBtn;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/" +
            "XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/" +
            "XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/" +
            "XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton")
    private MobileElement contactSupportBtn;

    @iOSFindBy(accessibility = "Proceed anyway")
    private MobileElement proceedBtn;

    @iOSFindBy(accessibility = "Close")
    private MobileElement closeSupportChat;


    String warningLocation = "(16, 124)";
    int currentDate = getCurrentDate();
    String currentMonth = getCurrentMonth();
    int currentYear = getCurrentYear();

    public StandingOrder(AppiumDriver driver) {
        super(driver);
    }

    // is displayed methods

    public boolean isViewSPayDisplayed(){
        try {
            return viewSPayments.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    // CustomMethods

    public int getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return  cal.get(Calendar.DAY_OF_MONTH);
    }
    public String getCurrentMonth(){
        String currentMonth =Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG,Locale.getDefault());

        return currentMonth;
    }
    public int getCurrentYear(){
        int currentMonth =Calendar.getInstance().get(Calendar.YEAR);

        return currentMonth;
    }
    public void navigateToStandingOrder(){
        directDebit.navigateToDDScreen();
        clickSOTab();
    }
    public void navigateToStandingOrderDetails(){
        directDebit.navigateToDDScreen();
        clickSOTab();
        click(payeeNameField);
    }
    public void navigateToSOAsUser(String user,String pass){
        directDebit.navigateToDDScreenAsUser(user,pass);
        clickSOTab();
    }
    public boolean isSpinnerDisplayed(){
        try {
            return spinner.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public int getDatePlus2(String month, int currentDay,int year){
        String[] evenMonths={"September","April","June","November"};
        String[] oddMonths = {"January","March","May","July","August","October","November","December"};
        int result;

        if (Arrays.asList(evenMonths).contains(month)){
            switch (currentDay){
                case 29:
                    result = 01;
                    break;
                case 30:
                    result = 02;
                    break;
                 default:
                     result = currentDay +2;
            }

        }else if (Arrays.asList(oddMonths).contains(month)){
            switch (currentDay){
                case 30:
                    result = 01;
                    break;
                case 31:
                    result = 02;
                    break;
                default:
                    result = currentDay +2;
            }
        }else if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){

            switch (currentDay){
                case 28:
                    result = 01;
                    break;
                case 29:
                    result = 02;
                    break;
                default:
                    result = currentDay +2;
            }
        }else {
            switch (currentDay){
                case 27:
                    result = 01;
                    break;
                case 28:
                    result = 02;
                    break;
                default:
                    result = currentDay +2;
            }
        }
        return result;
    }

    //Click Methods

    public void clickSOTab(){click(sOTab);}

    public void clickCancelonDetails(){click(detailsCancelBtn);}

    // Display methods

    public boolean isUpTitleDisplayed(){
        try {
           return upcomingTitle.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public boolean isNoSOMessageDisplayed(){
        try {
            return noResultsTitle.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    // Test Methods

    public boolean manageScheduleIsDisplayed(){
        directDebit.navigateToDDScreen();
        boolean one = directDebit.isRecentDisplayed();
        boolean two = dDTab.isDisplayed() && sOTab.isDisplayed();
        boolean three = sOTab.getLocation().toString().equals("(197, 74)");

        return one && two && three;

    }
    public boolean switchTabVerification(){
        directDebit.navigateToDDScreen();
        boolean one = directDebit.isRecentDisplayed();
        clickSOTab();
        boolean two = !directDebit.isRecentDisplayed();
        click(dDTab);
        boolean three = directDebit.isRecentDisplayed();

        return one && two && three;
    }
    public boolean scheduleHeaderIsDisplayed(){
        navigateToStandingOrder();
        return scheduledPayTitle.isDisplayed();
    }
    public boolean refreshCheck(){
        directDebit.navigateToDDScreen();
        boolean one = isSpinnerDisplayed();
        directDebit.isRecentDisplayed();
        clickSOTab();
        boolean two = isSpinnerDisplayed();
        upcomingTitle.isDisplayed();
        click(dDTab);
        boolean three = !isSpinnerDisplayed();
        directDebit.clickFRecentDD();
        directDebit.clickDetailsBB();
        boolean four = !isSpinnerDisplayed();
        directDebit.clickDirectDebitsBB();
        directDebit.clickViewSPayements();
        boolean five = isSpinnerDisplayed();

        return one && two && three && four && five;
    }
    public boolean goBackFromSO(){
        navigateToStandingOrder();
        directDebit.clickDirectDebitsBB();
        return directDebit.isPaymentPageDisplayed();
    }
    public boolean displaySO(){
        navigateToStandingOrder();
        boolean one = upcomingTitle.isDisplayed();
        boolean two = payAmountField.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean three = payeeNameField.getText().matches(".*");
        boolean four = payDateField.getText().matches("^Next payment on\\s+[a-zA-Z]{3}\\s+\\d{2}$");
        boolean five = payTypeField.getText().equals("Standing Order");

        return one && two && three && four && five;
    }
    public boolean isInfoTextShown(){
        navigateToStandingOrder();
        boolean one =informativeText.isDisplayed();
        return one;
    }

    public boolean emptyStateSO(){
        navigateToSOAsUser("NOTRANSACTIONUSER","NOTRANSACTIONPASSWORD");
        boolean one = !isUpTitleDisplayed();
        boolean two = noResultsTitle.isDisplayed();

        return one && two;
    }

    public boolean isRetryErrMsgDisplayedSO(){
        navigateToSOAsUser("ONETRANUSER","TESTPASSWORD");
        boolean one = networkErrMessage.isEnabled() && alertImage.isEnabled() && isUpTitleDisplayed();
        boolean two = alertImage.getLocation().toString().equals(warningLocation);

        return one && two;
    }
    public boolean isNetworkErrMsgDisplayedSO(){
        navigateToSOAsUser("TWOTRANUSER","TESTPASSWORD");
        boolean one = networkErrMessage.isEnabled() && alertImage.isEnabled() && isNoSOMessageDisplayed();
        boolean two = alertImage.getLocation().toString().equals(warningLocation);

        return one && two;
    }
    public boolean isFailureErrMsgDisplayedSO(){
        navigateToSOAsUser("THREETRANUSER","TESTPASSWORD");
        boolean one = failureMessage.isEnabled() && isNoSOMessageDisplayed();
        click(contactSupportBtn);
        boolean two = supportChatTitle.isDisplayed();


        return one && two;
    }
    public boolean nameDiplayedInHeader(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        click(payeeNameField);
        return driver.findElementByAccessibilityId(name).isDisplayed();
    }
    public boolean goBackFromDetails(){
        navigateToStandingOrderDetails();
        click(sODetailsBB);

        return isUpTitleDisplayed();
    }
    public boolean isCancelSODisplayed(){
        navigateToStandingOrderDetails();
        return detailsCancelBtn.isDisplayed();
    }
    public boolean detailsPageAfter1stPay(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        if (name.equals("Mycroft Holmes")){
            driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[2]").click();
        }
        else {
            click(payeeNameField);
        }
        boolean one = detailsTitle.isDisplayed();
        boolean two = detailsAmountfield.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean three = detailsPrevPay.getText().matches("^\\d{2}\\s[a-zA-z]{3,9},\\s\\d{4}$");
        boolean four = detailsNextPay.getText().matches("^\\d{2}\\s[a-zA-z]{3,9},\\s\\d{4}$");
        boolean five = detailsFrequencyField.isDisplayed() && detailsRefField.isDisplayed();

        return one && two && three && four && five;
    }
    public boolean detailsPageBefore1stPay(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]";
        while (!name.equals("Mycroft Holmes")){
            name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
            path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        boolean one = detailsTitle.isDisplayed();
        boolean two = detailsAmountfield.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean four = detailsNextPayBefore.getText().matches("^\\d{2}\\s[a-zA-z]{3,9},\\s\\d{4}$");
        boolean five = detailsFreqBefore.isDisplayed() && detailsRefFieldBefore.isDisplayed();

        return one && two && four && five;
    }
    public boolean cancelPopUpVerification(){
        navigateToStandingOrder();
        int date = Integer.parseInt(payDateField.getText().replaceAll("[^0-9]", ""));
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])[1]";
        int dayAfterTomorrow = getDatePlus2(currentMonth,currentDate,currentYear);
        while (date <= dayAfterTomorrow || date == currentDate){
            date = Integer.parseInt(driver.findElementByXPath
                    ("(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])["+Integer.toString(i)+"]").
                    getText().replaceAll("[^0-9]",""));
            path = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
       return cancelSOBtn.isDisplayed() && cancelDiscaimerMsg.isDisplayed() && goBackBtn.isDisplayed();
    }

    public boolean doNotDeleteSO(){
        navigateToStandingOrder();
        int date = Integer.parseInt(payDateField.getText().replaceAll("[^0-9]", ""));
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])[1]";
        while (date <= currentDate+2){
            date = Integer.parseInt(driver.findElementByXPath
                    ("(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])["+Integer.toString(i)+"]").
                    getText().replaceAll("[^0-9]",""));
            path = "(//XCUIElementTypeStaticText[@name=\"bottomRightLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
        click(goBackBtn);
        goBackBtn.isDisplayed();
        return detailsTitle.isDisplayed();
    }
    public boolean partialSOPopUp(){
        navigateToStandingOrder();
        click(payeeNameField);
        clickCancelonDetails();
        click(cancelSOBtn);

        return partSuccessPopUpMsg.isDisplayed() && partSuccessPopUpTitle.isDisplayed()
                && goBackBtn.isDisplayed() && proceedBtn.isDisplayed();
    }
    public boolean partialSOPopUpGoBack(){
        navigateToStandingOrder();
        click(payeeNameField);
        clickCancelonDetails();
        click(goBackBtn);
        detailsTitle.getText();
        detailsNextPayBefore.getText();

        return detailsTitle.isDisplayed();
    }
    public boolean partialDeleteSO(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]";
        while (!name.equals("Sherlock Holmes")){
            name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
            path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
        click(proceedBtn);
        boolean one = partSuccessTitle.isDisplayed() && partSuccessMsg.isDisplayed() && partSuccessCSB.isDisplayed() && partSuccessDoneBtn.isDisplayed();
        click(partSuccessCSB);
        boolean two = supportChatTitle.isDisplayed();
        click(closeSupportChat);
        clickCancelonDetails();
        click(proceedBtn);
        click(partSuccessDoneBtn);
        boolean three = isUpTitleDisplayed();

        return one && two && three;
    }
    public boolean failureDeleteSO(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]";
        while (!name.equals("Tywin Lannister")){
            name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
            path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
        click(cancelSOBtn);
        boolean one = failTitle.isDisplayed() && failErrorMessage.isDisplayed() && failContactSupport.isDisplayed() && failDoneBtn.isDisplayed();
        click(failContactSupport);
        boolean two = supportChatTitle.isDisplayed();
        click(closeSupportChat);
        clickCancelonDetails();
        click(cancelSOBtn);
        click(failDoneBtn);
        boolean three = isUpTitleDisplayed();

        return one && two && three;
    }
    public boolean alreadyDeleteSO(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]";
        while (!name.equals("John Watson")){
            name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
            path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
        click(cancelSOBtn);
        boolean one = alreadyCanTitle.isDisplayed() && alrteadyCanMsg.isDisplayed() && closeBtn.isDisplayed();
        click(closeBtn);
        boolean two = isUpTitleDisplayed();

        return one && two;
    }
    public boolean successfulDeleteSO(){
        navigateToStandingOrder();
        String name = payeeNameField.getText();
        int i = 2;
        String path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])[1]";
        while (!name.equals("Mycroft Holmes")){
            name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
            path = "(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]";
            i++;
        }
        driver.findElementByXPath(path).click();
        clickCancelonDetails();
        click(proceedBtn);
        boolean one = successDeleteMsg.isDisplayed() && successDelDoneBtn.isDisplayed();
        click(successDelDoneBtn);
        boolean two = isUpTitleDisplayed();
        boolean three;
        name = payeeNameField.getText();
        try {
            while (!name.equals("Mycroft Holmes")){
                name = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"topLeftLabel\"])["+Integer.toString(i)+"]").getText();
                i++;
            }
            three = false;
        }catch (Exception e){
            three = true;
        }

        return one && two && three;
    }
}
