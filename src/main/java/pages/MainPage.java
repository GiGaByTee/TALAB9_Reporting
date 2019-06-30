package pages;

import elements.*;
import model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(className = "aic")
    private Button composeButton;

    @FindBy(name = "to")
    private TextField whomField;

    @FindBy(xpath = "//span[@class='vN bfK a3q']")
    private TextField savedEmail;

    @FindBy(className = "aoT")
    private TextField subjectField;

    @FindBy(css = ".Ar.Au div")
    private TextField textField;

    @FindBy(xpath = "//img[@class ='Ha']")
    private Button closeButton;

    @FindBy(xpath = "//a[contains(@href,'drafts')]")
    private Label draftFolder;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[1]/td[5]")
    private Label lastMessageInDraftFolder;

    @FindBy(css = ".dC div")
    private Button sendButton;

    public void clickComposeButton() {
        composeButton.click();
    }

    public void typeWhomField(String emailAddress) {
        whomField.sendKeys(emailAddress);
    }

    public void typeSubjectField(String subject) {
        subjectField.sendKeys(subject);
    }

    public void typeTextField(String text) {
        textField.sendKeys(text);
    }

    public void clickOnCloseMessageButton() {
        closeButton.click();
    }

    public void openListOfDrafts() {
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//a[contains(@href,'drafts')]")));
        draftFolder.click();
       }

    public void clickOnLastMessageInDraftFolder() {
        lastMessageInDraftFolder.click();
    }

    public Email takeEmail() {
        Email email = new Email();
        email.setEmailAddress(savedEmail.getAttribute("email"));
        email.setSubject(subjectField.getAttribute("value"));
        email.setBody(textField.getText());
        return email;
    }

    public void clickSendButton() {
        sendButton.click();
    }
}

