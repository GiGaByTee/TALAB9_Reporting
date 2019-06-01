import bo.SignInBO;
import bo.WriteMessageBO;
import driver.DriverManager;
import listener.CustomTestNGListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilits.json.Parser;
import utilits.model.UserModel;
import utilits.properties.FilePath;

import java.io.File;

@Listeners({CustomTestNGListener.class})
public class TestGmail {

    @DataProvider(parallel = true)
    public Object[] getUsers() {
        FilePath filePath = new FilePath();
        File json = new File(filePath.propertyFile("filePathJSON"));
        Parser parser = new Parser();
        return parser.getData(json);
    }

    @Test(dataProvider = "getUsers")
    public void LoginSaveMessageInDraftAndSendTest(UserModel user) {

        SignInBO signIn = new SignInBO();
        signIn.login(user.getLogin(), user.getPassword());

        WriteMessageBO writeMessage = new WriteMessageBO();
        writeMessage.tryToWriteMessage(
                user.getMessageModel().getReceiver(),
                user.getMessageModel().getSubject(),
                user.getMessageModel().getMessage());

        Assert.assertTrue(writeMessage.isSaveInDraft(user.getMessageModel().getSubject()));
        writeMessage.sendMessage();
    }



    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
