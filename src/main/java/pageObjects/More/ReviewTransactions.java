package pageObjects.More;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageObjects.Base.BasePage;

public class ReviewTransactions extends BasePage {

    CardLostOrStolenFurtherInfo info = new CardLostOrStolenFurtherInfo(driver);

    public ReviewTransactions(AppiumDriver driver) {
        super(driver);
    }

    public int returnElements(){
        info.passToReviewTransactionsPage();
        return driver.findElements(By.name("sectionHeaderLabel")).size();
    }
}
