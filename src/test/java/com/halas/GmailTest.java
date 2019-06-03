package com.halas;

import com.halas.business.GmailAuthorisationBO;
import com.halas.business.GmailMessageBO;
import com.halas.driver.WebDriverManager;
import com.halas.listener.CustomTestListener;
import com.halas.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.halas.driver.WebDriverManager.getWebDriver;
import static com.halas.util.parser.JsonParser.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({CustomTestListener.class})
public class GmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailTest.class);

    @DataProvider(parallel = true)
    public Iterator<Object[]> usersLoginPassword() {
        return Stream.of(getUsers()).iterator();
    }

    @BeforeMethod
    void initUrlAndPages() {
        getWebDriver().get(getBaseUrl());
    }

    @Test(dataProvider = "usersLoginPassword")
    void testSingInAndGoToSavedMessageAndCheckCorrectAndSendMess(String userLogin, String userPassword) {
        GmailAuthorisationBO gmailAuthorisationBO = new GmailAuthorisationBO();
        GmailMessageBO gmailMessageBO = new GmailMessageBO();
        Message message = new Message(getWhoReceiveMessage(), getWhoReceiveCopyMessage(), getWhoReceiveHiddenCopyMessage(), getThemeMessage(), getMessage());
        gmailAuthorisationBO.authoriseUser(userLogin, userPassword);
        //assert to success login by userLogin
        assertTrue(gmailAuthorisationBO.isSuccessAuthorisation(userLogin));
        LOG.info("SUCCESS authorization.");
        gmailMessageBO.createDraftMessage(message);
        gmailMessageBO.goToDraftMessagesClickOnFirstOnTopMessage();
        Message actualMessage = gmailMessageBO.getMessage();
        //assert actual and expected fields from message
        assertEquals(actualMessage, message);
        LOG.info("Message FINE.");
        gmailMessageBO.sendMessage();
    }

    @AfterMethod
    void closeResources() {
        WebDriverManager.shutdown();
    }
}
