package pageObjects.Base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.Constants;
import utils.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected AppiumDriver driver;
    String elementName;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void synchElement(MobileElement element){
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(element));
            new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (TimeoutException e)
        {
            //TODO: Sysout(e.getMessage());
        }
    }

    //Package private Click Method
    public void click(MobileElement element) {
        try{
            synchElement(element);
            if (!element.isDisplayed()){
                scrollToElement(element);
                if(element.getAttribute("name")!=null){
                    elementName = element.getAttribute("name");
                }else {
                    elementName = element.toString();
                }
                element.click();
                ExtentTestManager.getTest().log(Status.INFO,"Clicked " + elementName);
            } else {
                if(element.getAttribute("name")!=null){
                    elementName = element.getAttribute("name");
                }else {
                    elementName = element.getTagName();
                }
                element.click();
                ExtentTestManager.getTest().log(Status.INFO,"Clicked " + elementName);
            }

        }
        catch (ElementNotVisibleException | ElementNotSelectableException | NoSuchElementException e) {
            ExtentTestManager.getTest().log(Status.ERROR, "Unable to click element because of: - " + e.getMessage());
        }
    }

    /**
     *
     * @param element
     * @param text
     */
    protected void writeText(MobileElement element, String text) {
        try {
            if (!element.isDisplayed()){
                scrollToElement(element);
                elementName = element.getAttribute("name");
                element.setValue(text);
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + text + " to " + elementName);
            } else {
                elementName = element.toString();
                element.setValue(text);
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + text + " to " + elementName);
            }
        }
        catch (ElementNotVisibleException | ElementNotSelectableException | NoSuchElementException e) {
            ExtentTestManager.getTest().log(Status.ERROR, "Unable to write text to element because of: - " + e.getMessage());
        }
    }

    protected void writeNumber(MobileElement element, int number){
        try {
            if (!element.isDisplayed()){
                scrollToElement(element);
                elementName = element.getAttribute("name");
                element.setValue(String.valueOf(number));
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + number + " to " + elementName);
            } else {
                elementName = element.getAttribute("name");
                element.setValue(String.valueOf(number));
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + number + " to " + elementName);
            }
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e){
            //TODO: Sysout(e.getMessage());
        }
    }

    protected void writeDecimal(MobileElement element, double number){
        try {
            if (!element.isDisplayed()){
                scrollToElement(element);
                elementName = element.getAttribute("name");
                element.setValue(String.valueOf(number));
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + number + " to " + elementName);
            } else {
                elementName = element.getAttribute("name");
                element.setValue(String.valueOf(number));
                ExtentTestManager.getTest().log(Status.INFO,"Wrote " + number + " to " + elementName);
            }
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e){
            //TODO: Sysout(e.getMessage());
        }
    }

    protected void clearText(MobileElement element) {
        try {
            if (!element.isDisplayed()){
                scrollToElement(element);
            }
            element.clear();
            ExtentTestManager.getTest().log(Status.INFO,"Deleted text in " + element.getAttribute("name"));
        }
        catch (ElementNotVisibleException | ElementNotSelectableException | NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
    }

    //Package private Read Text
    protected String readText(MobileElement element) {
        try {
            synchElement(element);
            if (!element.isDisplayed()){
                scrollToElement(element);
            }
            ExtentTestManager.getTest().log(Status.INFO,"Read text from " + element.getAttribute("name"));
            return element.getText();
        }
        catch (ElementNotVisibleException | ElementNotSelectableException | NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
        return null;
    }

    //Package private Read Attribute
    protected String readAttribute (MobileElement element, String attribute) {
        try {
            if (!element.isDisplayed()){
                scrollToElement(element);
            }
            ExtentTestManager.getTest().log(Status.INFO,"Read Attribute in " + element.getAttribute("name"));
            return element.getAttribute(attribute);
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
        return null;
    }

    // Package private DropDown Select
    protected void dropdownSelect (MobileElement element, String selection) {
        try {
            new Select(element).selectByVisibleText(selection);
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
    }

    // Package private Hover
    protected void hover (String locator) {

        MobileElement element = (MobileElement) driver.findElement(By.name(locator));
        Actions builder = new Actions(driver);
        Action hoverOver = null;

        try {
            // Build action for mouse to move open location. This uses the build() method.
            hoverOver = builder
                    .moveToElement(element)
                    .build();

            hoverOver.perform();
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }

    }

    // Package private css value
    protected String getCSSValue  (MobileElement element, String propertyName) {
        scrollToElement(element);
        String cssValue = null;

        try {
            cssValue = element.getCssValue(propertyName);
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
        return cssValue;
    }

    //Package private scroll
    protected void scrollToElement(MobileElement element){
        try {
            String elementID = element.getId();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("element", elementID);
            scrollObject.put("toVisible", "element");
            js.executeScript("mobile: scroll", scrollObject);
            ExtentTestManager.getTest().log(Status.INFO,"Scrolling to " + element.getAttribute("name"));
        } catch (WebDriverException e) {
            ExtentTestManager.getTest().log(Status.ERROR, "Unable to scroll to element");
        }
    }

    protected void scrollUp(MobileElement element){
        while (!element.isDisplayed()) {
            TouchAction actions = new TouchAction((PerformsTouchActions) driver);
            actions.press(PointOption.point(10, 100))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(10, 250))
                    .perform();
        }
    }

    protected void radioButton(boolean select, MobileElement locatorButtonOne, MobileElement locatorButtonTwo){
        try {
            if (select){
                click(locatorButtonOne);//FIXME: needs accessibility ID
            } else {
                click(locatorButtonTwo);
            }
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch (ElementNotSelectableException e) {
            //TODO: Sysout(e.getMessage());
        }
        catch(NoSuchElementException e) {
            //TODO: Sysout(e.getMessage());
        }
    }

    protected void IosDatePickerWheel(HashMap<String, String> map, MobileElement element){
        if (!element.isDisplayed()){
            scrollToElement(element);
        }
        click(element);
        WebElement yearElement = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(0);
        yearElement.sendKeys(map.get("month"));
        WebElement monthElement = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(1);
        monthElement.sendKeys(map.get("day"));
        WebElement dayElement = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(2);
        dayElement.sendKeys(map.get("year"));
        ExtentTestManager.getTest().log(Status.INFO,"Entered date "  + element.getAttribute("name"));
    }

    protected void IosPickerWheel(MobileElement element, String choice){
        scrollToElement(element);
        click(element);
        WebElement wheel = (WebElement) driver.findElements(By.className("XCUIElementTypePickerWheel")).get(0);
        wheel.sendKeys(choice);
    }

    protected int IosTableCellCount(){
        List cells = driver.findElements(By.className("XCUIElementTypeCell"));
        return cells.size();
    }

    protected void clickSpecificIosTableCell(String locator){
        try {
            MobileElement element = (MobileElement) driver.findElement(By.name(locator));
            element.click();
        }
        catch (ElementNotVisibleException e) {
            //TODO: Sysout(e.getMessage());
        }
    }

    protected void clickGenericIostableCell(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.className("XCUIElementTypeCell")).click();
        ExtentTestManager.getTest().log(Status.INFO,"Clicked table cell");
    }

    protected int CheckboxSelected(MobileElement element) {
        try {
            int value;
            String s = element.getAttribute("value");
            if (s != null) {
                value = 1;
            } else {
                value = 0;
            }
            ExtentTestManager.getTest().log(Status.INFO,"Checked value of textbox " + element.getAttribute("name"));
            return value;
        } catch (ElementNotVisibleException e) {
            System.out.println(e.getMessage());
        }
        return 2;
    }

    protected boolean doesKeyboardExist(){
        try {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            MobileElement element = (MobileElement) driver.findElement(By.className("XCUIElementTypeKeyboard"));
            return element.isDisplayed();
        }
        catch (ElementNotVisibleException e) {
            return false;
        }
    }

    protected void scrollToEnd(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
        ExtentTestManager.getTest().log(Status.INFO,"Scrolling to Bottom");
    }

    protected void scrollToTop(){
        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
        ExtentTestManager.getTest().log(Status.INFO,"Scrolling to top");
    }

    public void takeScreenshot() {

        File file = driver.getScreenshotAs(OutputType.FILE);
        String file_name = "Screenshot" + new Date().getTime() + ".png";
        String newFilePath = Constants.Screenshot_Folder_Path +  "/" + file_name;
        try {
            FileUtils.copyFile(file, new File(newFilePath));
            ExtentTestManager.getTest().log(Status.INFO, "Screenshot : " , MediaEntityBuilder.createScreenCaptureFromPath(file_name).build());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
