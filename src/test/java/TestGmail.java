import bo.SignInBO;
import bo.WriteMessageBO;
import driver.DriverManager;
import listener.MyCustomListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilits.json.Parser;
import utilits.model.UserModel;
import utilits.properties.FilePath;

import java.io.File;

@Listeners({MyCustomListener.class})
class TestGmail {
    private Logger logger = LogManager.getLogger(TestGmail.class);

    @DataProvider(parallel = true)
    public Object[] getUsers() {
        FilePath filePath = new FilePath();
        File json = new File(filePath.propertyFile("fileWayJSON"));
        Parser parser = new Parser();
        return parser.getData(json);
    }

    @Test(dataProvider = "getUsers")
    public void LoginTest(UserModel user) {
        SignInBO signIn = new SignInBO();
        signIn.login(user.getLogin(), user.getPassword());
        logger.info("You have successfully logged in.");

        WriteMessageBO writeMessage = new WriteMessageBO();
        writeMessage.tryToWriteMessage(
                user.getMessageModel().getReceiver(),
                user.getMessageModel().getSubject(),
                user.getMessageModel().getMessage());
        logger.info("Message was wrote but was not sent.");

        Assert.assertTrue(writeMessage.isSaveInDraft(user.getMessageModel().getSubject()));
        logger.info("Message was saved as a draft.");
        writeMessage.sendMessage();
        logger.info("Message was sent.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
