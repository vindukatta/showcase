package testUtils;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Constants;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtils;

import java.lang.reflect.Field;

import static org.testng.Reporter.log;

public class APITestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //Constants.Report_File_Name = iTestResult.getName();
        log("START");
        System.out.println("Started test for "+iTestResult.getName());
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log(".");
        System.out.println("PASSED - "+iTestResult.getName());
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log("F");
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
