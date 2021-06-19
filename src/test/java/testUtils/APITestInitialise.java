package testUtils;

import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import utils.Constants;

public class APITestInitialise extends TestListenerAdapter {

    @BeforeMethod
    public void setUp() {
        Constants.Report_File_Name = this.getClass().getSimpleName();
    }

}

