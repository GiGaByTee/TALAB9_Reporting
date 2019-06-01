package com.igor.business;

import com.igor.logger.CustomLogger;
import com.igor.page.LogInPage;

public class LogInBO {
    private LogInPage logInPage;

    public LogInBO(){
        logInPage = new LogInPage();
    }

    public void logIn(String username, String password){
        CustomLogger.info("Set username");
        logInPage.setUsernameAndSubmit(username);
        CustomLogger.info("Set password");
        logInPage.setPasswordAndSubmit(password);
    }
}
