package Onboarding_tests.APITests;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Onboarding.api.OnBoard_API_CustomerData;
import static io.restassured.RestAssured.*;
import testUtils.TestInitialise;
import utils.Constants;
import utils.ExcelDataDriver;
import java.util.*;

public class NewCustomerOnboardingAPITest extends TestInitialise {

    int counter = 0;

    @DataProvider(name = "dataSet")
    public Object[][] testDataProvider() {
        return new ExcelDataDriver(Constants.File_OnBoarding_TestData, "OnBoardUserAPIData").createDataProvider();
    }


    @Test(dataProvider = "dataSet")
    public void acceptanceAPI(HashMap<String, String> dataSet){


        System.out.println("Scenario : ["+ ++counter + "]");

        RestAssured.port = Integer.valueOf(8090);
        RestAssured.useRelaxedHTTPSValidation();
        LinkedHashMap<String, LinkedHashMap<String, Object>> customerData = new OnBoard_API_CustomerData().createOnBoardAPICustomerData(dataSet);


        JSONObject json = new JSONObject(customerData);
        System.out.println(json);

        int response = given().header("Content-Type", "application/json").
                body(json.toString()).
                when().post("http://mule.icepick.guru:80/iceberg/v1/onboarding/users/").getStatusCode();

        Assert.assertEquals(response,201);

        System.out.println(response);

    }

}
