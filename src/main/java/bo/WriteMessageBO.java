package bo;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import po.DraftMessagePage;
import po.HomePage;
import po.widget.WriteMessage;

public class WriteMessageBO {

    private Logger logger = LogManager.getLogger(WriteMessageBO.class);
    private HomePage homePage = new HomePage();
    private WriteMessage writeMessage = new WriteMessage();
    private DraftMessagePage draftMessagePage = new DraftMessagePage();

    @Step("Verify subject: {0} step...")
    public Boolean isSaveInDraft(String subject){
        draftMessagePage.goToDraftMessage();
        draftMessagePage.chooseLastMessage();
        return draftMessagePage.isCorrectSubject(subject);
    }
    @Step("To send message step... ")
    public void sendMessage(){
        logger.info("Message was saved as a draft.");
        draftMessagePage.sendMessage();
        logger.info("Message was sent.");
    }

    @Step("To fill the fields receiver: {0}, subject: {1}, message: {2} step...")
    public void tryToWriteMessage(String receiver, String subject, String message){
        logger.info("You have successfully logged in.");
        homePage.goToWriteMessage();
        writeMessage.inputReceiver(receiver);
        writeMessage.inputSubject(subject);
        writeMessage.inputMessage(message);
        writeMessage.toCloseMessage();
        logger.info("Message was wrote but was not sent.");
    }

}
