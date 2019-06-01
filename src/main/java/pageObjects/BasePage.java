package pageObjects;

import driver.DriverLoader;
import elements.Checkbox;
import elements.decorator.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage() {
        driver = DriverLoader.getDriver();
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void selectEmails(int letterAmount, List<Checkbox> webElementList) {
        for (int i = 0; i < letterAmount; i++) {
            try {
                webElementList.get(i).setChecked();
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                webElementList.get(i).setChecked();
            }
        }
    }
}
