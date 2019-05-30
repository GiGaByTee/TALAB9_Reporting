package com.igor.page;

import com.igor.decorator.element.TextArea;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BasePage{
    @FindBy(id = "identifierId")
    private TextArea usernameField;
    @FindBy(name = "password")
    private TextArea passwordField;

    public void setUsernameAndSubmit(String username){
        usernameField.typeAndSubmit(username);
    }

    public void setPasswordAndSubmit(String password){
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordField.getWebElement()));
        passwordField.typeAndSubmit(password);
    }
}
