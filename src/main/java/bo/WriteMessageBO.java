package bo;

import po.DraftMessagePage;
import po.HomePage;
import po.widget.WriteMessage;

public class WriteMessageBO {

    private HomePage homePage = new HomePage();
    private WriteMessage writeMessage = new WriteMessage();
    private DraftMessagePage draftMessagePage = new DraftMessagePage();

    public Boolean isSaveInDraft(String subject){
        draftMessagePage.goToDraftMessage();
        draftMessagePage.chooseLastMessage();
        return draftMessagePage.isCorrectSubject(subject);
    }

    public void sendMessage(){
        draftMessagePage.sendMessage();
    }

    public void tryToWriteMessage(String receiver, String subject, String message){
        homePage.goToWriteMessage();
        writeMessage.inputReceiver(receiver);
        writeMessage.inputSubject(subject);
        writeMessage.inputMessage(message);
        writeMessage.toCloseMessage();
    }

}
