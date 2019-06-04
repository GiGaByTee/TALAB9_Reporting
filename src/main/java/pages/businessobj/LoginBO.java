package pages.businessobj;

import pages.pagemodels.GmailPage;
import pages.pagemodels.LoginPage;

public class LoginBO {
    private LoginPage loginPage;

    public LoginBO() {
    }

    public GmailPage login(String login, String passw){
        loginPage = new LoginPage();
        loginPage.navigateToLoginPg();
        loginPage.setEmailField(login);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(passw);
        GmailPage gmailPage = loginPage.clickPasswordNextBtn();
        return gmailPage;
    }

}
