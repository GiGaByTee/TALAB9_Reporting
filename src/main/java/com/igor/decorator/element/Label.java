package com.igor.decorator.element;
import org.openqa.selenium.WebElement;

public class Label extends Element {
    public Label(WebElement webElement) {
        super(webElement);
    }

    public boolean isEmpty(){
        return webElement.getText().isEmpty();
    }

    public String getText(){
        return webElement.getText();
    }
}
