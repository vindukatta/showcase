package testUtils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtils;

import java.lang.reflect.Field;

import static org.testng.Reporter.log;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log("START");
        System.out.println("Started test for "+iTestResult.getName());
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log(".");
        Class clazz = iTestResult.getTestClass().getRealClass().getSuperclass();
        try {
            Field field = clazz.getDeclaredField("driver");
            AppiumDriver driver = (AppiumDriver) field.get(iTestResult.getInstance());
            ScreenshotUtils screenshot = new ScreenshotUtils(driver);
            screenshot.takeScreenshot("TESTS PASSED",iTestResult.getName().substring(0, iTestResult.getName().indexOf(" ")));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("PASSED - "+iTestResult.getName());
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log("F");
        Class clazz = iTestResult.getTestClass().getRealClass().getSuperclass();
        try {
            Field field = clazz.getDeclaredField("driver");
            AppiumDriver driver = (AppiumDriver) field.get(iTestResult.getInstance());
            ScreenshotUtils screenshot = new ScreenshotUtils(driver);
            screenshot.takeScreenshot("TESTS FAILED",iTestResult.getName().substring(0, iTestResult.getName().indexOf(" ")));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("FAILED - "+iTestResult.getName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log("S");
        System.out.println("SKIPPED - "+iTestResult.getName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

}
