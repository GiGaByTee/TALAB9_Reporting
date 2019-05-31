package com.igor.page;

import com.igor.decorator.element.Label;
import org.openqa.selenium.support.FindBy;

public class SentPage extends BasePage{
    @FindBy(xpath = "//div[@role='main']//tbody/tr[1]/td[6]//span[@class='bog']/span")
    private Label sentLetter;

    public String getLetter(){
        return sentLetter.isEmpty() ? "" : sentLetter.getText();
    }
}

