package listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        logger.trace("Test " + iTestContext.getName() + " finished");    }
}
