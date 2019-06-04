import data.Consts;
import data.TestDataProvider;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.businessobj.GmailBO;
import pages.businessobj.LoginBO;

public class GmailTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.getDriver();
    }

    @Test(enabled = false, dataProvider = "recipientsMails", dataProviderClass = TestDataProvider.class, priority = 1)
    public void sendTestEmailsForUsers(String recipient, String subject, String mailBody){
        LoginBO loginBO = new LoginBO();
        GmailBO emailBO = new GmailBO(loginBO.login(Consts.EMAIL, Consts.PASSW));
        emailBO.sendEmail(recipient, subject, mailBody);
        Assert.assertTrue(emailBO.isViewEmailSentMsgBtnDisplayed(), "Success message widget is not displayed.");
    }

    @Test(dataProvider = "users", dataProviderClass = TestDataProvider.class, priority = 2)
    public void delete3EmailsAndRevert(String login, String passw){
        LoginBO loginBO = new LoginBO();
        GmailBO emailBO = new GmailBO(loginBO.login(login, passw));
        emailBO.deleteUnreadEmail(Consts.COUNT_OF_EMAILS);
        Assert.assertTrue(emailBO.areEmailsDeleted(), "Emails weren't deleted.");
        emailBO.revertDeletedEmails();
        Assert.assertTrue(emailBO.isActionCancelledMsgDispalyed(), "Cancel message widget wasn't shown.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.driverQuit();
    }

}
