package com.igor.decorator.element;

import com.igor.utils.constant.Constants;
import com.igor.utils.provider.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends Element {
    public Button(WebElement webElement) {
        super(webElement);
    }

    public boolean isClickable(){
        return webElement.isDisplayed() && webElement.isEnabled();
    }

    public void click(){
        webElement.click();
    }

    public void safelyClick(){
        if(this.isClickable()){
            webElement.click();
        }else{
            (new WebDriverWait(DriverProvider.getDriver(), Constants.EXPLICIT_WAIT)).
                    until(ExpectedConditions.elementToBeClickable(webElement)).click();
        }
    }
}
