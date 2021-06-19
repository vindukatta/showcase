package pageObjects.Onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Base.BasePage;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class BusinessSearch extends BasePage {

    private SetUp setup = new SetUp(driver);

    @iOSFindBy(accessibility = "Great, you're ready to get started!")
    private MobileElement coverPageTitle;

    @iOSFindBy (accessibility = "Next")
    private MobileElement nextButton;

    @iOSFindBy(accessibility = "Your Business")
    private MobileElement businessSearchTitle;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Business Details\"]")
    private MobileElement btnBackToBusinessSearch;

    @iOSFindBy(accessibility = "business_search.search_field_title.initial")
    private MobileElement businessSearchBoxTitle;

    @iOSFindBy(accessibility = "business_search.search_field_title")
    private MobileElement businessSearchBox;

    @iOSFindBy(accessibility = "search_textfield")
    private MobileElement searchTexfield;

    @iOSFindBy(accessibility = "search exit button")
    private MobileElement searchExitButton;

    @iOSFindBy(accessibility = "We are currently experiencing some technical issues. " +
            "Please log back into Iceberg to continue with your sign up later")//FIXME:get a better error message)
    private MobileElement noBusinessFoundError;

    @iOSFindBy(accessibility = "business_details.beneficial_owner")
    private MobileElement beneficialOwner;

    @iOSFindBy(accessibility = "business_details.name")
    private MobileElement businessName;

    @iOSFindBy(accessibility = "business_details.address")
    private MobileElement businessAddress;

    @iOSFindBy(accessibility =  "business_details.activity")
    private MobileElement txtBusinessSicCodes;

    @iOSFindBy(accessibility = "Business Address")
    private MobileElement txtBusinessAddress;

    public BusinessSearch(AppiumDriver driver) {
        super(driver);
    }

    //ELEMENTS DISPLAYED
    public boolean coverPageTitleDisplayed(){
        return coverPageTitle.isDisplayed();
    }

    public boolean businessSearchTitleDisplayed(){
        return businessSearchTitle.isDisplayed();
    }

    public boolean noBusinessFoundErrorDisplayed(){
        return noBusinessFoundError.isDisplayed();
    }


    public void writeBusinessTitle(String businessName){
        writeText(businessSearchBoxTitle, businessName);
    }

    public void writeBusinessSearch(String businessName){
        writeText(businessSearchBox, businessName);
    }

    //CLICK METHODS
    public void clickNextButton(){
        click(nextButton);
    }

    public void clickBusinessSearchBoxTitle(){
        click(businessSearchBoxTitle);
    }

    public void clickSearchExitButton(){
        click(searchExitButton);
    }

    public void navigateToBusinessSearch(){
        setup.passThroughSetUp();
        click(nextButton);
    }

    public boolean businessSearchKeyboard(){
        navigateToBusinessSearch();
        clickBusinessSearchBoxTitle();
        return doesKeyboardExist();
    }

    public boolean searchByBusinessName(){
        //CUICE-3979 LOOKUP MY BUSINESS - Search by Business Name
        navigateToBusinessSearch();

        writeBusinessTitle("GLAZE");
        if (IosTableCellCount() == 0){
            return false;
        }
        String countOne = String.valueOf(IosTableCellCount());
        writeBusinessTitle(" LIMITED");

        if (IosTableCellCount() == 0) {
            return false;
        }

        String countTwo = String.valueOf(IosTableCellCount());
        clearText(businessSearchBoxTitle);

        if (countOne != countTwo){
            return true;
        } else {
            return false;
        }
    }

    public boolean searchByRegistrationNumber(){
        navigateToBusinessSearch();
        writeBusinessTitle("054");
        if (IosTableCellCount() == 0){
            return false;
        }
        String countOne = String.valueOf(IosTableCellCount());
        writeBusinessSearch("4");
        new WebDriverWait(driver, 1).until(ExpectedConditions.invisibilityOfElementLocated(By.name("HOUSE CROWD PROJECT O54 LIMITED")));

        if (IosTableCellCount() == 0) {
            return false;
        }

        String countTwo = String.valueOf(IosTableCellCount());
        clearText(businessSearchBox);
        System.out.println(countOne + " " + countTwo);

        if (!countOne.equals(countTwo)){
            return true;
        } else {
            return false;
        }
    }

    public boolean noBussinessFoundSearchByBusinessName(){
        //CUICE-3981 LOOKUP MY BUSINESS - Search by business name - no business found
        navigateToBusinessSearch();
        writeBusinessTitle("noresults");
        return noBusinessFoundErrorDisplayed();
    }

    public boolean noBussinessFoundErrorSearchByBusinessNumber(){
        navigateToBusinessSearch();
        try {
            //CUICE-3982 LOOKUP MY BUSINESS - Search by company reg number - no business found
            writeBusinessTitle("01234567");
            return noBusinessFoundErrorDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean cancelSearch(){
        navigateToBusinessSearch();
        clickBusinessSearchBoxTitle();
        clickSearchExitButton();
        return businessSearchTitleDisplayed();
    }

    public boolean selectionOfCompanyByBusinessName(){
        //CUICE-3995 - LOOKUP MY BUSINESS - Selection of company
        navigateToBusinessSearch();
        writeBusinessTitle("GLAZE LIMITED");
        clickGenericIostableCell();

        //check results are return
        boolean one =  readText(beneficialOwner).equalsIgnoreCase("Mr Robert Elwell");
        btnBackToBusinessSearch.click();
        return one;
    }

    public boolean selectionOfCompanyByBusinessRegNumber(){
        //CUICE-3995 - LOOKUP MY BUSINESS - Selection of company
        navigateToBusinessSearch();
        writeBusinessTitle("05717355");
        clickGenericIostableCell();

        //check results are returned
        return readText(beneficialOwner).equalsIgnoreCase("Mr Robert Elwell");
    }

    public boolean isBusinessInformationDisplayed(){
        navigateToBusinessSearch();
        writeBusinessTitle("GLAZE LIMITED");
        clickGenericIostableCell();
        boolean owner = readText(beneficialOwner).equalsIgnoreCase("Mr Robert Elwell");
        boolean name = readText(businessName).equalsIgnoreCase("Glaze Limited");
        boolean address = readText(businessAddress).equalsIgnoreCase("89 King Street\nMaidstone\nME14 1BG\nUnited Kingdom");

        return owner && name && address;
    }

    public void passThroughBusinessSearch(){
        clickNextButton();
        writeBusinessTitle("05717355");
        clickGenericIostableCell();
    }
    public void passThroughBusinessUnderThreeYearsSearch(){
        clickNextButton();
        writeBusinessTitle("tyn ltd");
        clickGenericIostableCell();
    }

    public void populateBusinessSearch(String business){
        clickNextButton();
        writeBusinessTitle(business);
        clickGenericIostableCell();
    }

    public void verifyCompanySicCodesAndDescription(List<String> expectedSicCodes){
        List<String> actualSicCodes = new ArrayList<>();
        scrollToElement(txtBusinessSicCodes);
        String  sicCodesInfo = txtBusinessSicCodes.getAttribute("value");
        String[] tempVar = sicCodesInfo.split("\n");
        for(int i = 0; i < tempVar.length; i++){
            actualSicCodes.add(tempVar[i]);
        }
        Assert.assertTrue(actualSicCodes.containsAll(expectedSicCodes),"Sic codes verification");
        scrollUp(txtBusinessAddress);
    }
}
