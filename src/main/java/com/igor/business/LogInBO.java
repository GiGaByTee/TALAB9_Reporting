package com.igor.business;

import com.igor.page.LogInPage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInBO {
    private static final Logger LOGGER = LogManager.getLogger(LogInBO.class);
    private LogInPage logInPage;

    public LogInBO(){
        logInPage = new LogInPage();
    }

    @Step("Log in with usenamename: {0} and password: {1} running method: {method} step")
    public void logIn(String username, String password){
        LOGGER.info("Set username");
        logInPage.setUsernameAndSubmit(username);
        LOGGER.info("Set password");
        logInPage.setPasswordAndSubmit(password);
    }
}
