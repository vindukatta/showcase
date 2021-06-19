package pageObjects.Onboarding.api;

import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CreateBusinessFinancialDetailsAPIData {

    HashMap<String, String> dataSet;

    public LinkedHashMap<String, Object> getBusinessFinancialDetails(HashMap<String, String> dataSet) {

        this.dataSet = dataSet;

        LinkedHashMap<String, Object> businessFinancialDetails = new LinkedHashMap<>();
        businessFinancialDetails.put("sourcesOfWealth",getSourceOfWealthDetails());
        businessFinancialDetails.put("transactionDetails",getTransactionDetails());
        businessFinancialDetails.put("initialSourceOfWealth",getInitialSourceOfWealthDetails());
        businessFinancialDetails.put("depositorsDetails",getSourceOfFundsDetails());

        return  businessFinancialDetails;
    }


    public LinkedHashMap<String, Object> getSourceOfWealthDetails() {

        LinkedHashMap<String, Object> sow = new LinkedHashMap<>();

        sow.put("sources",new ArrayList<>(Arrays.asList(dataSet.get("AnnualTurnoverFundsOptions").split(";"))));

        sow.put("turnover",new LinkedHashMap<String, Object>(){{
            put("currency","GBP");
            put("amount",dataSet.get("AnnualTurnover"));
        }});

        return sow;
    }

    public LinkedHashMap<String, Object> getTransactionDetails() {

        LinkedHashMap<String, Object> transactionDetails = new LinkedHashMap<>();

        transactionDetails.put("transactionTypes",new ArrayList<>(Arrays.asList(dataSet.get("TransactionTypes").split(";"))));

        transactionDetails.put("accountUsage",new ArrayList<>(Arrays.asList(dataSet.get("TransactionAccountUsage").split(";"))));

        return transactionDetails;
    }

    public LinkedHashMap<String, Object> getInitialSourceOfWealthDetails() {

        LinkedHashMap<String, Object> iSOW = new LinkedHashMap<>();

        iSOW.put("totalNumberOfInvestors",1);

        iSOW.put("investors",new ArrayList<>(Arrays.asList(new LinkedHashMap<String, Object>(){{
            put("name","Investor 1");
            if(isCompanyMoreThan3YOld()) {
                put("sourceOfFunds", new ArrayList<>());
            } else{
                put("sourceOfFunds", new ArrayList<>(Arrays.asList(dataSet.get("InitialSOWFundsOptions").split(";"))));
            }
        }})));

        if(!isCompanyMoreThan3YOld()) { //Less than 3Y old companies
            iSOW.put("totalInvestedAmount", new LinkedHashMap<String, Object>() {{
                put("currency", "GBP");
                put("amount", dataSet.get("InitialSOWAmount"));
            }});
        }

        return iSOW;
    }


    public LinkedHashMap<String, Object> getSourceOfFundsDetails() {

        LinkedHashMap<String, Object> sof = new LinkedHashMap<>();

        sof.put("depositors",new ArrayList<>(Arrays.asList(new LinkedHashMap<String, Object>(){{
            put("name","Depositor 1");
            put("sourceOfFunds",new ArrayList<>(Arrays.asList(dataSet.get("SourceOfFundsOptions").split(";"))));
        }})));

        sof.put("totalDepositedAmount",new LinkedHashMap<String, Object>(){{
            put("currency","GBP");
            put("amount",dataSet.get("SOFDepositedAmount"));
        }});

        sof.put("totalNumberOfDepositors",1);

        sof.put("lastTwelveMonthsFunds",new LinkedHashMap<String, Object>(){{
            put("currency","GBP");
            put("amount",dataSet.get("Last12MonthsFunds"));
        }});

        return sof;
    }

    public boolean isCompanyMoreThan3YOld(){
        boolean check = false;

        try{

            String dateOfIncorporation = dataSet.get("InCorporationDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date incoprorationDate = sdf.parse(dateOfIncorporation);
            Date todaysDate = new Date();

            Calendar companyIncorpationDate = getCalendar(incoprorationDate);
            Calendar todaysCalenderDate = getCalendar(todaysDate);

            if((todaysCalenderDate.get(YEAR) - companyIncorpationDate.get(YEAR)) > 3){
                return true;
            }else if((todaysCalenderDate.get(YEAR) - companyIncorpationDate.get(YEAR)) == 3){
                if(companyIncorpationDate.get(MONTH) < todaysCalenderDate.get(MONTH)){
                    return true;
                }
                else if(companyIncorpationDate.get(MONTH) == todaysCalenderDate.get(MONTH) && companyIncorpationDate.get(DATE) < todaysCalenderDate.get(DATE)){
                    return true;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.UK);
        cal.setTime(date);
        return cal;
    }

}