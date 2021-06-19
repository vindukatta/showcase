package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExtentManager {
    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static ExtentReports extent;
    private static String reportFileName = Constants.Report_File_Name + ".html";
    private static String fileSeparator = System.getProperty("file.separator");
    private static String reportFileDir =  Constants.Report_Directory + fileSeparator + Constants.Report_File_Name ;
    private static String reportFilePath = reportFileDir + fileSeparator + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {

        createReportDirectory();

        Constants.Screenshot_Folder_Path = reportFileDir ;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFilePath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
        extent.setSystemInfo("OS", "Mac");
        extent.setSystemInfo("AUT", "QA");

        return extent;
    }

    //Create the report path
    private static void createReportDirectory() {
        try {
            File testDirectory = new File(Constants.Report_Directory);
            if (!testDirectory.exists()) {
                testDirectory.mkdir();
            }
            File reportDirectory = new File(reportFileDir);
            if (reportDirectory.exists()) {
                FileUtils.cleanDirectory(reportDirectory);
                reportDirectory.delete();
            }
            reportDirectory.mkdir();
        }catch (Exception e){
            System.out.println("Exception occurred while creating report folder");
            e.printStackTrace();
        }
    }
}
