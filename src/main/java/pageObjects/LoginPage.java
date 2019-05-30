package pageObjects;

import elements.Button;
import elements.Input;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "input#identifierId")
    private Input userEmailInput;
    @FindBy(id = "identifierNext")
    private Button emailNextButton;
    @FindBy(xpath = "//div[@id='password']//input[@name='password']")
    private Input passwordInput;
    @FindBy (id = "passwordNext")
    private Button passNextButton;
    @FindBy(css = "ul li:nth-child(2) a")
    private Button signInButton;

    public void typeEmailAndSubmit(String userEmail){
        userEmailInput.sentKeys(userEmail);
        emailNextButton.click();
    }
    public void typePasswordAndSubmit(String userPassword){
        passwordInput.sentKeys(userPassword);
        passNextButton.click();
    }
}
