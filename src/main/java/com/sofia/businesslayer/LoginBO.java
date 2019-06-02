package com.sofia.businesslayer;

import com.sofia.pageobjects.gmailpages.GmailSignInPageObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginBO {
    private static final Logger LOG = LogManager.getLogger(LoginBO.class);
    private long threadId = Thread.currentThread().getId();
    private GmailSignInPageObj loginPage = new GmailSignInPageObj();

    public String loginIntoAccount(String testUsername, String testPassword){
        LOG.info(threadId + " " + testUsername + " Making username input");
        loginPage.typeUernameAndSubmit(testUsername);
        LOG.info(threadId + " " +  testUsername + " Username Correct. Making password input");
        loginPage.typePasswordAndSubmit(testPassword);
        LOG.info(threadId + " " + testUsername + " Log in successfully!");
        return loginPage.getActiveUsernameAttributeValue();
    }
}
