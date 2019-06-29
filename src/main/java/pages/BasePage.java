package pages;

import driver.DriverProvider;
import decorator.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    BasePage() {
        driver = DriverProvider.getWebDriver();
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }
}