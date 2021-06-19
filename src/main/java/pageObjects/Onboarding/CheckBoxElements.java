package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.Base.BasePage;
import java.util.Random;

public class CheckBoxElements extends BasePage {

    @FindBy(name = "Cash flow generated from the Company Operations")
    private MobileElement sowOopeartions;

    @FindBy(name = "Injection of Funds from Associated Entity")
    private MobileElement sowEntity;

    @FindBy(name = "Injection of Funds from Owner / Parent Company")
    private MobileElement sowParentComapny;

    @FindBy(name = "Injection of Funds from External Source")
    private MobileElement sowExternalSource;

    @FindBy(name = "Loan / Bank Funding")
    private MobileElement sowFunding;

    @FindBy(name = "Owner's Personal Savings")
    private MobileElement isowSavings;

    @FindBy(name = "Sale of Business / Asset")
    private MobileElement isowAsset;

    @FindBy(name = "Funds Generated from Asset Sale")
    private MobileElement sofAssetSale;

    @FindBy(name = "Funds Generated from Business Operations")
    private MobileElement sofBusinessOps;

    @FindBy(name = "Repatriation of Funds from Offshore Operations")
    private MobileElement sofOffshoreOps;

    @FindBy(name = "Transfer from Owner, Parent or Associated Entity")
    private MobileElement sofEntity;

    @FindBy(name = "Transfer from Another Financial Institution")
    private MobileElement sofInstitution;

    @FindBy(name = "Salary account")
    private MobileElement auSalary;

    @FindBy(name = "Operational Account - domestic")
    private MobileElement auDomestic;

    @FindBy(name = "Petty Cash")
    private MobileElement auCash;

    @FindBy(name = "Collection accounts")
    private MobileElement auCollection;

    @FindBy(name = "Personal use")
    private MobileElement auPersonal;

    @FindBy(name = "Rental collection")
    private MobileElement auRental;

    @FindBy(name = "Fund pooling")
    private MobileElement auFund;

    @FindBy(name = "Facility support - Escrow, loan")
    private MobileElement auLoan;

    @FindBy(name = "Investment purpose - domestic")
    private MobileElement auInvestment;

    @FindBy(xpath = "//XCUIElementTypeApplication[@name=\"Iceberg\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
    private MobileElement checkBoxTable;

    @FindBy(name = "Automated Clearing House")
    private MobileElement ttClearingHouse;

    @FindBy(name = "Cash")
    private MobileElement ttCash;

    @FindBy(name = "Cheque")
    private MobileElement ttCheque;

    @FindBy(name = "Domestic Wire Transfer")
    private MobileElement ttDomesticTransfer;


    MobileElement[] cbElements;

    MobileElement [] auCBElements = {auSalary,auDomestic,auCash,auCollection,auPersonal,auRental,auFund,auLoan,auInvestment};

    MobileElement [] sofCBElements = {sofAssetSale,sofBusinessOps,sofOffshoreOps,sofEntity,sofInstitution};

    MobileElement [] isowCBElements = {sowEntity,sowParentComapny,sowExternalSource,sowFunding,isowSavings,isowAsset};

    MobileElement [] sowCBElements = {sowOopeartions,sowEntity,sowParentComapny,sowExternalSource,sowFunding};

    MobileElement [] ttCBElements = {ttClearingHouse, ttCash, ttCheque, ttDomesticTransfer};

    public CheckBoxElements(AppiumDriver driver) {
        super(driver);
    }


    public void clickAllCheckboxElements(String sourceOfWealthType){

        if(sourceOfWealthType.contains("Ongoing")){
            cbElements = sowCBElements;
        }else if(sourceOfWealthType.contains("Initial")){
            cbElements = isowCBElements;
        }else if(sourceOfWealthType.contains("SourceOfFunds")){
            cbElements = sofCBElements;
        }else if(sourceOfWealthType.contains("AccountUsage")){
            cbElements = auCBElements;
        }else if(sourceOfWealthType.contains("TransactionType")){
            cbElements = ttCBElements;
        }

        for (MobileElement e : cbElements){
            click(e);
            scrollToElement(checkBoxTable);

        }
    }

    public void clickRandomElement(String sourceOfWealthType){
        if(sourceOfWealthType.contains("Ongoing")){
            cbElements = sowCBElements;
        }else if(sourceOfWealthType.contains("Initial")){
            cbElements = isowCBElements;
        }else if(sourceOfWealthType.contains("SourceOfFunds")){
            cbElements = sofCBElements;
        }else if(sourceOfWealthType.contains("AccountUsage")){
            cbElements = auCBElements;
        }else if(sourceOfWealthType.contains("TransactionType")){
            cbElements = ttCBElements;
        }

        Random rand = new Random();

        int size = cbElements.length;

        int value = rand.nextInt(size);

        click(cbElements[value]);
    }
}