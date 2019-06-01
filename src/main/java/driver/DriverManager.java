package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilits.properties.FilePath;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static FilePath filePath = new FilePath();

    static {
        System.setProperty("webdriver.chrome.driver", filePath.propertyFile("filePathChrome"));
    }

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driverPool.get())) {
            driverPool.set(new ChromeDriver());
            driverPool.get().get(filePath.propertyFile("filePathGmail"));
            driverPool.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driverPool.get().manage().window().maximize();
        }
        return driverPool.get();
    }

    public static void tearDown() {
        if(!Objects.isNull(driverPool.get())){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
