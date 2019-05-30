package com.igor.page;

import com.igor.decorator.CustomFieldDecorator;
import com.igor.utils.provider.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    protected BasePage(){
        driver = DriverProvider.getDriver();
        webDriverWait = new WebDriverWait(driver, EXPLICIT_WAIT);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }
}
