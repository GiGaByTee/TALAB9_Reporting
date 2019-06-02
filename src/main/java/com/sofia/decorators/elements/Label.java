package com.sofia.decorators.elements;

import com.sofia.decorators.Element;
import org.openqa.selenium.WebElement;

public class Label extends Element {
    public Label(WebElement webElement) {
        super(webElement);
    }

    public String getAttributeValue() {
        return webElement.getAttribute("innerHTML");
    }
}
