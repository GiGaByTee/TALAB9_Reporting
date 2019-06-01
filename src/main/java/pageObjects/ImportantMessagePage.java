package pageObjects;

import elements.Button;
import elements.Checkbox;
import elements.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportantMessagePage extends BasePage {
    @FindBy(xpath = "//div[@class='AO']//div[@class='nH']/div[2]//tr/td[2]/div")
    private List<Checkbox> checkboxes;
    @FindBy(css = "[gh] [act='10']")
    private Button deleteButton;
    @FindBy(css = "[gh] .Dj > .ts:nth-of-type(2)")
    private WebElement lettersAmount;
    @FindBy(css = "span.bAq")
    private Label messAreDeletedLabel;
    private Logger logger = LogManager.getLogger(ImportantMessagePage.class);

    public void selectLettersInImportantFolder(int letterAmount) {
        selectEmails(letterAmount, checkboxes);
    }

    public void deleteSelectedLetters() {
        this.deleteButton.click();
        Wait wait = new FluentWait(driver);
        wait.until(ExpectedConditions.visibilityOf(messAreDeletedLabel.getWebElement()));
    }

    public int getLetterInFolderAmount() {
        try {
            return Integer.parseInt(lettersAmount.getText());
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            return 0;
        }
    }
}
