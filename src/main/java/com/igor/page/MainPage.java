package com.igor.page;

import com.igor.decorator.element.Button;
import com.igor.decorator.element.Label;
import com.igor.decorator.element.TextArea;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{
    @FindBy(xpath = "//div[@role='complementary']/..//div[@role='button']")
    private Button composeButton;
    @FindBy(xpath = "//form[@role='search']//input")
    private TextArea searchField;
    @FindBy(xpath = "//form[@role='search']/button[4]")
    private Button searchButton;

    public void clickToComposeButton(){
        composeButton.safelyClick();
    }

    public void goToSentPage(){
        searchField.clearAndType("in:sent");
        searchButton.safelyClick();
        webDriverWait.until(ExpectedConditions.urlContains("sent"));
    }
}
