package decorator;

import decorator.elements.BaseElement;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    public static BaseElement createInstance(Class<BaseElement> clas, WebElement webElement){
        try{
            return clas.getConstructor(WebElement.class).newInstance(webElement);
        }catch (Exception e){
            e.printStackTrace();
            throw new AssertionError("WebElement can`t be represented as " + clas);

        }
    }
}
