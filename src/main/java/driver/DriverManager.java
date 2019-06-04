package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverPropertyManager;
import utils.Property;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    static Logger logger = LogManager.getLogger(DriverManager.class);

    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();;

    public static WebDriver getDriver(){
        return getWebDriverInstance();
    }

    private static WebDriver getWebDriverInstance(){
        if (Objects.isNull(DRIVER_POOL.get())){
            DriverPropertyManager.setSystemWebDriverProperty();
            DRIVER_POOL.set(new ChromeDriver());
            DRIVER_POOL.get().manage().window().maximize();
            DRIVER_POOL.get().manage().timeouts().implicitlyWait(Long.parseLong(Property.get("implicit.wait")), TimeUnit.SECONDS);
        }
        return DRIVER_POOL.get();
    }

    public static void driverQuit(){
        if (!Objects.isNull(getDriver())){
            getDriver().quit();
            DRIVER_POOL.set(null);
            logger.info("WebDriver is down.");
        }
    }
}
