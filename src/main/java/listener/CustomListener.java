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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static java.lang.String.format;
import static org.testng.Reporter.log;

@Listeners
public class CustomListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        log("Test with name: "+context.getName()+", STARTED in time: " + context.getStartDate().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        log("Test completed on: " + context.getEndDate().toString());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        log(format("result : SUCCESS : %s", result.getMethod().getMethodName()));
    }
    @Override
    public void onTestFailure(ITestResult result) {
        log("result : FAILURE "+ result.getMethod().getMethodName().toUpperCase());
        /*File imageFile = ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
        String failureImageFileName = result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss")
                .format(new GregorianCalendar().getTime()) + ".png";
        String destDir = "./test-output/html/screenshots/";
        new File(destDir).mkdirs();
        log(imageFile.toString());
        try {
            FileUtils.copyFile(imageFile, new File(destDir + "/" +failureImageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        if (!result.isSuccess()) {
            try {
                String failureImageFileName =new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
                String failureImageFileName1;
                File  scrFile = ((TakesScreenshot)DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(failureImageFileName));
                String userDirector = System.getProperty("user.dir") + "/";
                String s1 = "<table><tr><td><font style=\"text-decoration: underline;\" " +
                        "size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr> ";
                Reporter.log(s1);
                Reporter.log("<tr><td><a href=\""+ userDirector + failureImageFileName +"\"><img src=\"file:///" + userDirector+ failureImageFileName + "\" alt=\"\""+ "height=’120′ width=’120′/></td></tr> ");
                Reporter.setCurrentTestResult(null);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log(result.getMethod().getMethodName() + "result : SKIPPED");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
