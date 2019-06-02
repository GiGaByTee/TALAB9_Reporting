package com.sofia.decorators.customdecotator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import com.sofia.decorators.IElement;
import com.sofia.decorators.WrapperFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.
        DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Метод викликається фабрикою для кожного поля в класі
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<IElement> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
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
     * Повертає задекорований клас поля
     * або null, якщо клас не відходить для декоратора
     */
    @SuppressWarnings("unchecked")
    private Class<IElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {
            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // отримуємо клас для елементів списку
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }
        if (IElement.class.isAssignableFrom(clazz)) {
            return (Class<IElement>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Створення елементу.
     * Знаходить WebElement і передає його в кастомний клас
     */
    protected IElement createElement(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    /**
     * Створення списку
     */
    @SuppressWarnings("unchecked")
    protected List<IElement> createList(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        List<IElement> elements = (List<IElement>) Proxy.newProxyInstance(
                loader, new Class[] {List.class}, handler);
        return elements;
    }

}