package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import pageObjects.Base.BasePage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils extends BasePage {

    public ScreenshotUtils(AppiumDriver driver) {
        super(driver);
    }

    /*
     *
     *
     */
    public void takeScreenshot(String type, String identifier) {
        String folder;
        DateFormat df;
        File file = driver.getScreenshotAs(OutputType.FILE);

        df = new SimpleDateFormat("dd-MM-yyyy");
        folder = "screenshots " + df.format(new Date());

        new File(folder).mkdir();
        String file_name = identifier + ".png";

        try {
            FileUtils.copyFile(file, new File(folder + "/" + type + "/" + file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

