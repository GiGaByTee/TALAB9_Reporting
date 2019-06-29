package decorator;

import elements.DefaultElementFactory;
import elements.Element;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<Element> decorateableClass = decorateableClass(field);
        if (decorateableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return createElement(loader, locator, decorateableClass);
        }
        return super.decorate(loader, field);
    }

    private Class<Element> decorateableClass(Field field) {

        Class<?> clazz = field.getType();
        if (Element.class.isAssignableFrom(clazz)) {
            return (Class<Element>) clazz;
        } else {
            return null;
        }
    }

    protected Element createElement(ClassLoader loader,
                                    ElementLocator locator,
                                    Class<Element> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return DefaultElementFactory.createInstance(clazz, proxy);
    }
}


