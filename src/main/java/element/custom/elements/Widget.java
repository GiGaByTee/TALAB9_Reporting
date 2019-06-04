package element.custom.elements;

import element.Element;
import org.openqa.selenium.WebElement;

public class Widget extends Element {
    public Widget(WebElement webElement){
        super(webElement);
    }

    public boolean isDisplayed(){
        return webElement.isDisplayed();
    }
}
