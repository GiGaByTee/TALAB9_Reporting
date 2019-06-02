package com.sofia;

import com.sofia.businesslayer.HomeEmailPageBO;
import com.sofia.businesslayer.LoginBO;
import com.sofia.utilmanager.driver.DriverManager;
import com.sofia.utilmanager.property.Property;
import com.sofia.utilmanager.report.CustomListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.sofia.utilmanager.driver.DriverManager.quitDriver;
import static com.sofia.utilmanager.jsonparser.JsonParser.*;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

@Listeners({CustomListener.class})
public class GmailUndoDeleteEmailsTest {
    private static final Logger LOG = LogManager.getLogger(GmailUndoDeleteEmailsTest.class);
    private static final int CHECKBOX_AMOUNT = getCheckboxAmount();
    private static final int USERS_AMOUNT = getUserAmount();
    private static final String TEST_PASSED = "Test passed successfully";

    @BeforeMethod()
    public void setStartedPage(){
        DriverManager.getDriverInstance().get(Property.getProperty("login_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        Object[][] objects = new Object[USERS_AMOUNT][];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object[]{getUsername(i), getPassword(i)};
        }

        return Stream.of(objects).iterator();
    }

    @Test(dataProvider = "users")
    public void logInAndSendEmail(String testUsername, String testPassword) {
        LoginBO signIn = new LoginBO();
        assertEquals(signIn.loginIntoAccount(testUsername, testPassword), testUsername);
        HomeEmailPageBO homePage = new HomeEmailPageBO();
        assertTrue(homePage.deleteCheckedEmails(CHECKBOX_AMOUNT));
        assertTrue(homePage.undoEmailDeletion().matches("^[^ .]+ .+\\.$"));
        LOG.info(TEST_PASSED);
    }

    @AfterMethod
    public void endTest() {
        quitDriver();
    }
}
