package businessObjects;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.LoginPage;

public class LoginBO {
    private LoginPage loginPage;
    private Logger logger = LogManager.getLogger(LoginBO.class);
    public LoginBO() {
        loginPage = new LoginPage();
    }
    @Step("User ligin with name: {0}, password: {1} using method: {method} step...")
    public void login(String email, String password) {
        loginPage.typeEmailAndSubmit(email);
        logger.info("User with email "+ email+" entered his email");
        loginPage.typePasswordAndSubmit(password);
        logger.info("User with email "+ email+" entered his password");
        logger.info("User with email "+ email+" successfully log in");

    }
}
