package Payments_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Payments.AvailableBalance;
import testUtils.TestInitialise;
import pageObjects.Payments.StandingOrder;

public class standingOrder_tests extends TestInitialise {

    private StandingOrder standingOrder;
    private AvailableBalance availableBalance;

    @BeforeMethod
    public void beforeMethod() {

        standingOrder= new StandingOrder(driver);
        availableBalance = new AvailableBalance(driver);
    }

    @Test(priority = 1, description = "Toggle display")
    public void CUICE7312() { Assert.assertTrue(standingOrder.manageScheduleIsDisplayed()); }

    @Test(priority = 2, description = "Switch between payment types")
    public void CUICE7314() { Assert.assertTrue(standingOrder.switchTabVerification()); }

    @Test(priority = 3, description = "Scheduled payments page header text")
    public void CUICE7315() { Assert.assertTrue(standingOrder.scheduleHeaderIsDisplayed()); }

    @Test(priority = 4, description = "Do not refresh DD and SO lists after they have been displayed")
    public void CUICE7535() { Assert.assertTrue(standingOrder.refreshCheck()); }

    @Test(priority = 5, description = "go back from standing order tab")
    public void CUICE7401() { Assert.assertTrue(standingOrder.goBackFromSO()); }

    @Test(priority = 6, description = "Standing order list display")
    public void CUICE7399() { Assert.assertTrue(standingOrder.displaySO()); }

    @Test(priority = 7, description = "Informative text is shown")
    public void CUICE7402() { Assert.assertTrue(standingOrder.isInfoTextShown()); }// failing to find element

    @Test(priority = 8, description = "Failure - display warning in snack bar")
    public void CUICE7038() { Assert.assertTrue(standingOrder.isFailureErrMsgDisplayedSO()); }

    @Test(priority = 9, description = "Network error ")
    public void CUICE7040() { Assert.assertTrue(standingOrder.isNetworkErrMsgDisplayedSO()); }

    @Test(priority = 10, description = "Retry - display warning in snack bar")
    public void CUICE7039() { Assert.assertTrue(standingOrder.isRetryErrMsgDisplayedSO()); }

    @Test(priority = 11, description = "No result - display empty state")
    public void CUICE7037() { Assert.assertTrue(standingOrder.emptyStateSO()); }

    @Test(priority = 13, description = "cancel")
    public void CUICE7662() { Assert.assertTrue(availableBalance.cancelStartDatePicker()); }

    @Test(priority = 14, description = "done")
    public void CUICE7663() { Assert.assertTrue(availableBalance.clickDoneOnDatePicker()); }

    @Test(priority = 15, description = "Navigate to so detail page")
    public void CUICE8099() { Assert.assertTrue(standingOrder.nameDiplayedInHeader()); }

    @Test(priority = 16, description = "SO details page view (before 1st payment)")
    public void CUICE8100() { Assert.assertTrue(standingOrder.detailsPageBefore1stPay()); }

    @Test(priority = 17, description = "SO view details page view (after 1st payment)")
    public void CUICE8101() { Assert.assertTrue(standingOrder.detailsPageAfter1stPay()); }

    @Test(priority = 18, description = "SO details back button")
    public void CUICE8102() { Assert.assertTrue(standingOrder.goBackFromDetails()); }

    @Test(priority = 19, description = "Cancel standing order is displayed")
    public void CUICE8103() { Assert.assertTrue(standingOrder.isCancelSODisplayed()); }

    @Test(priority = 20, description = "Cancel standing order Pop up")
    public void CUICE8128() { Assert.assertTrue(standingOrder.cancelPopUpVerification()); }

    @Test(priority = 21, description = "Do not delete standing order")
    public void CUICE8130() { Assert.assertTrue(standingOrder.doNotDeleteSO()); }

    @Test(priority = 22, description = "Partial success pop-up")
    public void CUICE8132() { Assert.assertTrue(standingOrder.partialSOPopUp()); }

    @Test(priority = 23, description = "Partial success standing order delete")
    public void CUICE8134() { Assert.assertTrue(standingOrder.partialDeleteSO()); }

    @Test(priority = 24, description = "Partial success pop up - do not delete SO")
    public void CUICE8140() { Assert.assertTrue(standingOrder.partialSOPopUpGoBack()); }

    @Test(priority = 25, description = "Failure - standing order deletion")
    public void CUICE8142() { Assert.assertTrue(standingOrder.failureDeleteSO()); }

