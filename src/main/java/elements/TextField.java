package elements;

import org.openqa.selenium.WebElement;

public class TextField extends Element {

    public TextField(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String text) {
        webElement.sendKeys(text);
    }

    public String getAttribute(String text) {
        return webElement.getAttribute(text);
    }

    public String getText() {
        return webElement.getText();
    }
}
