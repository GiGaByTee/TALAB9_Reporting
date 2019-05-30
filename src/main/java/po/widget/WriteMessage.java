package po.widget;

import decorator.elements.Button;
import decorator.elements.Input;
import org.openqa.selenium.support.FindBy;
import po.BasePage;

public class WriteMessage extends BasePage {

    @FindBy(css ="textarea[name='to']")
    private Input receiverInput;

    @FindBy(className = "editable")
    private Input messageInput;

    @FindBy(xpath = "//img[@class='Ha' and @src='images/cleardot.gif']")
    private Button closedButton;

    @FindBy(name = "subjectbox")
    private Input subjectInput;

    public void inputReceiver(String receiver){
        receiverInput.clearAndSendKeys(receiver);
    }

    public void inputMessage(String message){
        messageInput.clearAndSendKeys(message);
    }

    public void inputSubject(String subject){
        subjectInput.clearAndSendKeys(subject);
    }

    public void toCloseMessage(){
        closedButton.click();
    }
}
