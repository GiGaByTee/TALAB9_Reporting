package com.sofia.businesslayer;

import com.sofia.pageobjects.gmailpages.GmailHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeEmailPageBO {
    private static final Logger LOG = LogManager.getLogger(HomeEmailPageBO.class);
    private GmailHomePage homePage = new GmailHomePage();
    private long threadId = Thread.currentThread().getId();

    public boolean deleteCheckedEmails(int checkboxAmount) {
        LOG.info(threadId + " There are enough messages to delete");
        homePage.checkEmailsBoxes(checkboxAmount);
        LOG.info(threadId + " Deleting...");
        homePage.clickDeleteButton();
        return !homePage.isCheckboxesListEmpty();
    }

    public String undoEmailDeletion() {
        LOG.info(threadId + " Clicking Undo Button");
        homePage.clickUndoButton();
        return homePage.getUndoWidgetAttributeValue();
    }
}
