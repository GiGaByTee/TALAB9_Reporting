package com.halas.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.halas.util.property.HandleProperty.getValueProperty;

public final class WebDriverManager {
    private static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    static {
        System.setProperty(getValueProperty("name"), getValueProperty("path"));
    }

    private WebDriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (Objects.isNull(DRIVER_POOL.get())) {
            createWebDriver();
        }
        return DRIVER_POOL.get();
    }

    public static void shutdown() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }

    private static void createWebDriver() {
        DRIVER_POOL.set(new ChromeDriver());
        DRIVER_POOL.get().manage()
                .timeouts()
                .implicitlyWait(Integer.valueOf(getValueProperty("implicit-wait-time")), TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
    }
}
