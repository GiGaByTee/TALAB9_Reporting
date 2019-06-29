package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverProvider() {
    }

    public static WebDriver getWebDriver() {
        if (Objects.isNull(driver.get())) {
            driver.set(new ChromeDriver());
            driver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}




