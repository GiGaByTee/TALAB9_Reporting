package po;

import decorator.elements.Button;
import decorator.elements.Input;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private Input loginInput;

    @FindBy(id = "identifierNext")
    private Button buttonSubmitLogin;

    @FindBy(name = "password")
    private Input passwordInput;

    @FindBy(xpath = "//div[@id='passwordNext']")
    private Button buttonSubmitPassword;

    public void inputLogin(String login){
        loginInput.clearAndSendKeys(login);
        buttonSubmitLogin.click();
    }

    public void inputPassword(String password){
        passwordInput.clearAndSendKeys(password);
        buttonSubmitPassword.click();
    }

}
