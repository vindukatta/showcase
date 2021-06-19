package Onboarding_tests.APITests;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.Onboarding.api.OnBoard_API_CustomerData;
import testUtils.APITestInitialise;
import testUtils.APITestListener;
import utils.Constants;
import utils.ExcelDataDriver;
import utils.ExtentTestManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static io.restassured.RestAssured.given;

@Listeners(APITestListener.class)
public class ApplicationAcceptenceAPITest_415 extends APITestInitialise {

    int counter = 0;

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardUserAPIData400").createDataProvider();
    }


    @Test(dataProvider = "dataSet")
    public void acceptanceAPI(HashMap<String, String> dataSet){

        System.out.println("Scenario : [" + ++counter + "]");

        RestAssured.port = Integer.valueOf(8090);
        RestAssured.useRelaxedHTTPSValidation();
        LinkedHashMap<String, LinkedHashMap<String, Object>> customerData = new OnBoard_API_CustomerData().createOnBoardAPICustomerData(dataSet);

        JSONObject json = new JSONObject(customerData);

        String uri = "http://mule.icepick.guru:80/iceberg/v1/onboarding/users/";

        ExtentTestManager.getTest().log(Status.INFO, "URI : [" + uri + "]");

        ExtentTestManager.getTest().log(Status.INFO, "Post Request Headers :[Content-Type:application/json] ");

        ExtentTestManager.getTest().log(Status.INFO, "Post Request Body :[" + json + "] ");

        int response = given().header("Content-Type", "text/html").
                body(json.toString()).
                when().post(uri).getStatusCode();

        ExtentTestManager.getTest().log(Status.INFO, "Expected Response : [415], Actual Response:[" + response + "] ");

        System.out.println(response);
        Assert.assertEquals(response,415);

    }

}
