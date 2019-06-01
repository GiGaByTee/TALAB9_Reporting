package com.igor.listener;

import com.igor.utils.provider.DriverProvider;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

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
            String nameScreenshot = "screenshot";
            String path = getPath(nameScreenshot);
            FileUtils.copyFile(screenshot, new File(path));
            Reporter.log("<br>  <img src=" + path + " height='100' width='100' /><br>");
            Reporter.log("<a href=" + path + " target='_blank' >" + getFileName(nameScreenshot) + "</a>");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String getFileName(String nameTest) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
        Date date = new Date();
        return dateFormat.format(date) + "_" + nameTest + ".png";
    }

    private String getPath(String nameTest) throws IOException {
        File directory = new File(".");
        return directory.getCanonicalPath() + "\\target\\surefire-reports\\screenShots\\" + getFileName(nameTest);
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
