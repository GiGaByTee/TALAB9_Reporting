package com.sofia.pageobjects.gmailpages;

import com.sofia.decorators.elements.Input;
import com.sofia.decorators.elements.Label;
import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.support.FindBy;

public class GmailSignInPageObj extends GeneralGmailPage {
    @FindBy(xpath = "//input[@id='identifierId']")
    private Input usernameInput;

    @FindBy(id = "profileIdentifier")
    private Label activeUsername;

    @FindBy(name = "password")
    private Input passwordInput;

    public void typeUernameAndSubmit(String usernameInputValue) {
        usernameInput.sendKeys(usernameInputValue);
        usernameInput.submitInput();
    }

    public void typePasswordAndSubmit(String passwordInputValue) {
        passwordInput.sendKeys(passwordInputValue);
        passwordInput.submitInput();
    }

    public String getActiveUsernameAttributeValue(){
        return activeUsername.getAttributeValue();
    }
}
