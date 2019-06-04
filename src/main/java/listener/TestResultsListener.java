package listener;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultsListener implements ITestListener {
    Logger logger = LogManager.getLogger(TestResultsListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.trace("Test " + iTestResult.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.trace("Test " + iTestResult.getName() + " is success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("Test " + iTestResult.getName() + " failed");
        byte[] srcFile =  ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(srcFile);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.trace("Test " + iTestResult.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.trace("Test " + iTestContext.getName() + " started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.trace("Test " + iTestContext.getName() + " finished");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

}
