package com.igor.utils.provider;

import com.igor.utils.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;
import static com.igor.utils.constant.Constants.IMPLICIT_WAIT;

public class DriverProvider {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();
    private static final String NAME = Objects.requireNonNull(Property.getProperty("name"));
    private static final String PATH = Objects.requireNonNull(Property.getProperty("path"));
    private static final boolean HEADLESS_MODE = Boolean.valueOf(Property.getProperty("headless"));

    static {
        System.setProperty(NAME, PATH);
    }

    private DriverProvider() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(DRIVER_POOL.get())) {
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(HEADLESS_MODE);
        DRIVER_POOL.set(new ChromeDriver(options));
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().pageLoadTimeout(EXPLICIT_WAIT, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().setScriptTimeout(EXPLICIT_WAIT, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().fullscreen();
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
