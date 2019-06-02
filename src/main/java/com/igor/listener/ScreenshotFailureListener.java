package com.igor.listener;

import com.igor.logger.CustomLogger;
import com.igor.utils.provider.DriverProvider;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotFailureListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger(ScreenshotFailureListener.class);

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            File screenshot = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotName = getFileName();
            String path = getPath(screenshotName);
            FileUtils.copyFile(screenshot, new File(path));
            CustomLogger.logImage(screenshotName);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String getFileName() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "Thread" + Thread.currentThread() + "_" + "screenshot" + ".png";
    }

    private String getPath(String name) {
//        File directory = new File(".");
        return "target\\surefire-reports\\html\\" + name;
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
