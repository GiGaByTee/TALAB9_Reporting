package decorator;

import decorator.elements.BaseElement;
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
        Class<BaseElement> decoratableClass = decoratableClass(field);
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
    private Class<BaseElement> decoratableClass(Field field) {
        Class<?> clas = field.getType();
        if (List.class.isAssignableFrom(clas)) {
            if (Objects.isNull(field.getAnnotation(FindBy.class)) &&
                    Objects.isNull(field.getAnnotation(FindBys.class))) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clas = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }
        if (BaseElement.class.isAssignableFrom(clas)) {
            return (Class<BaseElement>) clas;
        } else {
            return null;
        }
    }

    protected BaseElement createElement(ClassLoader loader, ElementLocator locator, Class<BaseElement> clas) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clas, proxy);
    }

    @SuppressWarnings("unchecked")
    protected List<BaseElement> createList(ClassLoader loader, ElementLocator locator, Class<BaseElement> clas) {
        InvocationHandler handler = new LocatingCustomElementsListHandler(locator, clas);
        return (List<BaseElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
