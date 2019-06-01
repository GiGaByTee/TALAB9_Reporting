package pageObjects;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import elements.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[@aria-label and @role='button']//following::td[@jscontroller]//div[@role]")
    private List<Checkbox> importantMarkCheckboxes;
    @FindBy(xpath = "//form[@role='search']//input")
    private Input gmailSearchInput;
    @FindBy(xpath = "//button[@aria-label and following-sibling::*[position()=1][name()='table']]")
    private Button gmailSearchButton;
    @FindBy(xpath = "//div[@class='vh']/span/span")
    private Label actionDoneMessageLabel;
    private Logger logger = LogManager.getLogger(MainPage.class);

    public void markImportantMessages(int letterAmount) {
        Wait wait = new FluentWait(driver)
                .withTimeout(50, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.stalenessOf(importantMarkCheckboxes.get(letterAmount - 1).getWebElement()));
        selectEmails(letterAmount, importantMarkCheckboxes);
    }

    public boolean isActionDoneLabelDisplayed() {
        return actionDoneMessageLabel.isDisplayed();
    }

    public void openImportantFolder(String value) {
        gmailSearchInput.sentKeys(value);
        gmailSearchButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("imp"));
        logger.info("Navigated to important messages folder with URL:: " + driver.getCurrentUrl());
    }
}
