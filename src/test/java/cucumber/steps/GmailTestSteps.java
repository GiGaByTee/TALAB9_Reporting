package cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.Consts;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.businessobj.GmailBO;
import pages.businessobj.LoginBO;

public class GmailTestSteps {
    private LoginBO loginBO = new LoginBO();
    private GmailBO gmailBO;

    @Before
    public void setUp(){
        DriverManager.getDriver();
    }
    @Given("^User is logged in Gmail with credential \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userIsLoggedInGmailWithCredentialAnd(String login, String passw) throws Throwable {
        gmailBO = new GmailBO(loginBO.login(login, passw));
    }

    @When("^User delete emails and clicks revert action button$")
    public void userDeleteEmailsAndClicksRevertActionButton() throws Throwable {
        gmailBO.deleteUnreadEmail(Consts.COUNT_OF_EMAILS);
        gmailBO.revertDeletedEmails();
    }

    @Then("^Emails are reverted and proper message is displayed$")
    public void emailsAreRevertedAndProperMessageIsDisplayed() throws Throwable {
        Assert.assertTrue(gmailBO.isActionCancelledMsgDispalyed(), "Cancel message widget wasn't shown.");
    }

    @After
    public void tearDown(){
        DriverManager.driverQuit();
    }

}
