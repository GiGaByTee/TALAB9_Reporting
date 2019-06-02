package com.sofia.decorators;
import org.openqa.selenium.WebElement;

public class Element implements IElement {
    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }
}