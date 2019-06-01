package elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pageObjects.MainPage;

public class Input extends Element {
    public Input(WebElement webElement) {
        super(webElement);
    }

    public void sentKeys(String data) {
        if(isTextFieldClean()){
            webElement.sendKeys(data);
        } else {
            webElement.clear();
            webElement.sendKeys(data);
        }
    }

    public void typeAndSubmit(String value) {
        webElement.sendKeys(value + Keys.ENTER);
    }

    public boolean isTextFieldClean() {
        if (webElement.getText().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
