package bo;

import po.LoginPage;

public class SignInBO {

    private  LoginPage loginPage = new LoginPage();

    public void login(String login, String password){
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
    }
}
