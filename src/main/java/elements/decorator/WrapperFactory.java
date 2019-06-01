package elements.decorator;

import elements.Element;
import elements.IElement;
import org.openqa.selenium.WebElement;

public class WrapperFactory {
    static Element createInstance(Class<Element> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}