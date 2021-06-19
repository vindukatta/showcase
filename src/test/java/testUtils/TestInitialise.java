package testUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import utils.Constants;
import java.net.MalformedURLException;
import java.net.URL;

@Listeners(TestListener.class)
public class TestInitialise extends TestListenerAdapter {

    ConfigReader config = new ConfigReader();
    private static AppiumDriverLocalService service;
    protected AppiumDriver driver;

    @Parameters({"udid", "deviceName", "wdaLocalPort", "hardware"})
    @BeforeMethod
    public void setUp(@Optional("") String udid, @Optional("iPhone X") String deviceName, @Optional("8100") int wdaLocalPort, @Optional("0") int hardware) throws MalformedURLException {

        Constants.Report_File_Name = this.getClass().getSimpleName();

        /** The below code can start Appium server */
//        service = AppiumDriverLocalService.buildDefaultService();
//        service.start();

        if (hardware == 1){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.PLATFORM_NAME);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.IOS_VERSION);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
            capabilities.setCapability("wdaLocalPort", wdaLocalPort);
            capabilities.setCapability("bundleId", "com.hsbc.enterprise.iceberg.hzz");
            capabilities.setCapability("xcodeOrgId", "PVK3BL2ACY");
            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
            capabilities.setCapability("updatedWDABundleId", "com.appium.test.ice");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        } else {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.PLATFORM_NAME.trim());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.IOS_VERSION.trim());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME.trim());
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
            capabilities.setCapability("wdaLocalPort", wdaLocalPort);
            capabilities.setCapability(MobileCapabilityType.APP, Constants.APP_PATH.trim());
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");

            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
    }

    @AfterTest()
    public void takeDown(){
        driver.quit();
//        service.stop();
    }
}

