package testUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private final String propertyFilePath= "config.properties";

    public ConfigReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getPlatformName(){
        String platformName = properties.getProperty("platformName");
        if(platformName!= null){
            return platformName;
        } else {
            throw new RuntimeException("platform not specified in the Configuration.properties file.");
        }
    }

    public String getVersion(){
        String versionName = properties.getProperty("version");
        if(versionName!= null){
            return versionName;
        } else {
            throw new RuntimeException("version not specified in the Configuration.properties file.");
        }
    }

    public String getDeviceName(){
        String deviceName = properties.getProperty("device");
        if(deviceName!= null){
            return deviceName;
        } else {
            throw new RuntimeException("device not specified in the Configuration.properties file.");
        }
    }

    public String getAppPath(){
        String appPath = properties.getProperty("simulatorPath");
        if(appPath!= null){
            return appPath;
        } else {
            throw new RuntimeException("app path not specified in the Configuration.properties file.");
        }
    }

}

