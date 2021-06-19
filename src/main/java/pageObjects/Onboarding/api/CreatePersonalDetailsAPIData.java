package pageObjects.Onboarding.api;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CreatePersonalDetailsAPIData {

    public LinkedHashMap<String, Object> getPersonalDetails(HashMap<String, String> dataSet) {

        LinkedHashMap<String, Object> personalDetails = new LinkedHashMap<>();

        personalDetails.put("residentialAddress",new LinkedHashMap<String, Object>() {{
            String[] address;
            if(Boolean.parseBoolean(dataSet.get("IsResidentialAddressDifferent"))==Boolean.TRUE) {
                address = dataSet.get("ResidentialAddress").split(";");
            }else{
                address = dataSet.get("BusinessAddress").split(";");
            }
            put("addressLine1",address[0]);
            put("country","United Kingdom");
            //put("addressLine2","Timperley");
            put("city",address[1]);
            put("postcode",address[2]);
        }});

        boolean value = Boolean.parseBoolean(dataSet.get("IsResidentialAddressDifferent").toLowerCase());
        personalDetails.put( "residentialAddressIsDifferentToBusinessAddress",value);
        personalDetails.put("dob",dataSet.get("DOB"));

        personalDetails.put("nameElements",new LinkedHashMap<String, Object>() {{
            put("firstName",dataSet.get("FirstName"));
            put("lastName",dataSet.get("LastName"));
            put("title",dataSet.get("Title"));
            put("middleName",dataSet.get("MiddleName"));
        }});

        personalDetails.put("nationality",new LinkedHashMap<String, Object>() {{
            put("name",dataSet.get("Nationality"));
            put("identifier","");
        }});

        personalDetails.put("email",dataSet.get("Email"));
        personalDetails.put("mobileNumber",dataSet.get("Mobile"));

        return  personalDetails;
    }
}