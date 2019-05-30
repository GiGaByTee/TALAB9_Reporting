package allure;

import driver.DriverLoader;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static java.lang.String.format;
import static org.testng.Reporter.log;

@Listeners
public class CustomListener extends TestListenerAdapter {
    @Override
    public void onStart(ITestContext context) {
        log("Test with name: " + context.getName() + ", STARTED in time: " + context.getStartDate().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        log("Test completed on: " + context.getEndDate().toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log(format("result : SUCCESS : %s", result.getMethod().getMethodName()));
    }

    public void onTestFailure(ITestResult result) {
        log("result : FAILURE "+ result.getMethod().getMethodName().toUpperCase());
        File imageFile = ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
        String failureImageFileName = result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss")
                .format(new GregorianCalendar().getTime()) + ".png";
        String destDir = "./screenshots/";
        new File(destDir).mkdirs();
        log(imageFile.toString());
        try {
            FileUtils.copyFile(imageFile, new File(destDir + "/" +failureImageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        log(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
