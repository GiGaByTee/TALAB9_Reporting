package element.custom.elements;

import element.Element;
import org.openqa.selenium.WebElement;

public class Button extends Element {

    public Button(WebElement webElement){
        super(webElement);
    }

    public void click(){
        webElement.click();
    }

    public boolean isDisplayed(){
        return webElement.isDisplayed();
    }
}
