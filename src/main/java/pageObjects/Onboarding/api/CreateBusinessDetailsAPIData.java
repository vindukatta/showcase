package pageObjects.Onboarding.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CreateBusinessDetailsAPIData {

    public LinkedHashMap<String, Object> getBusinessDetails(HashMap<String, String> dataSet) {

        LinkedHashMap<String, Object> businessDetails = new LinkedHashMap<>();
        businessDetails.put("incorporationCountry",dataSet.get("InCorporationCountry"));
        boolean value = Boolean.parseBoolean(dataSet.get("IsTradingAddressDifferent").toLowerCase());
        businessDetails.put("tradingAddressIsDifferentToBusinessAddress",value);
        businessDetails.put("tradeName",dataSet.get("TradingName"));
        businessDetails.put("incorporationDate",dataSet.get("InCorporationDate"));
        businessDetails.put("entityType",dataSet.get("EntityType"));

        businessDetails.put("businessAddress",new LinkedHashMap<String, Object>() {{
            String[] address = dataSet.get("BusinessAddress").split(";");
            put("addressLine1",address[0]);
            put("country","United Kingdom");
            put("city",address[1]);
            put("postcode",address[2]);
        }});

        businessDetails.put("legalName",dataSet.get("LegalName"));
        businessDetails.put("ownerFullName",dataSet.get("OwnerFullName"));
        businessDetails.put("companyStatus",dataSet.get("CompanyStatus"));
        businessDetails.put("crn",dataSet.get("CRN"));
        businessDetails.put("numberOfEmployees",Integer.parseInt(dataSet.get("NoOfEmployess")));
        businessDetails.put("companyType",dataSet.get("CompanyType"));

        businessDetails.put("businessActivities",getSicCodes(dataSet));

        businessDetails.put("tradingAddress", new LinkedHashMap<String, Object>() {{
            String[] address = dataSet.get("TradingAddress").split(";");
            put("addressLine1", address[0]);
            put("country", "United Kingdom");
            put("city", address[1]);
            put("postcode", address[2]);
        }});

        businessDetails.put("naturesOfControl",new ArrayList<>(Arrays.asList(dataSet.get("NatureOfControl").split(";"))));

        return  businessDetails;
    }

    public ArrayList<LinkedHashMap<String, String>> getSicCodes(HashMap<String, String> dataSet) {
        ArrayList<LinkedHashMap<String, String>> sicodes = new ArrayList<>();

        String[] sicodeslist = dataSet.get("SicCodes").split(";");
        int i = 0 ;
        while(i < sicodeslist.length){
            String details = sicodeslist[i];
            String[] sicDetails = details.split("=");
            sicodes.add(new LinkedHashMap<String, String>(){{
                put("description",sicDetails[1].trim());
                put("code",sicDetails[0].trim());
            }});
            i++;
        }
        return sicodes;
    }
}