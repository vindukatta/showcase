package pageObjects.Transactions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import pageObjects.Login.Login;
import pageObjects.Base.BasePage;

import java.util.ArrayList;

public class TransactionsObjects extends BasePage {

    private Login login = new Login(driver);

    //ScreenshotUtils screenshot = new ScreenshotUtils(driver);

    @iOSFindBy(accessibility = "transactions.transactions_header_button_title")
    private MobileElement transactionsTitle;

    @iOSFindBy(accessibility = "ledgerBalanceValueLabel")
    private MobileElement totalBalance;

    @iOSFindBy(accessibility = "availableBalanceValueLabel")
    private MobileElement availableBalance;

    @iOSFindBy(accessibility = "sortCodeAndAccountLabel")
    private MobileElement accountNumber;

    @FindBy(name = "titleLabel") //FIXME: get proper accessibility ID
    private MobileElement individualTransactionTitle;

    @iOSFindBy(accessibility = "postingDateCell")
    private MobileElement postingDate;

    @iOSFindBy(accessibility = "transactionStatusCell")
    private MobileElement transactionStatus;

    @iOSFindBy(accessibility = "transactionTypeCell")
    private MobileElement transactionType;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"valueLabel\"])[1]") //FIXME: get proper accessibility ID
    private MobileElement postingDateValue;

    @iOSFindBy(accessibility = "cross white")
    private MobileElement transactionCancel;

    public TransactionsObjects(AppiumDriver driver) {
        super(driver);
    }

    public boolean viewTransactionDetails(){
        login.passThroughLogin();
        boolean one = transactionsTitle.isDisplayed();
        clickGenericIostableCell();
        boolean two = individualTransactionTitle.isDisplayed();

        String money = readText(individualTransactionTitle);
        boolean three;
        if (money.charAt(0) == '+'){
            three = true;
        } else if (money.charAt(0) == '-'){
            three = true;
        } else {
            three = false;
        }

        boolean four = false;
        if (money.charAt(money.length()-3) == '.'){
            four = true;
        }

        boolean five = postingDate.isDisplayed();
        boolean six = transactionStatus.isDisplayed();
        boolean seven = transactionType.isDisplayed();

        return one && two && three && four && five && six && seven;
    }

    public boolean sortCodeAndAccountNumber(){
        login.passThroughLogin();
        boolean one = accountNumber.isDisplayed();

        String number = readText(accountNumber);
        String sort = number.substring(number.lastIndexOf(' ') + 1);
        boolean two = sort.length() == 8;

        boolean three;
        if(sort.charAt(2) == '-' && sort.charAt(5) == '-'){
            three = true;
        } else {
            three = false;
        }
        return one && two && three;
    }

    public boolean availableBalance(){
        return availableBalance.isDisplayed();
    }

    public boolean accountBalance(){
        return totalBalance.isDisplayed();
    }

    public boolean viewOlderTransactionsByMonth(){

        ArrayList<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        login.passThroughLogin();
        clickGenericIostableCell();
        String transMonth = readText(postingDateValue);
        String value = transMonth.substring(transMonth.lastIndexOf(" ")+1);

        String month = null;
        switch (value) {
            case "Jan" : month = "January";
                break;
            case "Feb": month = "February";
                break;
            case "Mar": month = "March";
                break;
            case "Apr": month = "April";
                break;
            case "May": month = "May";
                break;
            case "Jun": month = "June";
                break;
            case "Jul": month = "July";
                break;
            case "Aug": month = "August";
                break;
            case "Sep": month = "September";
                break;
            case "Oct": month = "October";
                break;
            case "Nov": month = "November";
                break;
            case "Dec": month = "December";
                break;
        }

        int firstMonth = months.indexOf(month);
        if(firstMonth != 2){
            month = months.get(months.indexOf(month)-3);
        } else {
            int index = 12 + firstMonth;
            month = months.get(months.indexOf(index)-3);
        }
        click(transactionCancel);

        boolean one;
        try{
            MobileElement element = (MobileElement) driver.findElement(By.name(month));
            scrollToElement(element);
            one = element.isDisplayed();
        } catch (NoSuchElementException e){
            one = false;
        }

        if(!month.equals("January")){
            month = months.get(months.indexOf(month)-1);
        } else {
            month = "December";
        }

        boolean two;
        try {
            MobileElement e = (MobileElement) driver.findElement(By.name(month));
            scrollToElement(e);
            two = !e.isDisplayed();
        } catch (NoSuchElementException e){
            two = true;
        }
        return one && two;
    }

    public void transactionsScreenshot(){
        login.passThroughLogin();
        //screenshot.takeScreenshot();
    }
}
