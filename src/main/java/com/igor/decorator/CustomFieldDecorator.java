package com.igor.decorator;

import com.igor.decorator.element.AbstractElement;
import com.igor.decorator.tools.LocatingCustomElementListHandler;
import com.igor.decorator.tools.WrapperFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;
import java.util.Objects;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Method is called by factory for every field
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<AbstractElement> decoratableClass = decoratableClass(field);
        // if class of field is decorated
        if (!Objects.isNull(decoratableClass)) {
            ElementLocator locator = factory.createLocator(field);
            if (Objects.isNull(locator)) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    /**
     * Return decorated class field,
     * or null if class isn't correct for decorator
     */
    @SuppressWarnings("unchecked")
    private Class<AbstractElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // List has to have annotation
            if (Objects.isNull(field.getAnnotation(FindBy.class)) &&
                    Objects.isNull(field.getAnnotation(FindBys.class))) {
                return null;
            }

            // List have to be parametrized
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // get class for elements of list
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (AbstractElement.class.isAssignableFrom(clazz)) {
            return (Class<AbstractElement>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Creating element.
     * Found WebElement and passes it to the custom class
     */
    private AbstractElement createElement(ClassLoader loader, ElementLocator locator,
                                          Class<AbstractElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    /**
     * Сreating List
     */
    @SuppressWarnings("unchecked")
    private List<AbstractElement> createList(ClassLoader loader, ElementLocator locator,
                                             Class<AbstractElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        return (List<AbstractElement>) Proxy.newProxyInstance(
                loader, new Class[] {List.class}, handler);
    }

}