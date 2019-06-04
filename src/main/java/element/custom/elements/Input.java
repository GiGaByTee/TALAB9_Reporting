package element.custom.elements;

import element.Element;
import org.openqa.selenium.WebElement;

public class Input extends Element {

    public Input(WebElement webElement){
        super(webElement);
    }

    public void sendKeys(String txt){
        webElement.sendKeys(txt);
    }

    public void clear(){
        webElement.clear();
    }
}
