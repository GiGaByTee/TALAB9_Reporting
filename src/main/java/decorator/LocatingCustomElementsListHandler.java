package decorator;

import decorator.elements.BaseElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocatingCustomElementsListHandler implements InvocationHandler {

    private final ElementLocator locator;
    private final Class<BaseElement> clas;

    public LocatingCustomElementsListHandler(ElementLocator locator, Class<BaseElement>clas){
        this.locator = locator;
        this.clas = clas;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<WebElement> elements = locator.findElements();
        List<BaseElement> customsElements = new ArrayList<>();
        for(WebElement element: elements){
            customsElements.add(WrapperFactory.createInstance(clas, element));
        }
        try{
            return method.invoke(customsElements, args);
        }catch (InvocationTargetException e){
            throw e.getCause();
        }
    }
}
