package com.halas.business;

import com.halas.model.Message;
import com.halas.page.gmail.GmailFormSendMessage;
import com.halas.page.gmail.GmailHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailMessageBO {
    private static final Logger LOG = LogManager.getLogger(GmailMessageBO.class);
    private GmailFormSendMessage gmailFormSendMessage;
    private GmailHomePage gmailHomePage;

    public GmailMessageBO() {
        gmailFormSendMessage = new GmailFormSendMessage();
        gmailHomePage = new GmailHomePage();
    }

    public void createDraftMessage(final Message message) {
        LOG.info("create draft message..");
        gmailHomePage.clickOnWriteSomeoneButton();
        //якщо знайде що курсор є не на елементі email, а на якомусь іншому
        if (isMouseFocusOnNotEmailField()) {
            LOG.info("Mouse isn't focus on email, click on email");
            gmailFormSendMessage.clickOnEmailField();
        }
        gmailFormSendMessage.clickOnCC();
        gmailFormSendMessage.clickOnBCC();
        fillMessage(message);
        gmailFormSendMessage.clickOnButtonSaveAndCloseFormMessage();
    }

    private void fillMessage(final Message message) {
        LOG.info("Fill message in gmail..");
        gmailFormSendMessage.fillEmailField(message.getTo());
        gmailFormSendMessage.fillCCField(message.getCc());
        gmailFormSendMessage.fillBCCField(message.getBcc());
        gmailFormSendMessage.fillSubjectField(message.getSubject());
        gmailFormSendMessage.fillMessageField(message.getMessage());
    }

    private boolean isMouseFocusOnNotEmailField() {
        return gmailFormSendMessage.isDisplayedEmailFieldWithoutCcAndBcc();
    }

    public void goToDraftMessagesClickOnFirstOnTopMessage() {
        LOG.info("Go to draft messages and click on first on top message.");
        gmailHomePage.clickOnDraftsMessages();
        gmailHomePage.clickOnFirstOnTopSavedMessage();
        if (isMouseFocusOnNotEmailField()) {
            gmailFormSendMessage.clickOnEmailField();
        }
    }

    public Message getMessage() {
        LOG.info("Get actual message from gmail..");
        String actualTo = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        String actualCC = gmailFormSendMessage.getTextFromCcField();
        String actualBCC = gmailFormSendMessage.getTextFromBccField();
        String actualSubject = gmailFormSendMessage.getTextFieldThemeSend();
        String actualMess = gmailFormSendMessage.getTextFromMessage();
        return new Message(actualTo, actualCC, actualBCC, actualSubject, actualMess);
    }

    public void sendMessage() {
        LOG.info("Send message..");
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilSendingMessageWillEnd();
    }
}
