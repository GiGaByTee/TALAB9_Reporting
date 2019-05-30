package com.igor.business;

import com.igor.page.LogInPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInBO {
    private static final Logger LOGGER = LogManager.getLogger(LogInBO.class);
    private LogInPage logInPage;

    public LogInBO(){
        logInPage = new LogInPage();
    }

    public void logIn(String username, String password){
        LOGGER.info("Set username");
        logInPage.setUsernameAndSubmit(username);
        LOGGER.info("Set password");
        logInPage.setPasswordAndSubmit(password);
    }
}
