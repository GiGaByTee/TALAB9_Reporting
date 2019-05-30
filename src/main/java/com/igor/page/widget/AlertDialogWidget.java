package com.igor.page.widget;

import com.igor.decorator.element.Button;
import com.igor.decorator.element.Element;
import com.igor.page.BasePage;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AlertDialogWidget extends BasePage {
    @FindBy(xpath = "//div[@role='alertdialog']")
    private List<Element> alertDialogs;
    @FindBy(xpath = "//button[@name='ok']")
    private Button buttonOk;

    public boolean alertDialogIsEnable() {
        return alertDialogs.size() != 0;
    }

    public void clickToButtonOk() {
        buttonOk.safelyClick();
    }
}
