package pageObjects.Onboarding.api;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OnBoard_API_CustomerData {

    public LinkedHashMap<String, LinkedHashMap<String, Object>> customer;

    public LinkedHashMap<String, LinkedHashMap<String, Object>> createOnBoardAPICustomerData(HashMap<String, String> dataSet) {

        LinkedHashMap<String, LinkedHashMap<String, Object>> newCustomer = new LinkedHashMap<>();

        newCustomer.put("businessDetails", new CreateBusinessDetailsAPIData().getBusinessDetails(dataSet));
        newCustomer.put("businessFinancialDetails", new CreateBusinessFinancialDetailsAPIData().getBusinessFinancialDetails(dataSet));
        newCustomer.put("personalDetails", new CreatePersonalDetailsAPIData().getPersonalDetails(dataSet));
        return newCustomer;
    }
}
