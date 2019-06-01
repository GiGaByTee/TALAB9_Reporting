package com.igor.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

public class CustomLogger {
    private static final Logger LOGGER = LogManager.getLogger(CustomLogger.class);

    public static void info(String log) {
        Reporter.log("<p style='color:yellow; background:black'>" + log + "</p>");
        LOGGER.info(log);
    }

    public static void logImage(String path) {
        Reporter.log("<br>  <img src=" + path + " height='500' width='936' /><br>");
    }
}
