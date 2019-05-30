package decorator.elements;

import org.openqa.selenium.WebElement;

public class Input extends BaseElement {

    public Input(WebElement element) {
        super(element);
    }

    public void clearAndSendKeys(String key){
        element.clear();
        element.sendKeys(key);
    }

    public void clear(){
        element.clear();
    }


    public Boolean isEnabled(){
        return element.isEnabled();
    }

}
