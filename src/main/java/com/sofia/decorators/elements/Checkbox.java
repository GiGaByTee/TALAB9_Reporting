package com.sofia.decorators.elements;

import com.sofia.decorators.Element;
import org.openqa.selenium.WebElement;

public class Checkbox extends Element {
    public Checkbox(WebElement webElement) {
        super(webElement);
    }

    public void setChecked() {
        webElement.click();
    }

}