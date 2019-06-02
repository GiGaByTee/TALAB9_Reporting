package listener;

import driver.DriverLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.format;

@Listeners
public class CustomListener implements ITestListener {

    private static Logger log = LogManager.getLogger(CustomListener.class);
    private String logPath = "Logs/test-yaml.log";

    @Override
    public void onStart(ITestContext context) {
        log.info("------------------TEST START---------------------- ");
        log.info("TEST STARTED in time: " + context.getStartDate().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("TEST FINISHED in time: " + context.getEndDate().toString());
        log.info("------------------TEST FINISH---------------------- ");
        removeLogs();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(format(" Result : SUCCESS : %s", result.getMethod().getMethodName()));
        appendLogToReport();
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("result : FAILURE " + result.getMethod().getMethodName().toUpperCase());
        saveScreenshotToReport(result);
        appendLogToReport();
        Reporter.setCurrentTestResult(null);
    }

    private void appendLogToReport() {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            Path path = Paths.get(logPath);
            List<String> strings = Files.readAllLines(path);
            strings.stream().distinct().forEach(s -> Reporter.log(s + "</br>"));
        } catch (IOException e) {
            log.error("Can't read logs to Report");
        }
    }

    private void saveScreenshotToReport(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                String failureImageFileName = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
                File scrFile = ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(failureImageFileName));
                String userDirector = System.getProperty("user.dir") + "/";
                String screenCapture = "<table><tr><td><font style=\"text-decoration: underline;\" " +
                        "size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr> ";
                Reporter.log(screenCapture);
                Reporter.log("<tr><td><a href=\"" + userDirector + failureImageFileName + "\"><img src=\"file:///"
                        + userDirector + failureImageFileName + "\" alt=\"\"" + "height=’120′ width=’120′/></td></tr> ");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    public void removeLogs() {
        try {
            FileUtils.write(new File(logPath), "", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
