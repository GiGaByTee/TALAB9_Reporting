package pages.businessobj;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.pagemodels.GmailPage;

public class GmailBO {
    private GmailPage gmailPage;

    public GmailBO() {}

    public GmailBO(GmailPage gmailPage) {
        this.gmailPage = gmailPage;
    }

    @Step("Send email")
    public void sendEmail(String recipient, String subject, String mailBody){
        gmailPage.clickWriteNewEmailBtn();
        gmailPage.setReceiver(recipient);
        gmailPage.setEmailSubject(subject);
        gmailPage.setEmailBody(mailBody);
        gmailPage.clickSendBtn();
        gmailPage.expWait(25).until(ExpectedConditions.visibilityOf(gmailPage.getViewEmailSentMsgBtn().getWebElement()));
    }

    @Step("Verifying if 'Email sent' message is displayed")
    public boolean isViewEmailSentMsgBtnDisplayed(){
        return gmailPage.getViewEmailSentMsgBtn().isDisplayed();
    }

    @Step("Delete unread emails")
    public void deleteUnreadEmail(int emailsQty){
        gmailPage.checkUnreadEmailsInboxAndDelete(emailsQty);
    }

    @Step("Verifu if emails are deleted")
    public boolean areEmailsDeleted(){
        return gmailPage.isDeleteEmailsSuccessMessageDisplayed();
    }

    @Step("Revert deleted emails")
    public void revertDeletedEmails(){
        gmailPage.clickRevertBtn();
    }

    @Step("Verify if Email are reverted message is displayed")
    public boolean isActionCancelledMsgDispalyed(){
        return gmailPage.isActionCancelledMsgDispalyed();
    }
}
