package com.halas;

import com.halas.business.GmailAuthorisationBO;
import com.halas.business.GmailMessageBO;
import com.halas.driver.WebDriverManager;
import com.halas.model.Message;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.halas.driver.WebDriverManager.getWebDriver;
import static com.halas.util.parser.JsonParser.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyStepdefs {
    private static final Logger LOG = LogManager.getLogger(MyStepdefs.class);
    private GmailAuthorisationBO gmailAuthorisationBO;
    private GmailMessageBO gmailMessageBO;
    private Message message;

    public MyStepdefs() {
        gmailAuthorisationBO = new GmailAuthorisationBO();
        gmailMessageBO = new GmailMessageBO();
        message = new Message(getWhoReceiveMessage(), getWhoReceiveCopyMessage(), getWhoReceiveHiddenCopyMessage(), getThemeMessage(), getMessage());
    }
    @Before
    public void initBrowser(){
        getWebDriver().get(getBaseUrl());
    }

    @After
    public void closeBrowser(){
        WebDriverManager.shutdown();
    }

    @When("^User \"([^\"]*)\" and \"([^\"]*)\" authorise into account$")
    public void userLoginAndPasswordAuthoriseIntoAccount(String userLogin, String userPassword) {
        gmailAuthorisationBO.authoriseUser(userLogin, userPassword);
        assertTrue(gmailAuthorisationBO.isSuccessAuthorisation(userLogin));
        LOG.info("SUCCESS authorization.");
    }

    @Then("^Create draft message$")
    public void createDraftMessage() {
        gmailMessageBO.createDraftMessage(message);
    }

    @Then("^Go to draft message and click on last message$")
    public void goToDraftMessageAndClickOnLastMessage() {
        gmailMessageBO.goToDraftMessagesClickOnFirstOnTopMessage();
        Message actualMessage = gmailMessageBO.getMessage();
        assertEquals(actualMessage, message);
        LOG.info("Message FINE.");
    }

    @Then("^Send message$")
    public void sendMessage() {
        gmailMessageBO.sendMessage();
    }
}
