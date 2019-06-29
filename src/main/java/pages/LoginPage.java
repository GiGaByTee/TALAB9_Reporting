package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private TextField emailField;

    @FindBy(xpath = "//span[@class='RveJvd snByac']")
    private Button nextButton;

    @FindBy(name = "password")
    private TextField passwordField;

    public void typeUsername(String username) {
        emailField.sendKeys(username);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='RveJvd snByac']")));
        nextButton.click();
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys(password);
    }
}


