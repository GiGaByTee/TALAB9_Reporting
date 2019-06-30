import business.LoginPageBO;
import business.MainPageBO;
import dataproviders.UserDataProvider;
import model.Email;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.*;
import driver.DriverProvider;

import static constants.Constants.*;

public class GmailPageTest {
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @BeforeMethod
    @Parameters({"url"})
    public void setUp(String url) {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
        DriverProvider.getWebDriver().get(url);
    }

    @DataProvider(parallel = true)
    private Object[][] loginData() {
        return UserDataProvider.getDataFromDataProvider();
    }

    @Test(dataProvider = "loginData")
    public void correctlySavedDataTest(String email, String password, String receiverEmail, String subject, String text) {
        Email enteredEmail = new Email();
        enteredEmail.setEmailAddress(receiverEmail);
        enteredEmail.setSubject(subject);
        enteredEmail.setBody(text);
        LoginPageBO loginBO = new LoginPageBO();
        MainPageBO mainPageBO = new MainPageBO();
        loginBO.login(email, password);
        mainPageBO.composeEmail(receiverEmail, subject, text);
        mainPageBO.openSavedDraft();
        Email savedEmail = mainPageBO.getSavedEmail();
        Assert.assertTrue(enteredEmail.equals(savedEmail));
        logger.info("Saved email equals to entered");
        mainPageBO.sendLetter();
    }

    @AfterMethod
    public void tearDown() {
        DriverProvider.removeDriver();
    }
}