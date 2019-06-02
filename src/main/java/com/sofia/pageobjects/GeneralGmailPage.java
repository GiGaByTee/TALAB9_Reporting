package com.sofia.pageobjects;

import com.sofia.decorators.customdecotator.CustomFieldDecorator;
import com.sofia.utilmanager.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sofia.utilmanager.driver.DriverManager.*;

public abstract class GeneralGmailPage {
    protected WebDriver driver;
    private WebDriverWait webDriverWait;

    public GeneralGmailPage() {
        driver = DriverManager.getDriverInstance();
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(new CustomFieldDecorator(getDriverInstance()), this);
    }
}