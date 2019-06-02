package com.sofia.utilmanager.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.sofia.utilmanager.jsonparser.JsonParser.getImplicitWait;
import static com.sofia.utilmanager.property.Property.getProperty;

public class DriverManager {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();
    private static final String DRIVER_PATH = getProperty("path");
    private static final String WEB_DRIVER_NAME = getProperty("name");
    private static final int IMPLICIT_WAIT = getImplicitWait();

    private DriverManager() {
    }

    static {
        System.setProperty(WEB_DRIVER_NAME, DRIVER_PATH);
    }

    public static WebDriver getDriverInstance() {
        if (Objects.isNull(DRIVER_POOL.get())) {
            DRIVER_POOL = setSettings();
        }
        return DRIVER_POOL.get();
    }

    private static ThreadLocal<WebDriver> setSettings() {
        DRIVER_POOL.set(new ChromeDriver());
        DRIVER_POOL.get().manage().timeouts()
                .implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
        return DRIVER_POOL;
    }

    public static void quitDriver() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
