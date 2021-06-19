package pageObjects.Payments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import pageObjects.Login.Login;
import pageObjects.Base.BasePage;

import java.util.Calendar;
import java.util.Locale;

public class AccountsTab extends BasePage {

    Login login = new Login(driver);

    @iOSFindBy(accessibility = "balanceViewBottomSubtitle")
    private MobileElement graphAvailableBalanceTitle;

    @iOSFindBy(accessibility = "tab_bar.overview_title")
    private MobileElement overviewTab;

    @iOSFindBy(accessibility = "tab_bar.transactions_title")
    private MobileElement accountsTab;

    @iOSFindBy(accessibility = "tab_bar.payments_title")
    private MobileElement paymentsTab;

    @iOSFindBy(accessibility = "tab_bar.support_title")
    private MobileElement supportTab;

    @iOSFindBy(accessibility = "tab_bar.more_title")
    private MobileElement moreTab;

    @iOSFindBy(accessibility = "transactions.no_transactions_title")
    private MobileElement noTrasactionTitle;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"transactions.no_transactions_sortcode_value\"])[1]")//FIXME
    private MobileElement noTransSortCode;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"transactions.no_transactions_sortcode_value\"])[2]") //FIXME
    private MobileElement noTransAccountNum;

    @iOSFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"titleLabel\"])[18]")
    private MobileElement firstTransaction;

    @iOSFindBy(accessibility = "balanceViewTitle")
    private MobileElement availableBalance;

    @iOSFindBy(accessibility = "balanceViewTopSubtitle")
    private MobileElement sortCodeAndAccountNum;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]" +
            "/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
    private MobileElement underline;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]" +
            "/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView")
    private MobileElement monthsBar;
    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/" +
            "XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/" +
            "XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/" +
            "XCUIElementTypeOther[3]/XCUIElementTypeScrollView/XCUIElementTypeOther[3]")
    private MobileElement glowingBall;

    String currentMonth = getCurrentMonth();

    public AccountsTab(AppiumDriver driver) {
        super(driver);
    }
    //Custom Methods

    public String getCurrentMonth(){
        String currentMonth =Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG,Locale.getDefault());

        return currentMonth;
    }

    //Test Methods

    public boolean emptyStateVerification(){
        login.loginAsUser("NOTRANSACTIONUSER","NOTRANSACTIONPASSWORD");
        boolean one = noTrasactionTitle.isDisplayed();
        boolean two = noTransSortCode.getText().matches("^\\d{2}-\\d{2}-\\d{2}");
        boolean three = noTransAccountNum.getText().matches("^\\d{8}");

        return one && two && three;
    }

    public boolean titleDisplayVerification(){
        login.navigateToLogin();
        boolean one = graphAvailableBalanceTitle.isDisplayed();
        boolean three = availableBalance.getText().matches("^(\\d{1,3},)?\\d{1,3}.\\d{2} GBP$");
        boolean two = sortCodeAndAccountNum.getText().matches("^\\d{2}-\\d{2}-\\d{2}\\s{3}\\D\\s{3}\\d{8}");

        return one && two && three;
    }
    public boolean moreThanOneMonthsBar(){
        login.navigateToLogin();
        boolean one = driver.findElementByName(currentMonth).getLocation().toString().equals("(157, 269)");
        boolean two = underline.isDisplayed();

        return one && two;
    }

    public boolean oneMonthBar() {
        login.loginAsUser("ONETRANUSER","TESTPASSWORD");
        boolean one;
        try {
            one = !monthsBar.isDisplayed();
        }catch (Exception e){
            one = true;
        }

        return one;
    }
}
