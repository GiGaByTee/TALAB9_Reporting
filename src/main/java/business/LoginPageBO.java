package business;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pages.LoginPage;

public class LoginPageBO {
    private static final Logger logger = LogManager.getLogger(LoginPageBO.class);
    LoginPage page = new LoginPage();

    public void login(String user, String password) {
        page.typeUsername(user);
        page.clickNextButton();
        page.typePassword(password);
        page.clickNextButton();
        logger.info("Login was successful");
    }
}
