package com.sofia.pageobjects.gmailpages;

import com.sofia.decorators.elements.Button;
import com.sofia.decorators.elements.Checkbox;
import com.sofia.decorators.elements.Label;
import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.sofia.utilmanager.jsonparser.JsonParser.getButtonWait;
import static com.sofia.utilmanager.jsonparser.JsonParser.getPageLoadWait;

public class GmailHomePage extends GeneralGmailPage {
    private static final int PAGE_LOAD_WAIT = getPageLoadWait();
    private static final int BUTTON_WAIT = getButtonWait();

    @FindBy(xpath = "//div[@role='checkbox']")
    private List<Checkbox> emailCheckboxes;

    @FindBy(xpath = "(//div[@class='asa'])[3]")
    private Button deleteButton;

    @FindBy(id = "link_undo")
    private Button undoButton;

    @FindBy(className = "bAq")
    private Label undoWidget;

    public List<Checkbox> getCheckboxes(){
        return emailCheckboxes;
    }

    public boolean isCheckboxesListEmpty() {
        return getCheckboxes().isEmpty();
    }

    public void checkEmailsBoxes(int amount) {
        for (int i = 0; i < amount; i++) {
            int finalI = i;
            new WebDriverWait(driver, PAGE_LOAD_WAIT).ignoring(StaleElementReferenceException.class).until(webDriver -> {
                getCheckboxes().get(finalI).setChecked();
                return true;
            });
        }
    }

    public void clickDeleteButton() {
        (new WebDriverWait(driver, BUTTON_WAIT)).until(ExpectedConditions.elementToBeClickable(deleteButton.getButtonElement()));
        deleteButton.click();
    }

    public void clickUndoButton() {
        undoButton.click();
    }

    public String getUndoWidgetAttributeValue(){
        return undoWidget.getAttributeValue();
    }

}
