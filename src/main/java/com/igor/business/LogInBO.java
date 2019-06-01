package com.igor.business;

import com.igor.logger.AllureLogger;
import com.igor.page.LogInPage;
import ru.yandex.qatools.allure.annotations.Step;

public class LogInBO {
    private LogInPage logInPage;

    public LogInBO(){
        logInPage = new LogInPage();
    }

    @Step("Log in with usenamename: {0} and password: {1} running method: {method} step")
    public void logIn(String username, String password){
        AllureLogger.info("Set username");
        logInPage.setUsernameAndSubmit(username);
        AllureLogger.info("Set password");
        logInPage.setPasswordAndSubmit(password);
    }
}
