package com.sofia.decorators.elements;

import com.sofia.decorators.Element;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Input extends Element {
    public Input (WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    public void submitInput() {
        webElement.sendKeys(Keys.ENTER);
    }

}