    @Test(priority = 26, description = "Already canceled - standing order deletion")
    public void CUICE8143() { Assert.assertTrue(standingOrder.alreadyDeleteSO()); }

    @Test(priority = 27, description = "Update picker wheel")
    public void CUICE7732() { Assert.assertTrue(availableBalance.updateDatePicker()); }

    @Test(priority = 28, description = "Close end date picker")
    public void CUICE7735() { Assert.assertTrue(availableBalance.cancelEndDatePicker()); }

    @Test(priority = 29, description = "Click Done on end date picker wheel")
    public void CUICE7736() { Assert.assertTrue(availableBalance.clickDoneOnDatePickerEnd()); }

    @Test(priority = 27, description = "Select no end date on picker wheel")
    public void CUICE7738() { Assert.assertTrue(availableBalance.clickNoEndDate()); }

    @Test(priority = 28, description = "Standing order - do not show error message when available balance exceeded")
    public void CUICE8152() { Assert.assertTrue(availableBalance.noInsufficientFundsErrorSO()); }

    @Test(priority = 29, description = "One off - show error message when available balance exceeded")
    public void CUICE8153() { Assert.assertTrue(availableBalance.insufficientFundsErrorOneOff()); }

    @Test(priority = 30, description = " Toggle One off to recurring - error state is not displayed")
    public void CUICE8151() { Assert.assertTrue(availableBalance.toggleOneOffToRecurringValidation()); }

    @Test(priority = 31, description = " Toggle Recurring to one off  - error state is displayed")
    public void CUICE8150() { Assert.assertTrue(availableBalance.toggleRecurringToOneOffValidation()); }

    @Test(priority = 32, description = " Payment type toggle - one off")
    public void CUICE8308() { Assert.assertTrue(availableBalance.oneOffTabVerification()); }

    @Test(priority = 33, description = " Payment type toggle - recurring")
    public void CUICE8309() { Assert.assertTrue(availableBalance.recurringTabVerification()); }

    @Test(priority = 34, description = "Retain payment amount and reference field values as a user toggles between payment type")
    public void CUICE8310() { Assert.assertTrue(availableBalance.refAndAmountToggle()); }

    @Test(priority = 35, description = "Retail frequency, start date and end date field when toggling between payment types ")
    public void CUICE8311() { Assert.assertTrue(availableBalance.toggleBackToRecurring()); }

    @Test(priority = 36, description = "Retain toggle selection when user navigates between pages")
    public void CUICE8312() { Assert.assertTrue(availableBalance.retainToggleOnReccurTab()); }

    @Test(priority = 37, description = "Additional fields on payment summary page")
    public void CUICE8369() { Assert.assertTrue(availableBalance.sOSummaryPageVerify()); }

    @Test(priority = 38, description = "Payment summary page where SO has no end date")
    public void CUICE8370() { Assert.assertTrue(availableBalance.sOSummaryPageVerifyNoEnDate()); }

    @Test(priority = 39, description = "Do not receive any brc")
    public void CUICE8639() { Assert.assertTrue(availableBalance.noBrcSOCheck()); }

    @Test(priority = 39, description = "Receive a brc we do not recognise")
    public void CUICE8796() { Assert.assertTrue(availableBalance.unknownBrcSOCheck()); }

    @Test(priority = 39, description = "Payment frequency pop up list")
    public void CUICE8848() { Assert.assertTrue(availableBalance.frequencyPopUpVerify()); }

    @Test(priority = 39, description = "cancelling the pop up")
    public void CUICE8650() { Assert.assertTrue(availableBalance.frequencyPopUpCancelVerify()); }

    @Test(priority = 39, description = "Start date defaults to 3 days in the future")
    public void CUICE8851() { Assert.assertTrue(availableBalance.startDateDefaultThreeDays()); }

    @Test(priority = 39, description = "Frequency default")
    public void CUICE8638() { Assert.assertTrue(availableBalance.frequencyAndStartDateDefault()); }

    @Test(priority = 39, description = "Frequency default")
    public void CUI8638() { Assert.assertTrue(availableBalance.lastDayOfMonthValidation()); }

    @Test(priority = 39, description = "Proceed with recurring payment CTA active state")
    public void CUICE8647() { Assert.assertTrue(availableBalance.ctaActiveState()); }

}
