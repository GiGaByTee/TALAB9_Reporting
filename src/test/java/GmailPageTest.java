import business.LoginPageBO;
import business.MainPageBO;
import dataprovider.UserDataProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.*;
import driver.DriverProvider;

import java.io.IOException;

import static constants.Constants.*;

public class GmailPageTest {
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @BeforeMethod
    public void setUp() {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
        DriverProvider.getWebDriver().get(URL);
    }

    @DataProvider(parallel = true)
    private Object[][] loginData() throws IOException {
        return UserDataProvider.getJsonData();
    }

    @Test(dataProvider = "loginData")
    public void correctlySavedDataTest(String userName, String password, String emailReceiver, String subject, String text) {
        LoginPageBO loginBO = new LoginPageBO();
        MainPageBO mainPageBO = new MainPageBO();
        loginBO.login(userName, password);
        mainPageBO.composeEmail(emailReceiver, subject, text);
        mainPageBO.openSavedDraft();
        Assert.assertTrue(mainPageBO.isSavedEmailInDraftEqualsToEntered(emailReceiver));
        logger.info("Saved email address equals to entered");
        Assert.assertTrue(mainPageBO.isSavedSubjectEqualsToEntered(subject));
        logger.info("Saved subject of letter equals to entered");
        Assert.assertTrue(mainPageBO.isSavedLetterTextEqualsToEntered(text));
        logger.info("Saved text message equals to entered");
        mainPageBO.sendLetter();
    }

    @AfterMethod
    public void tearDown() {
        DriverProvider.removeDriver();
    }
}