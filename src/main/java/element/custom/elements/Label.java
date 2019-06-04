package element.custom.elements;

import element.Element;
import org.openqa.selenium.WebElement;

public class Label extends Element {

    public Label(WebElement webElement){
        super(webElement);
    }

    public String getText(){
        return webElement.getText();
    }

    public boolean isDisplayed(){
        return webElement.isDisplayed();
    }
}
