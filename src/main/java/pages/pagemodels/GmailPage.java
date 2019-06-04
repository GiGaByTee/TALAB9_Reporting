package pages.pagemodels;

import element.custom.elements.Button;
import element.custom.elements.Input;
import element.custom.elements.Label;
import element.custom.elements.Widget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class GmailPage extends BasePage {
    Logger logger = LogManager.getLogger(GmailPage.class);

    @FindBy(xpath = "(//a[@title='Gmail'])[2]")
    private Label gmailLogo;

    @FindBy(xpath = "//div[@class = 'aic']//div[@role='button']")
    private Button writeNewEmailButton;

    @FindBy(xpath = "//div[@role='dialog']")
    private Widget dialogWindow;

    @FindBy(name = "to")
    private Input receiver;

    @FindBy(name = "subjectbox")
    private Input subject;

    @FindBy(css = "div[role='textbox']")
    private Input emailBody;

    @FindBy(xpath = "(//div[@class='dC']/div)[1]")
    private Button sendBtn;

    @FindBy(xpath = "//div[@class='Cp']//tr")
    private List<WebElement> unreadInboxEmailList;

    @FindBy(xpath = "(//div[@class='asa'])[3]")
    private Button deleteSelectedEmailsBtn;

    @FindBy(xpath = "(//div[@class='vh'])[1]")
    private Widget deleteEmailsSuccessMessage;

    @FindBy(xpath = "(//div[@class='vh']/span/span)[2]/span")
    private Button revertBtn;

    @FindBy(xpath = "//span[@class='aT']/span")
    private Widget actionCancelledMsg;

    @FindBy(id = "link_vsm")
    private Button viewEmailSentMsgBtn;


    public GmailPage() {
        expWait(30).until(ExpectedConditions.elementToBeClickable(gmailLogo.getWebElement()));
    }

    public Label getGmailLogo() {
        return this.gmailLogo;
    }

    public Button getWriteNewEmailButton() {
        return this.writeNewEmailButton;
    }

    public Widget getDialogWindow() {
        return this.dialogWindow;
    }

    public Input getReceiver() {
        return this.receiver;
    }

    public Input getSubject() {
        return this.subject;
    }

    public Input getEmailBody() {
        return this.emailBody;
    }

    public Button getSendBtn() {
        return this.sendBtn;
    }

    public List<WebElement> getUnreadInboxEmailList() {
        return unreadInboxEmailList;
    }

    public Button getDeleteSelectedEmailsBtn() {
        return deleteSelectedEmailsBtn;
    }

    public Widget getDeleteEmailsSuccessMessage() {
        return deleteEmailsSuccessMessage;
    }

    public Button getRevertBtn() {
        return revertBtn;
    }

    public Widget getCancelledMsg() {
        return actionCancelledMsg;
    }

    public Button getViewEmailSentMsgBtn() {
        return viewEmailSentMsgBtn;
    }

    public void clickWriteNewEmailBtn(){
        logger.trace("Clicking Write new email btn...");
        getWriteNewEmailButton().click();
    }

    public void setReceiver(String receiverEmail){
        logger.trace("Typing receiver email ...");
        getReceiver().sendKeys(receiverEmail);
    }

    public void setEmailSubject(String subject){
        logger.trace("Typing email subject...");
        getSubject().sendKeys(subject);
    }

    public void setEmailBody(String emailBody){
        logger.trace("Typing email email body...");
        getEmailBody().sendKeys(emailBody);
    }

    public void clickSendBtn(){
        logger.trace("Clicking Send email btn...");
        getSendBtn().click();
    }

    public void checkUnreadEmailsInboxAndDelete(int emailsQty) {
        if (getUnreadInboxEmailList().size() >= emailsQty) {
            for (int index = 0; index < emailsQty; index++) {
                logger.trace("Choosing " + (index + 1) + " unread email...");
                expWait(35).until(ExpectedConditions.elementToBeClickable(getUnreadInboxEmailList().get(index)));
                getUnreadInboxEmailList().get(index).findElement(By.xpath("(./td)[2]/div")).click();
            }
            getDeleteSelectedEmailsBtn().click();
        } else {
            logger.trace("There is no " + emailsQty + " unread emails inbox!");
            return;
        }
    }

    public boolean isDeleteEmailsSuccessMessageDisplayed(){
        return getDeleteEmailsSuccessMessage().isDisplayed();
    }

    public void clickRevertBtn(){
        expWait(25).until(ExpectedConditions.elementToBeClickable(getRevertBtn().getWebElement()));
        getRevertBtn().click();
    }

    public boolean isActionCancelledMsgDispalyed(){
        expWait(25).until(ExpectedConditions.visibilityOf(getCancelledMsg().getWebElement()));
        return getCancelledMsg().isDisplayed();
    }
}
