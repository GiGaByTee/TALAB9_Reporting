package elements.decorator;

import elements.Element;
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

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<Element> decoratableClass = decoratableClass(field);
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

    @SuppressWarnings("unchecked")
    private Class<Element> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if (List.class.isAssignableFrom(clazz)) {
            if (Objects.isNull(field.getAnnotation(FindBy.class)) &&
                    Objects.isNull(field.getAnnotation(FindBys.class))) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }
        if (Element.class.isAssignableFrom(clazz)) {
            return (Class<Element>) clazz;
        } else {
            return null;
        }
    }

    private Element createElement(ClassLoader loader, ElementLocator locator,
                                  Class<Element> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    @SuppressWarnings("unchecked")
    private List<Element> createList(ClassLoader loader, ElementLocator locator,
                                     Class<Element> clazz) {
        InvocationHandler handler = new Handler(locator, clazz);
        return (List<Element>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
    }

}