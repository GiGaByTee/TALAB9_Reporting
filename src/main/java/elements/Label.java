package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class Label extends Element {

    public Label(WebElement webElement) {
        super(webElement);
    }

    public boolean isDisplayed(){
        try{
        return webElement.isDisplayed();
        } catch (org.openqa.selenium.StaleElementReferenceException ex){
            return webElement.isDisplayed();
        }
    }
}
