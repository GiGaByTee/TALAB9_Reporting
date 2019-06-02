package com.sofia.utilmanager.report;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sofia.utilmanager.driver.DriverManager.getDriverInstance;

public class CustomListener implements ITestListener{
    private static final Logger LOG = LogManager.getLogger(CustomListener.class);
    private static final String SCREENSHOT_SAVE_PATH = "logs/screenshots/%s.png";

    private static String getCurrentDateTime() {
        Date instant = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("_yyyy-MM-dd_HH.mm.ss.SSS");
        return sdf.format(instant);
    }

    private static void takeScreenshot(File outputDirectory) {
        try {
            File scrFile = ((TakesScreenshot) getDriverInstance()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, outputDirectory);
            LOG.info("Screenshot taken.");
        } catch (IOException e) {
            LOG.error("Can't save screenshot file");
            LOG.error("Class: " + e.getClass());
            LOG.error("Message: " + e.getMessage());
            LOG.error(e.getStackTrace());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        outputErrors(result.getThrowable());
        String newScreenshotName = result + getCurrentDateTime();
        File outputFolder = new File(String.format(SCREENSHOT_SAVE_PATH, newScreenshotName));
        takeScreenshot(outputFolder);
    }

    private void outputErrors(Throwable throwable) {
        LOG.error("Test failed");
        LOG.error("Class: " + throwable.getClass());
        LOG.error("Message: " + throwable.getMessage());
        LOG.error(throwable.getStackTrace());
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Test passed successfully!");
    }
}
