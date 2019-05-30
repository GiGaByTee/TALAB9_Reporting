package bo;

import io.qameta.allure.Step;
import po.LoginPage;

public class SignInBO {

    private  LoginPage loginPage = new LoginPage();

    @Step("Sign in step with login: {0}, password: {1}, for method: {login} step...")
    public void login(String login, String password){
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
    }
}
