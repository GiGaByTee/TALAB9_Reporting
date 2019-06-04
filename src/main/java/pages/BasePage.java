package pages;

import driver.DriverManager;
import element.element.utils.FieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    private WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new FieldDecorator(driver), this);
    }

    public WebDriver driver(){
        return this.driver;
    }

    public Wait expWait(int seconds){
        Wait expWait = new WebDriverWait(driver, seconds);
        return expWait;

    }

    public JavascriptExecutor jsExecutor(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor;
    }

    public String isPageLoaded(){
        return (String) jsExecutor().executeScript("return document.readyState");
    }

}
