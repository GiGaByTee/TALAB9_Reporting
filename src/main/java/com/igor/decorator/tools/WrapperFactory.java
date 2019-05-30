package com.igor.decorator.tools;

import com.igor.decorator.element.AbstractElement;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    /**
     * Create instance,
     * implemented IElement,
     * invoke constructor with arguments WebElement
     */
    public static AbstractElement createInstance(Class<AbstractElement> clazz,
                                          WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).
                    newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}