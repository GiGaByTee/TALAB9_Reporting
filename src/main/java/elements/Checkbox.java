package elements;

import org.openqa.selenium.WebElement;

public class Checkbox extends Element {
    public Checkbox(WebElement webElement) {
        super(webElement);
    }

    public void setChecked() {
        webElement.click();
    }

    private boolean isChecked() {
        return webElement.isSelected();
    }
}
