import business.LoginPageBO;
import business.MainPageBO;
import dataproviders.UserDataProvider;
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
        LoginPageBO loginBO = new LoginPageBO();
        MainPageBO mainPageBO = new MainPageBO();
        loginBO.login(email, password);
        mainPageBO.composeEmail(receiverEmail, subject, text);
        mainPageBO.openSavedDraft();
        Assert.assertTrue(mainPageBO.isSavedEmailInDraftEqualsToEntered(receiverEmail));
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