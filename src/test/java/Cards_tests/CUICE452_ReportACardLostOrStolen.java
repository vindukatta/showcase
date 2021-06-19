package Cards_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.Login.Login;
import pageObjects.More.CardLostOrStolen;
import pageObjects.More.CardLostOrStolenFurtherInfo;
import pageObjects.More.CardsManagement;
import pageObjects.More.ReviewTransactions;
import testUtils.TestInitialise;

public class CUICE452_ReportACardLostOrStolen extends TestInitialise {

    private Login login;

    private CardsManagement Cards;
    private CardLostOrStolen cardLostOrStolen;
    private CardLostOrStolenFurtherInfo info;
    private ReviewTransactions transactions;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        Cards = new CardsManagement(driver);
        cardLostOrStolen = new CardLostOrStolen(driver);
        info = new CardLostOrStolenFurtherInfo(driver);
        transactions = new ReviewTransactions(driver);

        login = new Login(driver);
    }

    @Test(priority = 1, description = "Card Activation menu Option")
    public void CUICE6455() {
        login.setLogin("FIRST TIME");
        Assert.assertTrue(Cards.cardManagementScreenHasReportLostStolen());
        Assert.assertTrue(cardLostOrStolen.navigateToReportLostOrStolenScreen());
    }

    @Test(priority = 2, description = "Report Lost or Stolen screen")
    public void CUICE6460() {
        login.setLogin("FIRST TIME");
        Assert.assertTrue(cardLostOrStolen.reportLostStolenScreen());
    }

    @Test(priority = 3, description = "Card Image Display")
    public void CUICE6461(){
        login.setLogin("FIRST TIME");
        Cards.navigateToCardLostOrStolen();
        Assert.assertTrue(cardLostOrStolen.isCardImageDisplayed());
        //take card image screenshot
    }

    @Test(priority = 4, description = "Debit Card Lost or Stolen - Continue Button")
    public void CUICE6462(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(cardLostOrStolen.continueButton());
    }

    @Test(priority = 5, description = "Report Lost Stolen Screen (Further Info)")
    public void CUICE6736(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.verifyFurtherInformationScreen());
    }

    @Test(priority = 6, description = "What Happened to your card. Field")
    public void CUICE6737(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.verifyWhatHappenedYourCardScreen());
    }

    @Test(priority = 7, description = "When did you last see your card")
    public void CUICE6738(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.verifyWhenYouLastSawCard());
    }

    @Test(priority = 8, description = "Has your pin been compromised? Field")
    public void CUICE6739(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.verifyHasPinBeenCompromisedSelection());
    }

    @Test(priority = 9, description = "Report and Cancel Button")
    public void CUICE6740(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.verifyReportAndCancelButton());
    }

    @Test(priority = 10, description = "Report Card Lost/Stolen and PIN Compromised")
    public void CUICE7151(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.reportCardLostStolenPinCompromised());
    }

    @Test(priority = 11, description = "Report Card Lost/Stolen and PIN Not Compromised")
    public void CUICE7152(){
        login.setLogin("FIRST TIME");
        Assert.assertTrue(info.reportCardLostStolenPinNotCompromised());
    }

    @Test(priority = 12, description = "Report Card Lost/Stolen and PIN Compromised")
    public void CUICE7149(){
        login.setLogin("NO TRANSACTION");
        Assert.assertTrue(info.reportLostStolenBackendValidationError());
    }
//    PlaceHolder for test to be completed
//    @Test
//    public void CUICE(){
//        login.setLogin("FIRST TIME");
//        System.out.println(transactions.returnElements());
//    }
}