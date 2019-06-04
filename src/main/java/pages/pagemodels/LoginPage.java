package pages.pagemodels;

import element.custom.elements.Button;
import element.custom.elements.Input;
import element.custom.elements.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class LoginPage extends BasePage {
    Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@type='email']")
    private Input emailInput;

    @FindBy(xpath = "//div[@id='identifierNext']//span")
    private Button loginNextBtn;

    @FindBy(xpath = "//input[@type='password']")
    private Input passwField;

    @FindBy(xpath = "//div[@id='passwordNext']//span")
    private Button passwNextBtn;

    public LoginPage() {
    }

    public Input getEmailInput() {
        return emailInput;
    }

    public Button getLoginNextBtn() {
        return loginNextBtn;
    }

    public Input getPasswField() {
        return passwField;
    }

    public Button getPasswNextBtn() {
        return passwNextBtn;
    }

    public void navigateToLoginPg(){
        logger.trace("Navigating to 'https://mail.google.com'...");
        driver().navigate().to("https://mail.google.com");
    }

    public void setEmailField(String email){
        logger.trace("Typing email...");
        getEmailInput().sendKeys(email);
    }

    public void clickLoginNextBtn(){
        logger.trace("Clicking Next btn...");
        getLoginNextBtn().click();
    }

    public void setPasswrd(String passw){
        logger.trace("Typing password...");
        expWait(35).until(ExpectedConditions.elementToBeClickable(getPasswField().getWebElement()));
        getPasswField().sendKeys(passw);
    }

    public GmailPage clickPasswordNextBtn() {
        logger.trace("Clicking Next btn...");
        getPasswNextBtn().click();
        return new GmailPage();
    }
}
