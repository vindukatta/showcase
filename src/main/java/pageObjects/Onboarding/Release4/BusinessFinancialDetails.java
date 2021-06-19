package pageObjects.Onboarding.Release4;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;
import pageObjects.Onboarding.CheckBoxElements;

public class BusinessFinancialDetails extends BasePage {

    CheckBoxElements checkBoxElements = new CheckBoxElements(driver);

    @iOSFindBy(accessibility = "initial_source_of_wealth_amount_text_field")
    private MobileElement edtISOWAmount;

    @iOSFindBy(accessibility = "next_button_default_title")
    private MobileElement btnNext;

    @iOSFindBy(accessibility = "turnover_amount_text_field")
    private MobileElement edtAnnualTurnOverAmount;

    @iOSFindBy(accessibility = "deposited_amount_text_field")
    private MobileElement edtSourceOfFundsAmount;

    @iOSFindBy(accessibility = "transaction_amount_text_field")
    private MobileElement edtTotalDepositAmount;


    public BusinessFinancialDetails(AppiumDriver driver) {
        super(driver);
    }

    public void populateBusinessFinancialDetails(String iSOWAmount, String iSOWFundingOption,
                                                 String oSOWAmount, String oSOWFundingOption,
                                                 String sofAmount, String sofFundingOption,
                                                 String transactAccountUsageOption, String transactionTypeOption, String totalDepositAmount){
        populateInitialSourceOfWealth(iSOWAmount, iSOWFundingOption);
        populateOngoingSourceOfWealth(oSOWAmount, oSOWFundingOption);
        populateSourceOfFunds(sofAmount, sofFundingOption);
        populateTransactionDetails(transactAccountUsageOption, transactionTypeOption, totalDepositAmount);

    }

    public void populateInitialSourceOfWealth(String iSOWAmount, String iSOWFundingOption){
        writeText(edtISOWAmount,iSOWAmount);
        click(btnNext);
        clickFundingOption(iSOWFundingOption);
        click(btnNext);
    }

    public void populateOngoingSourceOfWealth(String oSOWAmount, String oSOWFundingOption){
        writeText(edtAnnualTurnOverAmount,oSOWAmount);
        click(btnNext);
        clickFundingOption(oSOWFundingOption);
        click(btnNext);
    }

    public void populateSourceOfFunds(String sofAmount, String sofFundingOption){
        writeText(edtSourceOfFundsAmount,sofAmount);
        click(btnNext);
        clickFundingOption(sofFundingOption);
        click(btnNext);
    }

    public void populateTransactionDetails(String transactAccountUsageOption, String transactionTypeOption, String totalDepositAmount){
        clickFundingOption(transactAccountUsageOption);
        click(btnNext);
        clickFundingOption(transactionTypeOption);
        click(btnNext);
        writeText(edtTotalDepositAmount,totalDepositAmount);
        click(btnNext);
    }

    public void clickFundingOption(String funding){
        MobileElement element = (MobileElement) driver.findElement(By.name(funding));
        click(element);
    }
}
