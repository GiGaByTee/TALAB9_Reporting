package com.halas.page.gmail;

import com.halas.decorator.element.realisation.Button;
import com.halas.decorator.element.realisation.EditText;
import com.halas.page.CommonPage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailAuthorizationPage extends CommonPage {
    @FindBy(id = "identifierId")
    private EditText loginField;
    @FindBy(css = "*[role='button'][id='identifierNext']")
    private Button nextLogin;
    @FindBy(css = "input[name='password']")
    private EditText passwordField;
    @FindBy(id = "passwordNext")
    private Button nextPassword;

    public void fillLoginAreaAndClickNext(String login) {
        loginField.sendKeys(login);
        driverWait.until(ExpectedConditions.visibilityOf(nextLogin.getElement()));
        nextLogin.click();
    }

    public void fillPasswordAreaAndClickNext(String password) {
        driverWait.until(ExpectedConditions.visibilityOf(passwordField.getElement()));
        passwordField.sendKeys(password);
        nextPassword.click();
    }
}
