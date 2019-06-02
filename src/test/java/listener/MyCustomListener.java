package listener;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MyCustomListener extends TestListenerAdapter {

    private Logger logger = LogManager.getLogger(MyCustomListener.class);
    private File logs = new File("logs/Reporter.log");

    public void onTestStart(ITestResult result){
        logger.info(Thread.currentThread().getName()+": Test class started: " + result.getTestClass().getName());
        logger.info(Thread.currentThread().getName()+": Test started: " + result.getName());
    }

    public void onTestSuccess(ITestResult result){
        logger.info(Thread.currentThread().getName()+": Test success: " + result.getName());
        appendLogToAllure();
    }

    public void onTestFailure(ITestResult result){
        getScreenshot();
        logger.info(Thread.currentThread().getName()+": Test failed: " + result.getName());
        if(Objects.nonNull(result.getThrowable())){
            result.getThrowable().printStackTrace();
        }
        appendLogToAllure();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        appendLogToAllure();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);

        try {
            FileWriter writer = new FileWriter(logs);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] getScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

        @Attachment(value = "Test logs", type = "text/html")
        private byte[] appendLogToAllure () {
            try {
                Path path = Paths.get("logs/Reporter.log");
                return Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

}
