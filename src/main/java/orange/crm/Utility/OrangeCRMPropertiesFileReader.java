package orange.crm.Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class OrangeCRMPropertiesFileReader {

    public Properties getProperty() {
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/browser-config.properties"));
            properties.load(new FileInputStream("resources/testdata-config.properties"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return properties;
    }

}
