package decorator.elements;

import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebElement element) {
        super(element);
    }

    public boolean isClickable(){
        return element.isEnabled();
    }

    public void click(){
        if(isClickable()&&isEnabled()) {
            element.click();
        }else System.out.println("Is not clickable");
    }

    public String getText(){
       return element.getText();
    }

    public Boolean isEnabled(){
        return element.isEnabled();
    }
}
