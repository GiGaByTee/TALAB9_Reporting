package utils;

public class DriverPropertyManager {

    public static void setSystemWebDriverProperty(){
        if (System.getProperty("os.name").contains(Property.get("mac.os"))){
            System.setProperty(Property.get("chrome.driver.property"), Property.get("chrome.driver.path"));
        } else if (System.getProperty("os.name").contains(Property.get("win.os"))){
            System.setProperty(Property.get("chrome.driver.property"), Property.get("chrome.driver.path") + ".exe");
        };
    }
}
