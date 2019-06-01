import allure.CustomListener;
import businessObjects.LoginBO;
import businessObjects.MessagesBO;
import driver.DriverLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.parser.CSVParser;
import utils.PropertyUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;

import static utils.model.Consts.LETTER_AMOUNT_TO_DELETE;

@Listeners({CustomListener.class})
public class GmailTest {
    private Logger logger = LogManager.getLogger(GmailTest.class);
    @DataProvider(parallel = true)
    private Iterator<Object[]> loginDataCSV() {
        return CSVParser.parseCSVFile(new File(PropertyUtils.getConfigList().get("csv_path"))).iterator();
    }
    @Severity(value = SeverityLevel.CRITICAL)
    @Owner(value = "Romana Chykalo")
    @Description(value = "\n" +
            "the test checks the login option and the ability to mark messages as important and delete them")
    @Test(dataProvider = "loginDataCSV")
    public void generalTest(String email, String password) {
        LoginBO loginBO = new LoginBO();
        loginBO.login(email, password);
        MessagesBO messagesBO = new MessagesBO();
        messagesBO.markAsImportant(LETTER_AMOUNT_TO_DELETE);
        Assert.assertTrue(messagesBO.isMarkAsImportantLabelPresent());
        int messAmountBeforeDelete=messagesBO.openImportantFolder();
        int messAmountAfterDelete = messagesBO.deleteMessages(LETTER_AMOUNT_TO_DELETE);
        Assert.assertEquals(messAmountBeforeDelete-messAmountAfterDelete, LETTER_AMOUNT_TO_DELETE);
    }

    @BeforeMethod()
    public void setUp() {
        DriverLoader.getDriver().get(PropertyUtils.getConfigList().get("cite"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverLoader.tearDown();
    }

    @BeforeTest
    public void logTestStart() {
        logger.info(" Test START");
    }

    @AfterTest
    public void logTestEnd() {
        logger.info("Test END ");
    }
}