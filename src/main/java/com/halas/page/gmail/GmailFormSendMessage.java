package com.halas.page.gmail;

import com.halas.decorator.element.realisation.Button;
import com.halas.decorator.element.realisation.EditText;
import com.halas.page.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailFormSendMessage extends CommonPage {
    //means email field when focus on email
    @FindBy(css = "form[enctype='multipart/form-data'] textarea[name='to']")
    private EditText fieldWhichEmailsSend;
    @FindBy(css = "[aria-label*='Ctrl'][aria-label*='Shift'][aria-label*='C'][role='link']")
    private Button openCCfield;
    @FindBy(name = "cc")
    private EditText fieldCC;
    @FindBy(css = "[aria-label*='Ctrl'][aria-label*='Shift'][aria-label*='B'][role='link']")
    private Button openBCCfield;
    @FindBy(name = "bcc")
    private EditText fieldBCC;
    @FindBy(name = "subjectbox")
    private EditText fieldSubject;
    @FindBy(css = "div[role='textbox']")
    private EditText fieldMessageSend;
    @FindBy(css = "*[class='Ha'][src='images/cleardot.gif']")
    private Button saveAndCloseFormMessage;
    @FindBy(css = "div[role='button'][data-tooltip*='Ctrl'][data-tooltip*='Enter']")
    private Button sendMessage;
    //means email when focus on another elements
    @FindBy(css = "form[enctype='multipart/form-data']>div:nth-child(2)")
    private Button email;

    public void clickOnCC() {
        driverWait.until(ExpectedConditions.visibilityOf(openCCfield.getElement()));
        openCCfield.click();
    }

    public void clickOnBCC() {
        openBCCfield.click();
    }

    public void fillEmailField(String emailsReceive) {
        fieldWhichEmailsSend.sendKeys(emailsReceive);
    }

    public void fillCCField(String emailsCopyReceive) {
        fieldCC.sendKeys(emailsCopyReceive);
    }

    public void fillBCCField(String emailsHiddenCopyReceive) {
        fieldBCC.sendKeys(emailsHiddenCopyReceive);
    }

    public void fillSubjectField(String subject) {
        fieldSubject.sendKeys(subject);
    }

    public void fillMessageField(String message) {
        fieldMessageSend.sendKeys(message);
    }

    public void clickOnButtonSaveAndCloseFormMessage() {
        saveAndCloseFormMessage.click();
    }

    public void clickOnEmailField() {
        driverWait.until(webDriver -> {
            email.click();
            return true;
        });
    }

    public boolean isDisplayedEmailFieldWithoutCcAndBcc() {
        return email.isDisplayed();
    }

    public void clickOnSendMessage() {
        driverWait.until(webDriver -> {
            try {
                sendMessage.click();
                return true;
            } catch (ElementClickInterceptedException e) {
                driver.findElement(By.cssSelector("[aria-live='assertive'][role='alert'] *[role='button']")).click();
            }
            return false;
        });
    }

    public String getTextFieldWhichEmailsSend() {
        return driver.findElement(By.cssSelector("form[enctype='multipart/form-data'] input[name='to']"))
                .getAttribute("value");
    }

    public String getTextFromCcField() {
        return fieldCC.getElement().getAttribute("value");
    }

    public String getTextFromBccField() {
        return fieldBCC.getElement().getAttribute("value");
    }

    public String getTextFieldThemeSend() {
        return driver.findElement(
                By.cssSelector("form[enctype='multipart/form-data'] [name='subject']"))
                .getAttribute("value");
    }

    public String getTextFromMessage() {
        return fieldMessageSend.getText();
    }

    public void waitUntilSendingMessageWillEnd() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
                "[role='alert'] [role='link'][id='link_undo']")));
    }
}
