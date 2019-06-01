package po;

import decorator.elements.Button;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3' and @role='button' and @gh='cm']")
    private Button composeMessage;

    public void goToWriteMessage() {
        composeMessage.click();

    }
}
