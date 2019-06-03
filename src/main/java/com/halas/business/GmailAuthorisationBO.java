package com.halas.business;

import com.halas.page.gmail.GmailAuthorizationPage;
import com.halas.page.gmail.GmailHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class GmailAuthorisationBO {
    private static final Logger LOG = LogManager.getLogger(GmailMessageBO.class);
    private GmailAuthorizationPage authorizationPage;
    private GmailHomePage gmailHomePage;

    public GmailAuthorisationBO() {
        authorizationPage = new GmailAuthorizationPage();
        gmailHomePage = new GmailHomePage();
    }

    public void authoriseUser(final String login, final String password) {
        LOG.info("Authorise user..");
        authorizationPage.fillLoginAreaAndClickNext(login);
        authorizationPage.fillPasswordAreaAndClickNext(password);
    }

    public boolean isSuccessAuthorisation(final String userLogin) {
        LOG.info("Check success authorization..");
        WebElement accountCircle = gmailHomePage.getAccountCircle();
        return accountCircle.getAttribute("aria-label").contains(userLogin);
    }
}
