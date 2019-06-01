package po;

import decorator.elements.Button;
import decorator.elements.Input;
import decorator.elements.Span;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DraftMessagePage extends BasePage {

    @FindBy(css = "input[name='q']")
    private Input searchInput;

    @FindBy(xpath = "//form[@role='search']/button[4]")
    private Button goToDraftMessage;

    @FindBy(xpath = "//span[./span[@data-standalone-draft-id and @data-legacy-standalone-draft-id]]")
    private List<Span> draftMessages;

    @FindBy(xpath = "//div[@class='aoI' and @role='region']")
    private WebElement writeMessageWidget;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3' and @role='button']")
    private WebElement sendButton;

    public void goToDraftMessage() {
        searchInput.clearAndSendKeys("in:draft");
        goToDraftMessage.click();
    }

    public void chooseLastMessage() {
        draftMessages.get(0).click();
    }

    public Boolean isCorrectSubject(String subject){
        return writeMessageWidget.getAttribute("aria-label").contains(subject);
    }


    public void sendMessage() {
        sendButton.click();
        goToDraftMessage.click();
    }
}
