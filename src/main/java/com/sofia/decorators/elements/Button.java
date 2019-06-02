package com.sofia.decorators.elements;

import com.sofia.decorators.Element;
import org.openqa.selenium.WebElement;

public class Button extends Element {
    public Button(WebElement webElement) {
        super(webElement);
    }

    public void click(){
        webElement.click();
    }

    public WebElement getButtonElement(){
        return webElement;
    }
}
