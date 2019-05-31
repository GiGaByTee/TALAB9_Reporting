package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.listener.CustomListener;
import com.igor.utils.provider.DriverProvider;
import com.igor.utils.property.Property;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.*;
import static org.testng.Assert.assertTrue;

@Listeners({CustomListener.class})
public class GmailTest {
    private final String MESSAGE_TITLE = UUID.randomUUID().toString();

    @BeforeMethod()
    public void setStartedPage(){
        DriverProvider.getDriver().get(Property.getProperty("initial_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        return Stream.of(getUsers()).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmailTest(String username, String password) {
        LogInBO logInBO = new LogInBO();
        MessageBO messageBO = new MessageBO();
        logInBO.logIn(username, password);
        messageBO.fillFieldsForMessage(getIncorrectReceiver(), MESSAGE_TITLE, getMessage());
        messageBO.sendMessage();
        assertTrue(messageBO.isAlertWidgetVisible());
        messageBO.correctReceiver(getReceiver());
        messageBO.sendMessage();
        assertTrue(messageBO.isLetterSent(MESSAGE_TITLE));
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
