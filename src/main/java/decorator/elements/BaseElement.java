package decorator.elements;

import org.openqa.selenium.WebElement;

public class BaseElement {
    protected WebElement element;

    public BaseElement(WebElement element){
        this.element = element;
    }
}
