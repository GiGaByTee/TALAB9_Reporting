package com.halas.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.Objects;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Метод видкликається фабрикою для кожного поля в класі
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratableClass = decoratableClass(field);
        // якщо клас поля декорується
        if (Objects.nonNull(decoratableClass)) {
            ElementLocator locator = factory.createLocator(field);
            if (Objects.isNull(locator)) {
                return null;
            }
            // елемент
            return createElement(loader, locator, decoratableClass);
        }
        return null;
    }

    /**
     * Повертає декоруючий клас поля,
     * або null якщо клас не підходить для декоратора
     */
    private Class<?> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        // у елемента повинен бути конструктор, приймаючий WebElement
        try {
            clazz.getConstructor(WebElement.class);
        } catch (Exception e) {
            return null;
        }
        return clazz;
    }

    /**
     * Створення елемента.
     * Знаходить WebElement і передає його в кастомний клас
     */
    protected <T> T createElement(ClassLoader loader,
                                  ElementLocator locator, Class<T> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return createInstance(clazz, proxy);
    }

    /**
     * Створює екземпляр класа,
     * викликаючи конструктор з аргументом WebElement
     */
    private <T> T createInstance(Class<T> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class)
                    .newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz);
        }
    }
}